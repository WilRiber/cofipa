package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Tipo;
import com.pc.cofipa.repository.helper.tipo.TiposQueries;

@Repository
public interface Tipos extends JpaRepository<Tipo, Long>, TiposQueries {

	Optional<Tipo> findByDescricaoIgnoreCase(String descricao);

}
