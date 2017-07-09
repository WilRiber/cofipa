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
import com.pc.cofipa.model.Divisao;
import com.pc.cofipa.repository.Departamentos;
import com.pc.cofipa.repository.Divisoes;
import com.pc.cofipa.repository.filter.DivisaoFilter;

import com.pc.cofipa.service.CadastroDivisaoService;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.service.exception.NomeDivisaoJaCadastradoException;






@Controller
@RequestMapping("/divisoes")
public class DivisaoController {
	
	@Autowired
	private CadastroDivisaoService cadastroDivisaoService;
	
	@Autowired
	private Divisoes divisoes;
	
	@Autowired
	private Departamentos departamentos;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Divisao divisao) {
		ModelAndView mv = new ModelAndView("divisao/CadastroDivisao");
		mv.addObject("departamentos", departamentos.findAll());
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Divisao> pesquisarPorCodigoDepartamento(
			@RequestParam(name = "departamento", defaultValue = "-1") Long codigoDepartamento) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {	}
		return divisoes.findByDepartamentoCodigo(codigoDepartamento);
		
	}
		@RequestMapping(value = "/novo", method = RequestMethod.POST)
		public ModelAndView cadastrar(@Valid Divisao divisao, BindingResult result, RedirectAttributes attributes) {
			if (result.hasErrors()) {
				return nova(divisao);
			}
			
			try {
				cadastroDivisaoService.salvar(divisao);
			} catch (NomeDivisaoJaCadastradoException e) {
				result.rejectValue("nome", e.getMessage(), e.getMessage());
				return nova(divisao);
			
		}
			
			attributes.addFlashAttribute("mensagem", "Divisao salva com sucesso");
			return new ModelAndView("redirect:/divisoes/nova");
		}
		@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
		public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Divisao divisao, BindingResult result) {
			if (result.hasErrors()) {
				return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
			}
			
			divisao = cadastroDivisaoService.salvar(divisao);
			return ResponseEntity.ok(divisao);
		}
		
		@GetMapping
		public ModelAndView pesquisar(DivisaoFilter divisaoFilter, BindingResult result
				, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
			ModelAndView mv = new ModelAndView("divisao/PesquisaDivisoes");
			mv.addObject("departamentos", departamentos.findAll());
			
			PageWrapper<Divisao> paginaWrapper = new PageWrapper<>(divisoes.filtrar(divisaoFilter, pageable)
					, httpServletRequest);
			mv.addObject("pagina", paginaWrapper);
			return mv;
		}
		
		@GetMapping("/{codigo}")
		public ModelAndView editar(@PathVariable Long codigo) {
			Divisao divisao = divisoes.findOne(codigo);
			ModelAndView mv = nova(divisao);
			mv.addObject(divisao);
			return mv;
		}
		
		@DeleteMapping("/{codigo}")
		public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Divisao divisao){
			try {
				cadastroDivisaoService.excluir(divisao);
			} catch (ImpossivelExcluirEntidadeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok().build();
		}
		
	}