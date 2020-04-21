package com.security.nestor.securityNestor.dao;

import org.springframework.data.repository.CrudRepository;

import com.security.nestor.securityNestor.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario,Long>{

	public Usuario findByUsername(String username);
}
