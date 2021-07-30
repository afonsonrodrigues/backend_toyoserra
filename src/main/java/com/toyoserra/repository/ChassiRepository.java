package com.toyoserra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toyoserra.models.Chassi;

@Repository
public interface ChassiRepository extends JpaRepository<Chassi, Long>{

	public Optional<Chassi> findByNumero(String numero);
	
}
