package com.toyoserra.dto.perfil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.toyoserra.models.Cargo;
import com.toyoserra.models.Perfil;

public class PerfilCadastroDTO {
	
	@NotNull
	@NotBlank
	private String nome;
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String senha;
	
	@NotNull
	private Cargo cargo;
	
	
	public Perfil toPerfil() {
		
		Perfil perfil = new Perfil();
		perfil.setNome(this.nome);
		perfil.setEmail(this.email);
		perfil.setCargo(this.cargo);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCodificada = encoder.encode(this.senha);
		
		perfil.setSenha(senhaCodificada);
		
		
		return perfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	

	public PerfilCadastroDTO() {
		super();
	}
	
	
	
	
}
