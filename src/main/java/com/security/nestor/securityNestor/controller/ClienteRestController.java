package com.security.nestor.securityNestor.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.security.nestor.securityNestor.entity.Cliente;
import com.security.nestor.securityNestor.service.IClienteService;

@RestController
@RequestMapping("/rest")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/")
	public List<Cliente> listar(){
		
		return clienteService.listar();
	}
	
	@GetMapping("/{id}")
	public Cliente getCliente(@PathVariable(value="id")Long id) {
		return clienteService.encontrarPorId(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void borrar(@PathVariable(value="id")Long id) {
		clienteService.baja(id);
	}
	
	@PutMapping("/alta")
	@ResponseBody
	public void actualizar(Cliente cliente) {
		
		clienteService.alta(cliente);
	}
	
	@PostMapping("/alta")
	@ResponseBody
	public void alta(Cliente cliente) {
		
		clienteService.alta(cliente);
	}
	
}
