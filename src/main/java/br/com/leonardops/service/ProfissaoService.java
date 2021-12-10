package br.com.leonardops.service;

import java.util.List;

import br.com.leonardops.domain.Profissao;

public interface ProfissaoService {
	
void salvar (Profissao profissao);
	
	void editar (Profissao profissao);
	
	void excluir (Long id);
	
	Profissao buscarPorId(Long id);
	
	List<Profissao> buscarTodos();

	boolean profissaoTemclientes(Long id);

}
