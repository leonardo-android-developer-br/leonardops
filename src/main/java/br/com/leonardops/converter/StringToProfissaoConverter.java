package br.com.leonardops.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.leonardops.domain.Profissao;
import br.com.leonardops.service.ProfissaoService;

@Component
public class StringToProfissaoConverter implements Converter<String, Profissao> {

	@Autowired
	private ProfissaoService service;
	
	@Override
	public Profissao convert(String text) {
		if(text.isEmpty()) {
			
		return null;
	}
		Long id = Long.valueOf(text);
	return service.buscarPorId(id);

}
}
