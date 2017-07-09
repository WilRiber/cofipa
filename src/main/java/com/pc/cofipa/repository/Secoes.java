package com.pc.cofipa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc.cofipa.model.Divisao;
import com.pc.cofipa.model.Secao;
import com.pc.cofipa.repository.helper.secao.SecoesQueries;

public interface Secoes extends JpaRepository<Secao, Long>, SecoesQueries {
	
	public Optional<Secao> findByNomeIgnoreCase(String nome);
	
	public Optional<Secao> findByNomeAndDivisao(String nome, Divisao divisao);

	public List<Secao> findByDivisaoCodigo(Long codigoDivisao);
	



}
