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

import com.pc.cofipa.model.TipoSistema;

import com.pc.cofipa.repository.TipoSistemas;

import com.pc.cofipa.repository.filter.TipoSistemaFilter;

import com.pc.cofipa.service.CadastroTipoSistemaService;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

import com.pc.cofipa.service.exception.NomeSistemaJaCadastradoException;

@Controller
@RequestMapping("/tipoSistemas")
public class TipoSistemasController {

	@Autowired
	private CadastroTipoSistemaService cadastroTipoSistemaService;
	
	@Autowired
	private TipoSistemas tipoSistemas;
	
	@RequestMapping("/novo")
	public ModelAndView novo(TipoSistema tipoSistema){
		return new ModelAndView ("tipoSistema/CadastroTipoSistema");
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid TipoSistema tipoSistema, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tipoSistema);
		}
		
		try {
		cadastroTipoSistemaService.salvar(tipoSistema);
		} catch (NomeSistemaJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(tipoSistema);
		}
		attributes.addFlashAttribute("mensagem", "Tipo sistema salvo com sucesso!");
		return new ModelAndView("redirect:/tipoSistemas/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid TipoSistema tipoSistema, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		tipoSistema = cadastroTipoSistemaService.salvar( tipoSistema);
		return ResponseEntity.ok(tipoSistema);
	}
	
	@GetMapping
	public ModelAndView pesquisar(TipoSistemaFilter tipoSistemaFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("tipoSistema/PesquisaTipoSistemas");
		
		PageWrapper<TipoSistema> paginaWrapper = new PageWrapper<>(tipoSistemas.filtrar(tipoSistemaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		TipoSistema tipoSistema = tipoSistemas.findOne(codigo);
		ModelAndView mv = novo(tipoSistema);
		mv.addObject(tipoSistema);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") TipoSistema tipoSistema){
		try{
		cadastroTipoSistemaService.excluir(tipoSistema);
		}catch(ImpossivelExcluirEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}
