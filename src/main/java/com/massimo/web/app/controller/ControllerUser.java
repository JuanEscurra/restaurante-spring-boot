package com.massimo.web.app.controller;


import javax.validation.Valid;

import com.massimo.web.app.config.Message;
import com.massimo.web.app.config.TypeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.massimo.web.app.domain.entity.Role;
import com.massimo.web.app.domain.entity.User;
import com.massimo.web.app.service.user.IUserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class ControllerUser {
	
	@Autowired
	private IUserService userService;
	
	@ModelAttribute
	public void data(Model model) {
		model.addAttribute("roles", Role.values());
		model.addAttribute("user", new User());
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users", userService.findAll());
		return "./user/index";
	}
	
	@GetMapping("/form")
	public String registro(Model model) {
		model.addAttribute("user", new User());
		return "./user/registro";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.findOne(id));
		return "./user/registro";
	}
	
	@PostMapping("/registrar")
	public String resultado(@Valid User user,
							BindingResult result,
							Model model,
							SessionStatus status,
							RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "./user/registro";
		}
		userService.save(user);
		flash.addFlashAttribute("message",
				new Message("Se ha registrado al usuario exitosamente", TypeMessage.SUCCESSFUL));
		status.setComplete();
		return "redirect:/user/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
		userService.delete(id);
		flash.addFlashAttribute("message",
				new Message("Se ha eliminado al usuario exitosamente", TypeMessage.SUCCESSFUL));
		return "redirect:/user/";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam String lastName, Model model) {
		model.addAttribute("users", userService.findByLastName(lastName));
		return "./user/index";
	}
}
