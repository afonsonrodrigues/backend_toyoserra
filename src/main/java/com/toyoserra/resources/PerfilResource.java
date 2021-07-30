package com.toyoserra.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.toyoserra.dto.perfil.PerfilCadastroDTO;
import com.toyoserra.dto.perfil.PerfilEditarDTO;
import com.toyoserra.exception.MensagemException;
import com.toyoserra.models.Perfil;
import com.toyoserra.repository.PerfilRepository;
import com.toyoserra.security.service.AuthenticationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PerfilResource {
	

	@Autowired
	PerfilRepository repository;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@GetMapping("/perfil/todos")
	public ResponseEntity<?> getTodos(){
		
		List<Perfil> todos = repository.findAll();
		
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}
	
	@PostMapping("/perfil")
	public ResponseEntity<?> postPerfilDTO(@Validated @RequestBody PerfilCadastroDTO dto) {
		Perfil perfil = dto.toPerfil();
		try {
		repository.save(perfil);
		
		return new ResponseEntity<>("Cadastrado com sucesso.", HttpStatus.OK);
		
		} catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>("E-mail já cadastrado.", HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/perfil/{email}")
	public ResponseEntity<?> getPerfil(@PathVariable String email){
		Optional<Perfil> optional = repository.findByEmail(email);
		
		if (optional.isEmpty())
			return new ResponseEntity<>("E-mail não encontrado.", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(new Perfil(optional.get()), HttpStatus.OK);
	}
	
	@PutMapping("/perfil")
	public ResponseEntity<?> putPerfil(@RequestBody PerfilEditarDTO dto) throws MensagemException{
		
		Perfil perfil = dto.toPerfil();
		Perfil perfilAlterar = authenticationService.getPerfil();
		List<Perfil> perfis = repository.findAll();
		
		for (Perfil p : perfis) {
			if(p.getId().equals(perfilAlterar.getId())) {
				if(perfil.getEmail() != null)
					p.setEmail(perfil.getEmail());
				if(perfil.getSenha()!= null)
					p.setSenha(perfil.getSenha());
				if(perfil.getNome()!= null)
					p.setNome(perfil.getNome());
				if(perfil.getCargo()!= null)
					p.setCargo(perfil.getCargo());
				
				repository.save(p);
			}
		}
		
		return new ResponseEntity<>("Perfil alterado com sucesso.", HttpStatus.OK);

}
	@DeleteMapping("/perfil")
	public ResponseEntity<?> deletePerfil(){
		
	    Perfil perfil = authenticationService.getPerfil();
	    repository.delete(perfil);
	    return new ResponseEntity<>("Perfil deletado com sucesso.", HttpStatus.OK);
	        
	}
}