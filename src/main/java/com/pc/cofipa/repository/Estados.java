package com.pc.cofipa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Estado;


@Repository
public interface Estados extends JpaRepository<Estado, Long> {

}
