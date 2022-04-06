package com.curso.poo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.curso.poo.model.Casa;


public interface CasaRepository extends JpaRepository<Casa, Integer>{
	
	@Modifying
	@Query("UPDATE Casa c SET c.tem_abits=true WHERE c.id=:id")
	void aplicarAbits(@Param("id") Integer id);
	
	@Query("SELECT c FROM Casa c WHERE c.cor LIKE LOWER(CONCAT ('%', :cor, '%'))")
	Page<Casa> buscarCasaCor(@Param("cor") String cor, Pageable paginacao);
	
}
