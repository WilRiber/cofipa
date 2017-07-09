package com.pc.cofipa.repository.filter;

import java.math.BigDecimal;

import com.pc.cofipa.model.Fornecedor;
import com.pc.cofipa.model.Unidade;

public class ProdutoFilter {
	
	
	private String descricao;
	private Unidade unidade;
	private Fornecedor fornecedor;
	private BigDecimal valorDe;
	private BigDecimal valorAte;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public BigDecimal getValorDe() {
		return valorDe;
	}
	public void setValorDe(BigDecimal valorDe) {
		this.valorDe = valorDe;
	}
	public BigDecimal getValorAte() {
		return valorAte;
	}
	public void setValorAte(BigDecimal valorAte) {
		this.valorAte = valorAte;
	}
	
	
	

}
