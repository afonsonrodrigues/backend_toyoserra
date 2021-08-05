package com.toyoserra.resources;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.toyoserra.dto.ordem.OrdemServicoCadastroDTO;
import com.toyoserra.exception.MensagemException;
import com.toyoserra.models.OrdemServico;
import com.toyoserra.models.Perfil;
import com.toyoserra.models.StatusOrdem;
import com.toyoserra.repository.OrdemServicoRepository;
import com.toyoserra.security.service.AuthenticationService;

@CrossOrigin(origins = "http://localhost:19006/", maxAge = 3600)
@RestController
public class OrdemServicoResource {
	
	@Autowired
	OrdemServicoRepository repository;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@GetMapping("/ordemServico/todos")
	public ResponseEntity<?> getTodos(){
		
		List<OrdemServico> todos = repository.findAll();
		
		return new ResponseEntity<>(todos, HttpStatus.OK);
		
	}

	@GetMapping("/ordemServico/aberto")
	public ResponseEntity<?> getOrdensAbetas(){
		
		List<OrdemServico> ordensAbertas = repository.findOrdensAbertas();
		
		return new ResponseEntity<>(ordensAbertas, HttpStatus.OK);
		
	}
	
	@GetMapping("/ordemServico/fechada")
	public ResponseEntity<?> getOrdensFechadas(){
		
		List<OrdemServico> ordensFechadas = repository.findOrdensFechadas();
		
		return new ResponseEntity<>(ordensFechadas, HttpStatus.OK);
		
	}
	

	@PostMapping("/ordemServico")
	public ResponseEntity<?> postOrdemServico(@Validated @RequestBody OrdemServicoCadastroDTO dto) {
		
		try {
			Perfil perfil = authenticationService.getPerfil();
			OrdemServico ordem = dto.toOrdemServico();
			ordem.setUsuario(perfil.getNome());
			repository.save(ordem);
			
			return new ResponseEntity<>("Ordem de serviço cadastrada com sucesso.", HttpStatus.OK);
			
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Já existe uma ordem com esse número de ordem de serviço.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/ordemServico/{id}")
	public ResponseEntity<?> putStatusOrdem(@PathVariable Long id) throws MensagemException {
		
		try {
			OrdemServico ordem = repository.findById(id).orElseThrow(() -> new MensagemException("ID não encontrado."));
			
			if(ordem.getStatus() == StatusOrdem.FECHADA) {
				return new ResponseEntity<>("Essa ordem já foi fechada", HttpStatus.BAD_REQUEST);
			}
			
			LocalDateTime dataFechamento = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
			
			ordem.setDataFechamento(dataFechamento);
			ordem.setStatus(StatusOrdem.FECHADA);
			repository.save(ordem);
			
			return new ResponseEntity<>("Ordem fechada com sucesso.", HttpStatus.OK);
		} catch (MensagemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
