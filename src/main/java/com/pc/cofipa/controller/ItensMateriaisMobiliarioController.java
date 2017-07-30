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
import com.pc.cofipa.model.ItemMaterialMobiliario;
import com.pc.cofipa.repository.ItensMateriaisMobiliario;
import com.pc.cofipa.repository.filter.ItemMaterialMobiliarioFilter;
import com.pc.cofipa.service.CadastroItemMaterialMobiliarioService;
import com.pc.cofipa.service.exception.DescricaoItemMaterialMobiliarioJaCadastradoException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/itensMateriaisMobiliario")
public class ItensMateriaisMobiliarioController {

	@Autowired
	private CadastroItemMaterialMobiliarioService cadastroItemMaterialMobiliarioService;
	
	@Autowired
	private ItensMateriaisMobiliario itensMateriaisMobiliario;
	
	@RequestMapping("/novo")
	public ModelAndView novo(ItemMaterialMobiliario itemMaterialMobiliario){
		return new ModelAndView ("itensMateriaisMobiliario/CadastroItensMateriaisMobiliario");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid ItemMaterialMobiliario itemMaterialMobiliario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(itemMaterialMobiliario);
		}
		
		try {
		cadastroItemMaterialMobiliarioService.salvar(itemMaterialMobiliario);
		} catch (DescricaoItemMaterialMobiliarioJaCadastradoException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			return novo(itemMaterialMobiliario);
		}
		attributes.addFlashAttribute("mensagem", "Item  salvo com sucesso!");
		return new ModelAndView("redirect:/itensMateriaisMobiliario/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid  ItemMaterialMobiliario itemMaterialMobiliario, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
		
		itemMaterialMobiliario = cadastroItemMaterialMobiliarioService.salvar(itemMaterialMobiliario);
		return ResponseEntity.ok(itemMaterialMobiliario);
	}
	
	@GetMapping
	public ModelAndView pesquisar(ItemMaterialMobiliarioFilter itemMaterialMobiliarioFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("itensMateriaisMobiliario/PesquisaItensMateriaisMobiliario");
		
		PageWrapper<ItemMaterialMobiliario> paginaWrapper = new PageWrapper<>(itensMateriaisMobiliario.filtrar(itemMaterialMobiliarioFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		ItemMaterialMobiliario itemMaterialMobiliario = itensMateriaisMobiliario.findOne(codigo);
		ModelAndView mv = novo(itemMaterialMobiliario);
		mv.addObject(itemMaterialMobiliario);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") ItemMaterialMobiliario itemMaterialMobiliario){
		try{
		cadastroItemMaterialMobiliarioService.excluir(itemMaterialMobiliario);
		}catch(ImpossivelExcluirEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}