package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Uge;
import com.pc.cofipa.repository.helper.uge.UgesQueries;

@Repository
public interface Uges extends JpaRepository<Uge, Long>, UgesQueries {

	Optional<Uge> findByNomeIgnoreCase(String nome);

}
