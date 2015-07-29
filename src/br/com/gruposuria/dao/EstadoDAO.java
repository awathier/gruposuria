package br.com.gruposuria.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.gruposuria.entity.Estado;

public class EstadoDAO extends DAO<Estado> {
	public EstadoDAO() {
	}

	@Override
	protected Class<Estado> novoTipoDeObjetoPersistente() {
		return Estado.class;
	}
	
	public List<Estado> listaTodos(){
		TypedQuery<Estado> query = getEntityManager().createNamedQuery("Estado.listaTodos", Estado.class);

		List<Estado> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;	
	}
	
}
