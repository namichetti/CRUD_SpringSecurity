package com.security.nestor.securityNestor.dao;

import org.springframework.data.repository.CrudRepository;

import com.security.nestor.securityNestor.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long>{

	public Cliente findByUsername(String username);
}
