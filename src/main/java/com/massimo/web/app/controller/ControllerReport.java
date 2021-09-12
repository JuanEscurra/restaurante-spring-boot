package com.massimo.web.app.controller;

import com.massimo.web.app.config.Message;
import com.massimo.web.app.config.TypeMessage;
import com.massimo.web.app.domain.entity.OrderStatus;
import com.massimo.web.app.service.order.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/report")
public class ControllerReport {

    private static Logger logger = LoggerFactory.getLogger(ControllerReport.class);

    @Autowired
    private IOrderService orderService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders",orderService.findByStatus(OrderStatus.PAGADO));
        return "/report/index";
    }

    @GetMapping("/search")
    public String searchById(@RequestParam Long id, RedirectAttributes flash) {
        if(orderService.existsById(id)) {
            return "redirect:/ticket/collect/" + id;
        }
        flash.addFlashAttribute("message",
                new Message("No existe ningún ticket guardado en la base de datos con ese id",TypeMessage.ERROR));
        return "redirect:/report/";
    }

    @GetMapping("/date")
    public String filterDate(@RequestParam Date lowerDate,
                             @RequestParam(required = false) Date upperDate,
                             Model model) {
        model.addAttribute("lowerDate",lowerDate);
        model.addAttribute("upperDate",upperDate);
        model.addAttribute("orders", orderService.listByDateReport(lowerDate, upperDate));
        return "/report/index";
    }
}
