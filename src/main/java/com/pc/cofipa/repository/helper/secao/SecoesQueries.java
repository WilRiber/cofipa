package com.pc.cofipa.repository.helper.secao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Secao;
import com.pc.cofipa.repository.filter.SecaoFilter;
;

public interface SecoesQueries {
	
	public Page<Secao> filtrar(SecaoFilter filtro, Pageable pageable);

}
