package br.com.leonardops.dao;

import java.util.List;

import br.com.leonardops.domain.Cliente;

public interface ClienteDao {
	
void save (Cliente cliente);
	
	void update (Cliente cliente);
	
	void delete (Long id);
	
	Cliente findById(Long id);
	
	List<Cliente> findAll();

	List<Cliente> findByNome(String nome);

}
