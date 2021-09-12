package com.massimo.web.app.controller;

import java.util.List;

import javax.validation.Valid;

import com.massimo.web.app.config.Message;
import com.massimo.web.app.config.TypeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.massimo.web.app.domain.entity.Product;
import com.massimo.web.app.service.product.IProductService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/menu")
public class ControllerMenu {

	private static Logger logger = LoggerFactory.getLogger(ControllerMenu.class);

	@Autowired
	private IProductService productService;
	
	@ModelAttribute
	public void getUsuario(Model model) {
		model.addAttribute("product", new Product());
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("productsActive", productService.findByStateActive());
		model.addAttribute("products", productService.findAll());
		return "./menu/index";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		return "./menu/registro";
	}
	
	@GetMapping("/form/{id}")
	public String formId(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "./menu/registro";
	}
	
	@PostMapping("/register")
	public String register(@Valid Product product,
						   BindingResult result,
						   Model model,
						   SessionStatus status,
						   RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "./menu/registro";
		}
		productService.save(product);
		flash.addFlashAttribute("message",
				new Message("Se ha guardado el producto exitosamente", TypeMessage.SUCCESSFUL));
		status.setComplete();
		return "redirect:/menu/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes flash) {
		productService.deleteById(id);
		flash.addFlashAttribute("message",
				new Message("Se ha eliminado el producto exitosamente", TypeMessage.SUCCESSFUL));
		return "redirect:/menu/";
	}
	
	@GetMapping("/reset")
	public String reset() {
		productService.restartListProducts();
		return "redirect:/menu/";
	}
	
	@GetMapping(value = "/search/{name}")
	public ResponseEntity<Product> search(@PathVariable String name) {
		Product product = productService.findByName(name);
		if(product != null) {
			return new ResponseEntity<>(product,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<Product>> searchJson(@RequestParam String name) {
		if(name == null || name.equals("")) {
			return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
		}
		return new ResponseEntity<>(productService.findByNameContaining(name),HttpStatus.OK);
	}
	
}
