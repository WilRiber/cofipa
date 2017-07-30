package com.pc.cofipa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pc.cofipa.controller.page.PageWrapper;

import com.pc.cofipa.model.PatrimonioMobiliario;
import com.pc.cofipa.repository.Andares;
import com.pc.cofipa.repository.Departamentos;
import com.pc.cofipa.repository.Divisoes;
import com.pc.cofipa.repository.ItensMateriaisMobiliario;
import com.pc.cofipa.repository.MateriaisMobiliario;
import com.pc.cofipa.repository.PatrimoniosMobiliario;
import com.pc.cofipa.repository.Secoes;

import com.pc.cofipa.repository.Tipos;
import com.pc.cofipa.repository.Uges;
import com.pc.cofipa.repository.filter.PatrimonioMobiliarioFilter;
import com.pc.cofipa.service.CadastroPatrimonioMobiliarioService;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/patrimonioMobiliario")
public class PatrimoniosMobiliarioController {
	
	@Autowired
	private CadastroPatrimonioMobiliarioService cadastroPatrimonioMobiliarioService;
	
	@Autowired
	private Uges uges;
	
	@Autowired
	private ItensMateriaisMobiliario itensMateriaisMobiliario;
	
	@Autowired
	private MateriaisMobiliario materiaisMobiliario;
	
	@Autowired
	private Departamentos departamentos;
	
	@Autowired
	private Andares andares;
	
	@Autowired
	private Tipos tipos;
	
	@Autowired
	private Divisoes divisoes;
	
	@Autowired
	private Secoes secoes;
	
	@Autowired
	private PatrimoniosMobiliario patrimoniosMobiliario;
	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(PatrimonioMobiliario patrimonioMobiliario) {
		ModelAndView mv = new ModelAndView("mobiliario/CadastroPatrimonioMobiliario");
		mv.addObject("uges", uges.findAll());
		mv.addObject("itensMateriaisMobiliario", itensMateriaisMobiliario.findAll());
		mv.addObject("departamentos", departamentos.findAll());
		mv.addObject("divisoes", divisoes.findAll());
		mv.addObject("secoes", secoes.findAll());
		mv.addObject("andares", andares.findAll());
		mv.addObject("tipos", tipos.findAll());	
		return  mv;
	}
	@RequestMapping(value = {"/novo", "{\\d+}"}, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid PatrimonioMobiliario patrimonioMobiliario, BindingResult result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){
			//throw new RuntimeException();
			return novo(patrimonioMobiliario);
		}
		
		cadastroPatrimonioMobiliarioService.salvar(patrimonioMobiliario);
		attributes.addFlashAttribute("mensagem","Patrimônio  salvo com sucesso!");
		return new ModelAndView("redirect:/patrimonioMobiliario/novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(PatrimonioMobiliarioFilter patrimonioMobiliarioFilter, BindingResult result
			, @PageableDefault(size = 5)Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("mobiliario/PesquisaPatrimoniosMobiliario");
		mv.addObject("uges", uges.findAll());
		mv.addObject("itensMateriaisMobiliario", itensMateriaisMobiliario.findAll());
		mv.addObject("materiaisMobiliario", materiaisMobiliario.findAll());
		mv.addObject("departamentos", departamentos.findAll());
		mv.addObject("divisoes", divisoes.findAll());
		mv.addObject("secoes", secoes.findAll());
		mv.addObject("andares", andares.findAll());
        mv.addObject("tipos", tipos.findAll());	
		
		PageWrapper<PatrimonioMobiliario> paginaWrapper = new PageWrapper<>(patrimoniosMobiliario.filtrar(patrimonioMobiliarioFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") PatrimonioMobiliario patrimonioMobiliario) {
		try {
		cadastroPatrimonioMobiliarioService.excluir(patrimonioMobiliario);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") PatrimonioMobiliario patrimonioMobiliario) {
		ModelAndView mv = novo(patrimonioMobiliario);
		mv.addObject(patrimonioMobiliario);
		
		return mv;
	}

}