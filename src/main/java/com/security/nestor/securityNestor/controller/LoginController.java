package com.security.nestor.securityNestor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(name="error")String error, Model model,RedirectAttributes flash) {
		
		if(error!=null) {
			flash.addFlashAttribute("danger", "Usuario o contraseña incorrecto/s");
		}
		
		return "login";
	}
}
