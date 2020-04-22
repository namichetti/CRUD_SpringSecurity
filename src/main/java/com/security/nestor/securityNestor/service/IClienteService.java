package com.security.nestor.securityNestor.service;

import java.util.List;

import com.security.nestor.securityNestor.entity.Cliente;

public interface IClienteService {


	public List<Cliente> listar();
	public void alta(Cliente cliente);
	public void baja(Long id);
	public Cliente encontrarPorId(Long id);
	
}
