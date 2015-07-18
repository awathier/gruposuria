package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.EstadoDAO;
import br.com.gruposuria.entity.Estado;

@SuppressWarnings("serial")
@Stateless
public class EstadoModel implements Serializable {

	@Inject
	private EstadoDAO cursoDAO;
	
	public List<Estado> listaEstados(){
		return cursoDAO.listaTodos();
	}
	
}
