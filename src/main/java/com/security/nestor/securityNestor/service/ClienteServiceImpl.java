package com.security.nestor.securityNestor.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.nestor.securityNestor.dao.IClienteDao;
import com.security.nestor.securityNestor.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> listar() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void alta(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void baja(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente encontrarPorId(Long id) {
		return clienteDao.findById(id).orElse(null);
	}
	
	
}
