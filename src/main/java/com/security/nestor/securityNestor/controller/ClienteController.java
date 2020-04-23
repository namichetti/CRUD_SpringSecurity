package com.security.nestor.securityNestor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.security.nestor.securityNestor.entity.Cliente;
import com.security.nestor.securityNestor.service.IClienteService;


@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping({"/"})
	public String listar(Model model) {
		List<Cliente> clientes = clienteService.listar();
		model.addAttribute("titulo","Listado clientes");
		if(!clientes.isEmpty()) {
			model.addAttribute("clientes", clientes);
		}
		return "index";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/alta")
	public String formulario(Cliente cliente, Model model) {
		model.addAttribute("titulo","Formulario cliente");
		model.addAttribute("cliente",cliente);
		return "form";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid Cliente cliente, BindingResult result,Model model, RedirectAttributes flash, SessionStatus status) {
		
		if(result.hasErrors()){
			model.addAttribute("titulo","Formulario cliente");
			return "form";
		}
		
		String mensaje = (cliente.getId() == null)?"Cliente agregado con éxito": "Cliente editado con éxito";
		flash.addFlashAttribute("success",mensaje);
		clienteService.alta(cliente);
		status.setComplete();
		return "redirect:/";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value="id")Long id, Model model,RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id==null || id<=0) {
			flash.addFlashAttribute("danger","Cliente inválido");
			return "redirect:/";
		}
		
		cliente = clienteService.encontrarPorId(id);
		
		if(cliente == null) {
			flash.addFlashAttribute("danger","Cliente no existe");
			return "redirect:/";
		}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo","Editar cliente");
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String baja(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id==null || id<=0) {
			flash.addFlashAttribute("danger","Cliente inválido");
			return "redirect:/";
		}
		
		clienteService.baja(id);
		flash.addFlashAttribute("success","Cliente eliminado con éxito");
		return "redirect:/";
	}
	
	@Secured({"ROLE_USER, ROLE_ADMIN"})
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id")Long id,Model model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id==null || id<=0) {
			flash.addFlashAttribute("danger","Cliente inválido");
			return "redirect:/";
		}
		
		cliente = clienteService.encontrarPorId(id);
		
		if(cliente.getId()==null) {
			flash.addFlashAttribute("danger","Cliente no existe");
			return "redirect:/";
		}
		
		model.addAttribute("titulo", "Descripción del cliente");
		model.addAttribute("cliente",cliente);	
		
		return "ver";
	}
}
