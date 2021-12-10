package br.com.leonardops.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.leonardops.domain.Cliente;


@Repository
public class ClienteDaoImpl extends AbstractDao<Cliente, Long> implements ClienteDao {

	@Override
	public List<Cliente> findByNome(String nome) {
		
		return createQuery("select f from Cliente f where f.nome like concat('%',?1,'%')", nome);
	}
	
	
}
