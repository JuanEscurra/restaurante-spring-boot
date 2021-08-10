package com.massimo.web.app.controller;


import javax.validation.Valid;

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

import com.massimo.web.app.models.dao.IRoleDao;
import com.massimo.web.app.models.entity.User;
import com.massimo.web.app.models.service.IUserService;

@Controller
@RequestMapping("/user")
public class ControllerUser {
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IUserService userService;
	
	@ModelAttribute
	public void getRoles(Model model) {
		model.addAttribute("roles", roleDao.findAll());
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
	public String resultado(@Valid User user, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			return "./user/registro";
		}
		userService.save(user);
		status.setComplete();
		return "redirect:/user/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		userService.delete(id);
		return "redirect:/user/";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam String lastName, Model model) {
		model.addAttribute("users", userService.findByLastName(lastName));
		return "./user/index";
	}
}
