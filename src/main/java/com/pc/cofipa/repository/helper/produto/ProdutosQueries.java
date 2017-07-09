package com.pc.cofipa.repository.helper.produto;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Produto;
import com.pc.cofipa.repository.filter.ProdutoFilter;

public interface ProdutosQueries {
	
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);

}
