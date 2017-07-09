package com.pc.cofipa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pc.cofipa.controller.page.PageWrapper;
import com.pc.cofipa.model.PatrimonioInformatica;

import com.pc.cofipa.repository.Andares;
import com.pc.cofipa.repository.Departamentos;
import com.pc.cofipa.repository.Divisoes;
import com.pc.cofipa.repository.ItensMateriaisInformatica;
import com.pc.cofipa.repository.MateriaisInformatica;
import com.pc.cofipa.repository.PatrimoniosInformatica;
import com.pc.cofipa.repository.Secoes;
import com.pc.cofipa.repository.TipoSistemas;
import com.pc.cofipa.repository.Tipos;
import com.pc.cofipa.repository.Uges;
import com.pc.cofipa.repository.filter.PatrimonioInformaticaFilter;

import com.pc.cofipa.service.CadastroPatrimonioInformaticaService;

@Controller
@RequestMapping("/patrimonioInformatica")
public class PatromoiosInformaticaController {
	
	@Autowired
	private CadastroPatrimonioInformaticaService cadastroPatrimonioInformaticaService;
	
	@Autowired
	private Uges uges;
	
	@Autowired
	private ItensMateriaisInformatica itensMateriaisInformatica;
	
	@Autowired
	private MateriaisInformatica materiaisInformatica;
	
	@Autowired
	private Departamentos departamentos;
	
	@Autowired
	private Andares andares;
	
	@Autowired
	private TipoSistemas tipoSistemas;
	
	@Autowired
	private Tipos tipos;
	
	@Autowired
	private Divisoes divisoes;
	
	@Autowired
	private Secoes secoes;
	
	@Autowired
	private PatrimoniosInformatica patrimoniosInformatica;
	
	@RequestMapping("/novo")
	public ModelAndView novo(PatrimonioInformatica patrimonioInformatica) {
		ModelAndView mv = new ModelAndView("informatica/CadastroPatrimonioInformatica");
		mv.addObject("uges", uges.findAll());
		mv.addObject("itensMateriaisInformatica", itensMateriaisInformatica.findAll());
		mv.addObject("departamentos", departamentos.findAll());
		mv.addObject("divisoes", divisoes.findAll());
		mv.addObject("secoes", secoes.findAll());
		mv.addObject("andares", andares.findAll());
		mv.addObject("tipoSistemas", tipoSistemas.findAll());
		mv.addObject("tipos", tipos.findAll());	
		return  mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid PatrimonioInformatica patrimonioInformatica, BindingResult result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){
			//throw new RuntimeException();
			return novo(patrimonioInformatica);
		}
		
		cadastroPatrimonioInformaticaService.salvar(patrimonioInformatica);
		attributes.addFlashAttribute("mensagem","Patrimônio  salvo com sucesso!");
		return new ModelAndView("redirect:/patrimonioInformatica/novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(PatrimonioInformaticaFilter patrimonioInformaticaFilter, BindingResult result
			, @PageableDefault(size = 5)Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("informatica/PesquisaPatrimoniosInformatica");
		mv.addObject("uges", uges.findAll());
		mv.addObject("itensMateriaisInformatica", itensMateriaisInformatica.findAll());
		mv.addObject("materiaisInformatica", materiaisInformatica.findAll());
		mv.addObject("departamentos", departamentos.findAll());
		mv.addObject("divisoes", divisoes.findAll());
		mv.addObject("secoes", secoes.findAll());
		mv.addObject("andares", andares.findAll());
		mv.addObject("tipoSistemas", tipoSistemas.findAll());
		mv.addObject("tipos", tipos.findAll());	
		
		PageWrapper<PatrimonioInformatica> paginaWrapper = new PageWrapper<>(patrimoniosInformatica.filtrar(patrimonioInformaticaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
		
}