package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Unidade;
import com.pc.cofipa.repository.helper.unidade.UnidadesQueries;

@Repository
public interface Unidades extends JpaRepository<Unidade, Long>, UnidadesQueries {
	
	public Optional<Unidade> findByDescricaoIgnoreCase(String descricao);

}
