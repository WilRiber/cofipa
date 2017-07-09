package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Andar;
import com.pc.cofipa.repository.helper.andar.AndaresQueries;

@Repository
public interface Andares extends JpaRepository<Andar, Long>, AndaresQueries{

	Optional<Andar> findByNomeIgnoreCase(String nome);

}
