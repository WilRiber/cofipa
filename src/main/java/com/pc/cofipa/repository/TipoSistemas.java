package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc.cofipa.model.TipoSistema;
import com.pc.cofipa.repository.helper.tipoSistema.TipoSistemasQueries;

public interface TipoSistemas extends JpaRepository<TipoSistema, Long>, TipoSistemasQueries {

	Optional<TipoSistema> findByNomeIgnoreCase(String nome);

}
