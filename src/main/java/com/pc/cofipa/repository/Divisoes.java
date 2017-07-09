package com.pc.cofipa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Departamento;
import com.pc.cofipa.model.Divisao;
import com.pc.cofipa.repository.helper.divisao.DivisoesQueries;

@Repository
public interface Divisoes extends JpaRepository<Divisao, Long>, DivisoesQueries {

	public Optional<Divisao> findByNomeIgnoreCase(String nome);

	public Optional<Divisao> findByNomeAndDepartamento(String nome, Departamento departamento);

	public List<Divisao> findByDepartamentoCodigo(Long codigoDepartamento);

}
