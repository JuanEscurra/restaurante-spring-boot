package com.massimo.web.app.controller;

import javax.validation.Valid;

import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderStatus;
import com.massimo.web.app.service.order.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/order")
public class ControllerOrder {

    @Autowired
    private IOrderService orderService;

    @ModelAttribute
    public void data(Model model) {
        model.addAttribute("orderStatus", OrderStatus.values());
        model.addAttribute("order", new Order());
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "./order/index";
    }

    @GetMapping("/form/")
    public String form() {
        return "./order/registro";
    }

    @GetMapping("/form/{id}")
    public String formId(@PathVariable Long id,Model model) {
        model.addAttribute("order", orderService.findOne(id));
        return "./order/registro";
    }

    @PostMapping("/register")
    public String register(@Valid Order order, BindingResult result, Model model, SessionStatus status) {
        if(result.hasErrors()) {
			return "redirect:/order/";
		}
		orderService.save(order);
		status.setComplete();
        return "redirect:/order/";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        orderService.delete(id);
        return "redirect:/order/";
    }
}
