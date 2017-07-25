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
import com.pc.cofipa.model.Produto;
import com.pc.cofipa.repository.Fornecedores;
import com.pc.cofipa.repository.Produtos;
import com.pc.cofipa.repository.Unidades;
import com.pc.cofipa.repository.filter.ProdutoFilter;
import com.pc.cofipa.service.CadastroProdutoService;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("produtos")
public class ProdutosController {
	
	//private static final Logger logger = LoggerFactory.getLogger(ProdutosController.class);
	
	@Autowired
	private Unidades unidades;
	
	@Autowired
	public Fornecedores fornecedores;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private Produtos produtos;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("Produto/CadastroProduto");
		mv.addObject("unidades", unidades.findAll());
		mv.addObject("fornecedores", fornecedores.findAll());
		return mv;
	}
	
	@RequestMapping(value = {"/novo", "{\\d+}"}, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){
			//throw new RuntimeException();
			return novo(produto);
		}
		
		
		cadastroProdutoService.salvar(produto);
		//System.out.println(">>>Fornecedor: " + produto.getFornecedor());
		attributes.addFlashAttribute("mensagem","Produto salvo com sucesso!");
		return new ModelAndView("redirect:/produtos/novo");
				
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProdutoFilter produtoFilter, BindingResult result
			, @PageableDefault(size = 5)Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("Produto/PesquisaProdutos");
		mv.addObject("unidades", unidades.findAll());
		mv.addObject("fornecedores", fornecedores.findAll());
		
		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(produtos.filtrar(produtoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Produto produto) {
		try {
		cadastroProdutoService.excluir(produto);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Produto produto) {
		ModelAndView mv = novo(produto);
		mv.addObject(produto);
		
		return mv;
	}
	

}
