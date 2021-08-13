package com.massimo.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class ControllerOrder {

    @GetMapping("/")
    public String index() {
        return "./order/index";
    }
}
