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
import com.pc.cofipa.model.Andar;

import com.pc.cofipa.repository.Andares;
import com.pc.cofipa.repository.filter.AndarFilter;
import com.pc.cofipa.service.CadastroAndarService;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.service.exception.NomeAndarJaCadastradoException;

@Controller
@RequestMapping("/andares")
public class AndaresController {
	
	@Autowired
	private CadastroAndarService cadastroAndarService;
	
	@Autowired
	private Andares andares;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Andar andar){
		return new ModelAndView ("andar/CadastroAndar");
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Andar andar, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(andar);
		}
		
		try {
		cadastroAndarService.salvar(andar);
		} catch (NomeAndarJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(andar);
		}
		attributes.addFlashAttribute("mensagem", "Andar salvo com sucesso!");
		return new ModelAndView("redirect:/andares/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Andar andar, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		andar = cadastroAndarService.salvar(andar);
		return ResponseEntity.ok(andar);
	}
	
	@GetMapping
	public ModelAndView pesquisar(AndarFilter andarFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("andar/PesquisaAndares");
		
		PageWrapper<Andar> paginaWrapper = new PageWrapper<>(andares.filtrar(andarFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Andar andar = andares.findOne(codigo);
		ModelAndView mv = novo(andar);
		mv.addObject(andar);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Andar andar){
		try{
		cadastroAndarService.excluir(andar);
		}catch(ImpossivelExcluirEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}
