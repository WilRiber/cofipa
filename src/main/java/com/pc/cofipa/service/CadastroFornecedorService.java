package com.pc.cofipa.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Fornecedor;
import com.pc.cofipa.repository.Fornecedores;
import com.pc.cofipa.service.exception.CpfCnpjFornecedorJaCadastradoException;


@Service
public class CadastroFornecedorService {
	
	
	@Autowired
	private Fornecedores fornecedores;
	
	
	@Transactional
	public void salvar(Fornecedor fornecedor) {
		
		Optional<Fornecedor> fornecedorOptional = fornecedores.findByCpfOuCnpj(fornecedor.getCpfOuCnpjSemFormatacao());
		if(fornecedorOptional.isPresent()) {
			throw new CpfCnpjFornecedorJaCadastradoException("CPF/CNPJ do fornecedor já cadastrado");
		}
		
		fornecedores.save(fornecedor);
	}

}