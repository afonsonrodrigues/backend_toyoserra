package com.toyoserra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.toyoserra.models.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
	
	@Query(value = "SELECT * FROM ordem_servico os WHERE os.status = 0", nativeQuery = true)
	List<OrdemServico> findOrdensAbertas();
	
	@Query(value = "SELECT * FROM ordem_servico os WHERE os.status = 1", nativeQuery = true)
	List<OrdemServico> findOrdensFechadas();

}
