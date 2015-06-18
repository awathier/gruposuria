package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.CidadeDAO;
import br.com.gruposuria.entity.Cidade;
import br.com.gruposuria.entity.Estado;

@SuppressWarnings("serial")
@Stateless
public class CidadeModel implements Serializable {

	@Inject
	private CidadeDAO cidadeDAO;
	
	public List<Cidade> listaCidades(){
		return cidadeDAO.listaTodos();
	}
	
	public List<Cidade> consultaCidadePorUf(Estado estado){
		return cidadeDAO.consultaCidadePorUf(estado);
	}
	
}
