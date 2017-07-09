package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.PatrimonioInformatica;
import com.pc.cofipa.repository.helper.patrimonioInformatica.PatrimoniosInformaticaQueries;

@Repository
public interface PatrimoniosInformatica extends JpaRepository<PatrimonioInformatica, Long>, PatrimoniosInformaticaQueries {

	Optional<PatrimonioInformatica> findByNumeroIgnoreCase(String numero);

}
