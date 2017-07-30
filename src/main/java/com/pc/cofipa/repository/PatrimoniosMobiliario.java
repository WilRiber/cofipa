package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.PatrimonioMobiliario;
import com.pc.cofipa.repository.helper.patrimonioMobiliario.PatrimoniosMobiliarioQueries;

@Repository
public interface PatrimoniosMobiliario extends JpaRepository<PatrimonioMobiliario, Long>, PatrimoniosMobiliarioQueries{

	Optional<PatrimonioMobiliario> findByNumeroIgnoreCase(String numero);
}
