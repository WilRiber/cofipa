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
import com.pc.cofipa.model.Tipo;

import com.pc.cofipa.repository.Tipos;
import com.pc.cofipa.repository.filter.TipoFilter;
import com.pc.cofipa.service.CadastroTipoService;
import com.pc.cofipa.service.exception.DescricaoTipoJaCadastradoException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/tipos")
public class TiposController {
	
	@Autowired
	private CadastroTipoService cadastroTipoService;
	
	@Autowired
	private Tipos tipos;
	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Tipo tipo) {
		return new ModelAndView("tipo/CadastroTipo");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Tipo tipo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tipo);
		}
		
		try {
		cadastroTipoService.salvar(tipo);
		} catch (DescricaoTipoJaCadastradoException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			return novo(tipo);
		}
		attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
		return new ModelAndView("redirect:/tipos/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Tipo tipo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
		
		tipo = cadastroTipoService.salvar(tipo);
		return ResponseEntity.ok(tipo);
	}
	@GetMapping
	public ModelAndView pesquisar(TipoFilter tipoFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("tipo/PesquisaTipos");
		
		PageWrapper<Tipo> paginaWrapper = new PageWrapper<>(tipos.filtrar(tipoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Tipo tipo = tipos.findOne(codigo);
		ModelAndView mv = novo(tipo);
		mv.addObject(tipo);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Tipo tipo){
		try{
		cadastroTipoService.excluir(tipo);
		}catch(ImpossivelExcluirEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}