package com.pc.cofipa.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pc.cofipa.controller.page.PageWrapper;

import com.pc.cofipa.model.Secao;
import com.pc.cofipa.repository.Divisoes;
import com.pc.cofipa.repository.Secoes;

import com.pc.cofipa.repository.filter.SecaoFilter;
import com.pc.cofipa.service.CadastroSecaoService;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

import com.pc.cofipa.service.exception.NomeSecaoJaCadastradoException;

@Controller
@RequestMapping("/secoes")
public class SecoesController {
	
	@Autowired
	private CadastroSecaoService cadastroSecaoService;
	
	@Autowired
	private Divisoes divisoes;
	
	@Autowired
	private Secoes secoes;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Secao secao) {
		ModelAndView mv = new ModelAndView("secao/CadastroSecao");
		mv.addObject("divisoes", divisoes.findAll());
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Secao> pesquisaPorCodigoDivisao(
		   @RequestParam(name = "divisao", defaultValue = "-1" )Long codigoDivisao) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		return secoes.findByDivisaoCodigo(codigoDivisao);
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Secao secao, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(secao);
		}
		
		try {
			cadastroSecaoService.salvar(secao);
		} catch (NomeSecaoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(secao);
		
	}
		
		attributes.addFlashAttribute("mensagem", "Secao salva com sucesso");
		return new ModelAndView("redirect:/secoes/novo");
	}
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Secao secao, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		secao = cadastroSecaoService.salvar(secao);
		return ResponseEntity.ok(secao);
	}
	
	@GetMapping
	public ModelAndView pesquisar(SecaoFilter secaoFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("secao/PesquisaSecoes");
		mv.addObject("divisoes", divisoes.findAll());
		
		PageWrapper<Secao> paginaWrapper = new PageWrapper<>(secoes.filtrar(secaoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Secao secao = secoes.findOne(codigo);
		ModelAndView mv = nova(secao);
		mv.addObject(secao);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Secao secao){
		try {
			cadastroSecaoService.excluir(secao);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}