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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.leonardops.domain.Empresa;
import br.com.leonardops.domain.Profissao;
import br.com.leonardops.service.EmpresaService;
import br.com.leonardops.service.ProfissaoService;

@Controller
@RequestMapping ("/profissoes")

public class ProfissaoController {
	
	@Autowired
	private ProfissaoService profservice;
	@Autowired
	private EmpresaService empservice;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Profissao profissao) {
		return "/profissao/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("profissoes", profservice.buscarTodos());
		return "/profissao/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Profissao profissao, RedirectAttributes attr) {
		profservice.salvar(profissao);
		attr.addFlashAttribute("success",  "Profissão inserida com sucesso.");
		return "redirect:/profissoes/cadastrar";

}
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissao", profservice.buscarPorId(id));
		return "/profissao/cadastro";
		
	}
	
	@PostMapping("/editar")
	public String editar(Profissao profissao, RedirectAttributes attr) {
		profservice.editar(profissao);
		attr.addFlashAttribute("success",  "Profissão alterada com sucesso ");
		return "redirect:/profissoes/cadastrar";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if(profservice.profissaoTemclientes(id)) {
			attr.addFlashAttribute("fail",  "Profissão não removida. Possui Cliente(s) vinculado(s). ");
			
		} else {
			profservice.excluir(id);
			attr.addFlashAttribute("success",  "Profissão excluida com sucesso ");
		}
		
		
		return "redirect:/profissoes/listar";
		
	}
	
	@ModelAttribute("empresas")
	public List<Empresa> listaEmpresas(){
		return empservice.buscarTodos();
	}
}
