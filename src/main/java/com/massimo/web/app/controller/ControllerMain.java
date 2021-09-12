package com.massimo.web.app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControllerMain {
    
    @GetMapping("/login")
    public String login(
    		@RequestParam(required = false) String error,
    		@RequestParam(required = false) String logout,
    		Model model, Principal principal, RedirectAttributes flash) {
        if(principal != null) {
            return "redirect:/";
        }
        if(error != null) {
        	model.addAttribute("error", "Nombre de usuario o contraseña incorrecta");
        }
        if(logout != null) {
        	model.addAttribute("logout", "Se ha finalizado sesión satisfactoriamente");
        }
        return "login";
    }

    @GetMapping({"/dashboard","/"})
    public String dashboard() {
        return "index";
    }
}
