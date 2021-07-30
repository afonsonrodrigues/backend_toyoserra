package com.toyoserra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyoserra.models.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Optional<Perfil> findByEmail(String email);

	boolean existsByEmail(String email);

}
