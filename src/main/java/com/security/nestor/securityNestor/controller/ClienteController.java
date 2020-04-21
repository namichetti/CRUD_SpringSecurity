package com.security.nestor.securityNestor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

	@GetMapping("/")
	public String listar() {
		return "index";
	}
}
