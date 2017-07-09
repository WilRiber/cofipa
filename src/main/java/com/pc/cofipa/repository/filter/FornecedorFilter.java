package com.pc.cofipa.repository.filter;

import com.pc.cofipa.model.TipoPessoa;

public class FornecedorFilter {
	
	private String nome;
	private String cpfOuCnpj;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	
	public Object getCpfOuCnpjSemFormatacao(){
		return TipoPessoa.removerFormatacao(this.cpfOuCnpj);
	}

}
