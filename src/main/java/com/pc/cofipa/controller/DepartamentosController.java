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
import com.pc.cofipa.model.Departamento;
import com.pc.cofipa.repository.Departamentos;
import com.pc.cofipa.repository.filter.DepartamentoFilter;
import com.pc.cofipa.service.CadastroDepartamentoService;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.service.exception.NomeDepartamentoJaCadastradoException;


@Controller
@RequestMapping("/departamentos")
public class DepartamentosController {
	
	@Autowired
	private CadastroDepartamentoService cadastroDepartamentoService;
	
	@Autowired
	private Departamentos departamentos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Departamento departamento) {
		return new ModelAndView ("departamento/CadastroDepartamento");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(departamento);
		}
			try {
				cadastroDepartamentoService.salvar(departamento);
			} catch (NomeDepartamentoJaCadastradoException e) {
				result.rejectValue("nome", e.getMessage(), e.getMessage());
				return novo(departamento);
			
		}
		
		attributes.addFlashAttribute("mensagem", "Departamento salvo com sucesso");
		return new ModelAndView("redirect:/departamentos/novo");
	}
		@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
		public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Departamento departamento, BindingResult result) {
			if (result.hasErrors()) {
				return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
			}
			
			departamento = cadastroDepartamentoService.salvar(departamento);
			return ResponseEntity.ok(departamento);
		}
		
		@GetMapping
		public ModelAndView pesquisar(DepartamentoFilter departamentoFilter, BindingResult result
				, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
			ModelAndView mv = new ModelAndView("departamento/PesquisaDepartamentos");
			
			PageWrapper<Departamento> paginaWrapper = new PageWrapper<>(departamentos.filtrar(departamentoFilter, pageable)
					, httpServletRequest);
			mv.addObject("pagina", paginaWrapper);
			return mv;
		}
		
		@GetMapping("/{codigo}")
		public ModelAndView editar(@PathVariable Long codigo) {
			Departamento departamento = departamentos.findOne(codigo);
			ModelAndView mv = novo(departamento);
			mv.addObject(departamento);
			return mv;
		}
		
		@DeleteMapping("/{codigo}")
		public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Departamento departamento){
			try{
			cadastroDepartamentoService.excluir(departamento);
			}catch(ImpossivelExcluirEntidadeException e){
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok().build();
		}
		
	}
