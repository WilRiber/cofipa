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

import com.pc.cofipa.model.ItemMaterialInformatica;
import com.pc.cofipa.repository.ItensMateriaisInformatica;

import com.pc.cofipa.repository.filter.ItemMaterialInformaticaFilter;
import com.pc.cofipa.service.CadastroItemMaterialInformaticaService;
import com.pc.cofipa.service.exception.DescricaoItemMaterialInformaticaJaCadastradoException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;


@Controller
@RequestMapping("/itensMateriaisInformatica")
public class ItensMateriaisInformaticaController {
	
	@Autowired
	private CadastroItemMaterialInformaticaService cadastroItemMaterialInformaticaService;
	
	@Autowired
	private ItensMateriaisInformatica itensMateriaisInformatica;
	
	@RequestMapping("/novo")
	public ModelAndView novo(ItemMaterialInformatica itemMaterialInformatica){
		return new ModelAndView ("itensMateriaisInformatica/CadastroItensMateriaisInformatica");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid ItemMaterialInformatica itemMaterialInformatica, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(itemMaterialInformatica);
		}
		
		try {
		cadastroItemMaterialInformaticaService.salvar(itemMaterialInformatica);
		} catch (DescricaoItemMaterialInformaticaJaCadastradoException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			return novo(itemMaterialInformatica);
		}
		attributes.addFlashAttribute("mensagem", "Item  salvo com sucesso!");
		return new ModelAndView("redirect:/itensMateriaisInformatica/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid  ItemMaterialInformatica itemMaterialInformatica, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
		
		itemMaterialInformatica = cadastroItemMaterialInformaticaService.salvar(itemMaterialInformatica);
		return ResponseEntity.ok(itemMaterialInformatica);
	}
	
	@GetMapping
	public ModelAndView pesquisar(ItemMaterialInformaticaFilter itemMaterialInformaticaFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("itensMateriaisInformatica/PesquisaItensMateriaisInformatica");
		
		PageWrapper<ItemMaterialInformatica> paginaWrapper = new PageWrapper<>(itensMateriaisInformatica.filtrar(itemMaterialInformaticaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		ItemMaterialInformatica itemMaterialInformatica = itensMateriaisInformatica.findOne(codigo);
		ModelAndView mv = novo(itemMaterialInformatica);
		mv.addObject(itemMaterialInformatica);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") ItemMaterialInformatica itemMaterialInformatica){
		try{
		cadastroItemMaterialInformaticaService.excluir(itemMaterialInformatica);
		}catch(ImpossivelExcluirEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}