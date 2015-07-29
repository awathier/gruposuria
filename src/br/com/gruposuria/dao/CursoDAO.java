package br.com.gruposuria.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.gruposuria.entity.Aluno;
import br.com.gruposuria.entity.Curso;

public class CursoDAO extends DAO<Curso> {
	public CursoDAO() {
	}

	@Override
	protected Class<Curso> novoTipoDeObjetoPersistente() {
		return Curso.class;
	}
	
	public Curso consultarPorCodigo(long codigo){
		TypedQuery<Curso> query = getEntityManager().createNamedQuery("Curso.consultaPorCodigo", Curso.class);
		query.setParameter("codigo", codigo);

		Curso curso = null;
		try{
			curso = (Curso)query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return curso;
	}
	
	public Aluno consultarPorCpf(String cpf){
		TypedQuery<Aluno> query = getEntityManager().createNamedQuery("Aluno.consultaPorCpf", Aluno.class);
		query.setParameter("cpf", cpf);

		Aluno aluno = null;
		try{
			aluno = (Aluno)query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return aluno;	
	}

	public List<Curso> consultarPorNome(String nome){
		TypedQuery<Curso> query = getEntityManager().createNamedQuery("Curso.consultaPorNome", Curso.class);
		query.setParameter("nome", nome +"%");

		List<Curso> lista = null;
		try{
			lista = query.getResultList();
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}
	
	public List<Curso> listaTodos(){
		TypedQuery<Curso> query = getEntityManager().createNamedQuery("Curso.listaTodos", Curso.class);

		List<Curso> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;	
	}
	
}
