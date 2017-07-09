package com.pc.cofipa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pc.cofipa.controller.page.PageWrapper;

import com.pc.cofipa.model.Uge;

import com.pc.cofipa.repository.Uges;

import com.pc.cofipa.repository.filter.UgeFilter;

import com.pc.cofipa.service.CadastroUgeService;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

import com.pc.cofipa.service.exception.NomeUgeJaCadastradoException;

@Controller
@RequestMapping("/uges")
public class UgesController {
	
	@Autowired
	private CadastroUgeService cadastroUgeService;
	
	@Autowired
	private Uges uges;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Uge uge){
		return new ModelAndView ("uge/CadastroUge");
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Uge uge, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(uge);
		}
		
		try {
		cadastroUgeService.salvar(uge);
		} catch (NomeUgeJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(uge);
		}
		attributes.addFlashAttribute("mensagem", "Uge salvo com sucesso!");
		return new ModelAndView("redirect:/uges/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Uge uge, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		uge = cadastroUgeService.salvar(uge);
		return ResponseEntity.ok(uge);
	}
	
	@GetMapping
	public ModelAndView pesquisar(UgeFilter ugeFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("uge/PesquisaUges");
		
		PageWrapper<Uge> paginaWrapper = new PageWrapper<>(uges.filtrar(ugeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Uge uge = uges.findOne(codigo);
		ModelAndView mv = novo(uge);
		mv.addObject(uge);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Uge uge){
		try{
		cadastroUgeService.excluir(uge);
		}catch(ImpossivelExcluirEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}