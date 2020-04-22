package com.security.nestor.securityNestor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("/form")
	public String formulario(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo","Formulario cliente");
		model.addAttribute("cliente",cliente);
		return "form";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result,Model model) {
		
		if(result.hasErrors()){
			model.addAttribute("titulo","Formulario cliente");
			return "form";
		}
		
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
