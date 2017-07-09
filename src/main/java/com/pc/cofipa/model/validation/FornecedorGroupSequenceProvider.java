package com.pc.cofipa.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.pc.cofipa.model.Fornecedor;

public class FornecedorGroupSequenceProvider implements DefaultGroupSequenceProvider<Fornecedor>{

	@Override
	public List<Class<?>> getValidationGroups(Fornecedor fornecedor) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Fornecedor.class);
		
		if(isPessoaSelecionada(fornecedor)){
			grupos.add(fornecedor.getTipoPessoa().getGrupo());
		}
		
		return grupos;
	}

	private boolean isPessoaSelecionada(Fornecedor fornecedor) {
		return fornecedor != null && fornecedor.getTipoPessoa() != null;
	}

}
