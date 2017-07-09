                                                                                                                                                                                     package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Departamento;
import com.pc.cofipa.repository.helper.departamento.DepartamentosQueries;

@Repository
public interface Departamentos extends JpaRepository<Departamento, Long>, DepartamentosQueries{

	Optional<Departamento> findByNomeIgnoreCase(String nome);

}
                                                                      