package com.toyoserra.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toyoserra.models.Perfil;
import com.toyoserra.repository.PerfilRepository;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private PerfilRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Perfil> optional = repository.findByEmail(username);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("User not found");
	}	
	
	public Perfil getPerfil() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String corrente = authentication.getName();
		
		return (Perfil)loadUserByUsername(corrente);
	}

}
