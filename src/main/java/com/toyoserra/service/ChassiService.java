package com.toyoserra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyoserra.models.Chassi;
import com.toyoserra.repository.ChassiRepository;

@Service
public class ChassiService {
	
	@Autowired
	ChassiRepository chassiRepository;

	
	public Chassi consultarChassi(String numero){
		return chassiRepository.findByNumero(numero).orElse(null);
	}
	
	public List<Chassi> consultarTodos(){
		return chassiRepository.findAll();
	}
}
