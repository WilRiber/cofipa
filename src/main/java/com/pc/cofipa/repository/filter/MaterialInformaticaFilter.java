package com.pc.cofipa.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.pc.cofipa.model.Fornecedor;
import com.pc.cofipa.model.ItemMaterialInformatica;
import com.pc.cofipa.model.Unidade;

public class MaterialInformaticaFilter {
	
	private String descricao;
	private BigDecimal valorTotal;
	private BigDecimal valorUnitario;
	private int quantidade;
	private int critico;
	private String numeroNotaFiscal;
	private LocalDate dataEmissao;
	private LocalDate dataEntrada;
	private Fornecedor fornecedor;
	private Unidade unidade;
	private ItemMaterialInformatica itemMaterialInformatica;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getCritico() {
		return critico;
	}
	public void setCritico(int critico) {
		this.critico = critico;
	}
	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}
	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public LocalDate getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public ItemMaterialInformatica getItemMaterialInformatica() {
		return itemMaterialInformatica;
	}
	public void setItemMaterialInformatica(ItemMaterialInformatica itemMaterialInformatica) {
		this.itemMaterialInformatica = itemMaterialInformatica;
	}
	
	
	
	
	

}
