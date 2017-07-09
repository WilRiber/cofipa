package com.pc.cofipa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.PatrimonioInformatica;

import com.pc.cofipa.repository.PatrimoniosInformatica;

import com.pc.cofipa.service.exception.NumeroPatrimonioInformaticaJaCadastradoException;


@Service
public class CadastroPatrimonioInformaticaService {

	@Autowired
	private PatrimoniosInformatica patrimoniosInformatica;
	
	
	@Transactional
	public void salvar(PatrimonioInformatica patrimonioInformatica) {
		
		Optional<PatrimonioInformatica> patrimonioInformaticaOptional = patrimoniosInformatica.findByNumeroIgnoreCase(patrimonioInformatica.getNumero());
		if(patrimonioInformaticaOptional.isPresent()) {
			throw new NumeroPatrimonioInformaticaJaCadastradoException("Numero do patrimônio já cadastrado");
		}
		
		patrimoniosInformatica.save(patrimonioInformatica);
	}

}
