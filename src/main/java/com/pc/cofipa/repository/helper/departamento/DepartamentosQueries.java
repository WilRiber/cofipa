package com.pc.cofipa.repository.helper.departamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Departamento;
import com.pc.cofipa.repository.filter.DepartamentoFilter;



public interface DepartamentosQueries {
	
	public Page<Departamento> filtrar(DepartamentoFilter filtro, Pageable pageable);

}
