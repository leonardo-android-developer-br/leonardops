package br.com.leonardops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leonardops.dao.EmpresaDao;
import br.com.leonardops.domain.Empresa;

@Service 
public class EmpresaServiceImpl implements EmpresaService {
	
	@Autowired
	private EmpresaDao dao;

	@Transactional(readOnly = false)
	@Override 
	public void salvar(Empresa empresa) {
		dao.save(empresa);
		
	}
	@Transactional(readOnly = false)
	@Override
	public void editar(Empresa empresa) {
		dao.update(empresa);
		
	}

	@Transactional(readOnly = false)@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Empresa buscarPorId(Long id) {
		return dao.findById(id);
		 
	}

	@Transactional(readOnly = true)
	@Override
	public List<Empresa> buscarTodos() {
		
		return dao.findAll();
	}
	@Override
	public boolean empresaTemProfissoes(Long id) {
		
		if(buscarPorId(id).getProfissoes().isEmpty()) {
		return false;
	}
	return true;
	}
}