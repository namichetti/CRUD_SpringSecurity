package com.security.nestor.securityNestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.security.nestor.securityNestor.entity.Cliente;
import com.security.nestor.securityNestor.service.IClienteService;


@Controller
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/")
	public String listar(Model model) {
		List<Cliente> clientes = clienteService.listar();
		model.addAttribute("titulo","Listado clientes");
		if(!clientes.isEmpty()) {
			model.addAttribute("clientes", clientes);
		}
		return "index";
	}
	
	@GetMapping("/alta")
	public String alta(Model model) {
		return "index";
	}
	
	@GetMapping("/delete/{id}")
	public String baja(Model model) {
		return "index";
	}
	
	@GetMapping("/editar/{id}")
	public String modificacion(Model model) {
		return "index";
	}
}
