package com.pc.cofipa.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.pc.cofipa.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.pc.cofipa.thymeleaf.processor.MenuAttributeTagProcessor;
import com.pc.cofipa.thymeleaf.processor.MessageElementTagProcessor;
import com.pc.cofipa.thymeleaf.processor.OrderElementTagProcessor;
import com.pc.cofipa.thymeleaf.processor.PaginationElementTagProcessor;


public class CofipaDialect extends AbstractProcessorDialect{
	
	public CofipaDialect(){
		super("pc Cofipa", "cofipa", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
	    final Set<IProcessor> processadores = new HashSet<>();
	    processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
	    processadores.add(new MessageElementTagProcessor(dialectPrefix));
	    processadores.add(new OrderElementTagProcessor(dialectPrefix));
	    processadores.add(new PaginationElementTagProcessor(dialectPrefix));
	    processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}