package br.com.leonardops.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.leonardops.domain.Cliente;
import br.com.leonardops.domain.Profissao;
import br.com.leonardops.domain.UF;
import br.com.leonardops.service.ClienteService;
import br.com.leonardops.service.ProfissaoService;

@Controller
@RequestMapping ("/clientes")

public class ClienteController {
	
	
	@Autowired
	private ClienteService clinservice;
	
	@Autowired
	private ProfissaoService profservice;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "/cliente/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("clientes", clinservice.buscarTodos());
		return "/cliente/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Cliente cliente, RedirectAttributes attr) {
		clinservice.salvar(cliente);
		attr.addFlashAttribute("success",  "Cliente inserido com sucesso.");
		return "redirect:/clientes/cadastrar";

}
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", clinservice.buscarPorId(id));
		return "/cliente/cadastro";
		
	}
	
	@PostMapping("/editar")
	public String editar(Cliente cliente, RedirectAttributes attr) {
		clinservice.editar(cliente);
		attr.addFlashAttribute("success",  "Cliente alterado com sucesso ");
		return "redirect:/clientes/cadastrar";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		clinservice.excluir(id);
		attr.addFlashAttribute("success",  "Cliente excluido com sucesso. ");
		return "redirect:/clientes/listar";
		
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("clientes", clinservice.buscarPorNome(nome));
		return "/cliente/lista";
	}
	
	@ModelAttribute("profissoes")
	public List<Profissao> getProfissoes(){
		return profservice.buscarTodos();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs(){
		return UF.values();
	}
}


