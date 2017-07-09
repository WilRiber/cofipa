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

import com.pc.cofipa.model.Unidade;
import com.pc.cofipa.repository.Unidades;

import com.pc.cofipa.repository.filter.UnidadeFilter;
import com.pc.cofipa.service.CadastroUnidadeService;
import com.pc.cofipa.service.exception.DescricaoUnidadeJaCadastradaException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/unidades")
public class UnidadesController {
	
	@Autowired
	private CadastroUnidadeService cadastroUnidadeService;
	
	@Autowired
	private Unidades unidades;
	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Unidade unidade) {
		return new ModelAndView("unidade/CadastroUnidade");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Unidade unidade, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(unidade);
		}
		
		try {
		cadastroUnidadeService.salvar(unidade);
		} catch (DescricaoUnidadeJaCadastradaException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			return novo(unidade);
		}
		attributes.addFlashAttribute("mensagem", "Unidade salva com sucesso!");
		return new ModelAndView("redirect:/unidades/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Unidade unidade, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
		
		unidade = cadastroUnidadeService.salvar(unidade);
		return ResponseEntity.ok(unidade);
	}
	@GetMapping
	public ModelAndView pesquisar(UnidadeFilter unidadeFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("unidade/PesquisaUnidades");
		
		PageWrapper<Unidade> paginaWrapper = new PageWrapper<>(unidades.filtrar(unidadeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Unidade unidade = unidades.findOne(codigo);
		ModelAndView mv = novo(unidade);
		mv.addObject(unidade);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Unidade unidade){
		try{
		cadastroUnidadeService.excluir(unidade);
		}catch(ImpossivelExcluirEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}
