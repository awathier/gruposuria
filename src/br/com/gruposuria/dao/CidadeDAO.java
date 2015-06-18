package br.com.gruposuria.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.gruposuria.entity.Cidade;
import br.com.gruposuria.entity.Estado;

public class CidadeDAO extends DAO<Cidade> {
	public CidadeDAO() {
	}

	@Override
	protected Class<Cidade> novoTipoDeObjetoPersistente() {
		return Cidade.class;
	}
	
	public List<Cidade> listaTodos(){
		TypedQuery<Cidade> query = getEntityManager().createNamedQuery("Cidade.listaTodos", Cidade.class);

		List<Cidade> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;	
	}
	
	public List<Cidade> consultaCidadePorUf(Estado estado){
		
		List<Cidade> lista = null;
		
		TypedQuery<Cidade> query = getEntityManager().createNamedQuery("Cidade.consultaCidadePorUf", Cidade.class);
		query.setParameter("idEstado", estado.getIdEstado());

		try{
			lista = query.getResultList();	
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;	
	}
	
}
