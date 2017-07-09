package com.pc.cofipa.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pc.cofipa.service.exception.NomeCidadeJaCadastradaException;
import com.pc.cofipa.service.exception.NomeDepartamentoJaCadastradoException;
import com.pc.cofipa.service.exception.NomeDivisaoJaCadastradoException;
import com.pc.cofipa.service.exception.NomeFornecedorJaCadastradoException;
import com.pc.cofipa.service.exception.NomeSecaoJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(NomeFornecedorJaCadastradoException.class)
	public ResponseEntity<String> handleNomeFornecedorJaCadastradoException(NomeFornecedorJaCadastradoException e){
		
		return ResponseEntity.badRequest().body(e.getMessage());
		
	}
	
	@ExceptionHandler(NomeDepartamentoJaCadastradoException.class)
	public ResponseEntity<String> handleNomeDepartamentoJaCadastradoException(NomeDepartamentoJaCadastradoException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(NomeDivisaoJaCadastradoException.class)
	public ResponseEntity<String> handleNomeDivisaoJaCadastradoException(NomeDivisaoJaCadastradoException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(NomeSecaoJaCadastradoException.class)
	public ResponseEntity<String> handleNomeSecaoJaCadastradoException(NomeSecaoJaCadastradoException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(NomeCidadeJaCadastradaException.class)
	public ResponseEntity<String> handleNomeCidadeJaCadastradaException(NomeCidadeJaCadastradaException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
