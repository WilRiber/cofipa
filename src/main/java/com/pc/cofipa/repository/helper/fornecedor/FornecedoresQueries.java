package com.pc.cofipa.repository.helper.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Fornecedor;
import com.pc.cofipa.repository.filter.FornecedorFilter;

public interface FornecedoresQueries {

	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable);

}
