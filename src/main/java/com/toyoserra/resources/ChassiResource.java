package com.toyoserra.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyoserra.models.Chassi;
import com.toyoserra.service.ChassiService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/chassis")
public class ChassiResource {

	@Autowired
	ChassiService chassiService;
	
	@GetMapping("/{numero}")
	public ResponseEntity<Chassi> buscar(@PathVariable("numero") String numero) {
		Chassi chassi = chassiService.consultarChassi(numero);
		return ResponseEntity.status(HttpStatus.OK).body(chassi);
	}
	
	@GetMapping
	public ResponseEntity<List<Chassi>> buscarTodos() {
		List<Chassi> chassis = chassiService.consultarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(chassis);
	}
	
}
