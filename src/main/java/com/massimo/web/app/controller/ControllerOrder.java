package com.massimo.web.app.controller;

import javax.validation.Valid;

import com.massimo.web.app.config.Message;
import com.massimo.web.app.config.TypeMessage;
import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderDetail;
import com.massimo.web.app.domain.entity.OrderStatus;
import com.massimo.web.app.domain.entity.Product;
import com.massimo.web.app.service.order.IOrderService;
import com.massimo.web.app.service.product.IProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
public class ControllerOrder {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    private static Logger logger = LoggerFactory.getLogger(ControllerOrder.class);

    @ModelAttribute
    public void data(Model model) {
        model.addAttribute("orderStatus", OrderStatus.values());
        model.addAttribute("order", new Order());
        model.addAttribute("products", productService.findByStateActive());
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", orderService.findByStatus(OrderStatus.POR_ATENDER));
        return "./order/index";
    }

    @GetMapping("/form/{id}")
    public String formId(@PathVariable Long id,Model model) {
        model.addAttribute("order", orderService.findOne(id));
        model.addAttribute("orderDetail", new OrderDetail());
        return "./order/registro";
    }

    @PostMapping("/register")
    public String register(
        @Valid Order order,
        BindingResult result,
        Model model,
        SessionStatus status,
        RedirectAttributes flash) {

        if(result.hasErrors() | orderService.save(order) == null) {
            flash.addFlashAttribute("message",
                    new Message("Hubo un error al registrar el pedido, probablemente la mesa ya está ocupada", TypeMessage.ERROR));
            return "redirect:/order/";
		}
		status.setComplete();
        flash.addFlashAttribute("message",
                new Message("Se ha reservado la mesa con exitosamente.", TypeMessage.SUCCESSFUL));

        return "redirect:/order/";
    }



    @PostMapping(value = "/register/detail",
        headers = "Accept=application/json",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetail> registerDetail(@RequestBody OrderDetail orderDetail) {
        OrderDetail detail = orderService.save(orderDetail);
        logger.info("detail: " + detail);
        if(detail != null) {
            return new ResponseEntity<>(detail,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/detail/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetail> deleteDetail(@PathVariable Long id) {
        return orderService.deleteOrderDetailById(id) ?
                new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        try {
            orderService.delete(id);
            flash.addFlashAttribute("message",
                    new Message("Se elimino exitosamente la comanda", TypeMessage.SUCCESSFUL));
        } catch (Exception e) {
            flash.addFlashAttribute("message",
                    new Message("Hubo un error al eliminar al eliminar la comanda, es probable que la comanda no exista",
                            TypeMessage.ERROR));
        }

        return "redirect:/order/";
    }

    @GetMapping("/finish/{id}")
    public String finish(@PathVariable Long id, RedirectAttributes flash) {
        Order order = orderService.findOne(id);
        Message message = new Message();
        if(order.getDetails().size() > 0) {
            message.setType(TypeMessage.SUCCESSFUL);
            message.setValue("Se ha finalizado el pedido exitosamente.");
            orderService.changeState(id,OrderStatus.ATENDIDO);
        } else {
            message.setType(TypeMessage.ERROR);
            message.setValue("No se pudo finalizar el pedido, debido a que debe tener al menos un producto");
        }
        flash.addFlashAttribute(message);
        return "redirect:/order/";
    }
}
