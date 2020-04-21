package com.security.nestor.securityNestor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.nestor.securityNestor.dao.IUsuarioDao;
import com.security.nestor.securityNestor.entity.Rol;
import com.security.nestor.securityNestor.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario "+username+ "no encontrado.");
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		for(Rol rol: usuario.getRoles()) {
			
			roles.add(new SimpleGrantedAuthority(rol.getNombreRol()));
		}
		
		if(roles.isEmpty()) {
			throw new UsernameNotFoundException("El usuario "+username+" no posee rol/es");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, roles);
	}

}
