package com.pc.cofipa.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.Fornecedor;
import com.pc.cofipa.repository.helper.fornecedor.FornecedoresQueries;

@Repository
public interface Fornecedores extends JpaRepository<Fornecedor, Long>, FornecedoresQueries {
	
	public Optional<Fornecedor> findByCpfOuCnpj(String cpfOuCnpj);

}
