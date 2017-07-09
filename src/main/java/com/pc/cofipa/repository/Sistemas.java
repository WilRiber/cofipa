package com.pc.cofipa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Sistema;

@Repository
public interface Sistemas extends JpaRepository<Sistema, Long>{

}
