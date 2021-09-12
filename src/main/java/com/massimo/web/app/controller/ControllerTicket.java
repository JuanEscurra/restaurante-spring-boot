package com.massimo.web.app.controller;

import com.massimo.web.app.config.Message;
import com.massimo.web.app.config.TypeMessage;
import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderStatus;
import com.massimo.web.app.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ticket")
public class ControllerTicket {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", orderService.findByStatus(OrderStatus.ATENDIDO));
        return "/ticket/index";
    }

    @GetMapping("/collect/{id}")
    public String collect(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findOne(id));
        return "/ticket/collect";
    }

    @GetMapping("/finish/{id}")
    public String finish(@PathVariable Long id, Model model, RedirectAttributes flash) {
        if(orderService.changeState(id,OrderStatus.PAGADO) != null) {
            flash.addFlashAttribute("message",
                    new Message("Se ha guardado el pago del pedido exitosamente", TypeMessage.SUCCESSFUL));
        }
        flash.addFlashAttribute("messagge",
                new Message("Hubo un error al realizar el pago del pedido.", TypeMessage.ERROR));
        return "redirect:/ticket/collect/"+id;
    }

    @GetMapping("/print/{id}")
    public String finish(@PathVariable Long id) {

        return "redirect:/ticket/collect/"+id;
    }
}
