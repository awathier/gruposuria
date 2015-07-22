package br.com.gruposuria.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.TypedQuery;

import br.com.gruposuria.constantes.SQL;
import br.com.gruposuria.entity.TurmaAluno;
import br.com.gruposuria.util.EditorDeConsultaSQL;

public class TurmaAlunoDAO extends DAO<TurmaAluno> {
	public TurmaAlunoDAO() {
	}

	@Override
	protected Class<TurmaAluno> novoTipoDeObjetoPersistente() {
		return TurmaAluno.class;
	}
	
	public List<TurmaAluno> listar(TurmaAluno TurmaAluno) {
		
		List<TurmaAluno> lista = new ArrayList<TurmaAluno>();
		//List<Object> parametros = new ArrayList<Object>();
		Map<String, Object> campos = new HashMap<String, Object>();
		String sql = SQL.getConsultaTURMAALUNO();
		EditorDeConsultaSQL editor = new EditorDeConsultaSQL(sql,campos);
		
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_CODIGO_TURMA_ALUNO, TurmaAluno.getCodigo(), "codigo");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_CODIGO_TURMA_TURMA_ALUNO, TurmaAluno.getTurma().getCodigo(), "codigoTurma");
		editor.adicionarOrdenacaoConsulta(SQL.ORDENAR_POR_ID_TURMA_ALUNO_DECRESCENTE);
		sql = editor.getSql();
		//parametros = editor.getParams();
		campos = editor.getCampos();
		Set<String> chaves = campos.keySet();
		TypedQuery<TurmaAluno> query = getEntityManager().createQuery(sql, TurmaAluno.class);
		for (String chave : chaves) {  
            if(chave != null)  
                //System.out.println(chave + campos.get(chave));
            	query.setParameter(chave, campos.get(chave));
        	}  
		
		return query.getResultList();
	}
	
	public List<TurmaAluno> listaTodos(){
		TypedQuery<TurmaAluno> query = getEntityManager().createNamedQuery("TurmaAluno.listaTodos", TurmaAluno.class);

		List<TurmaAluno> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;	
	}

	public TurmaAluno consultarPorCodigo(long id){
		TypedQuery<TurmaAluno> query = getEntityManager().createNamedQuery("TurmaAluno.consultaPorCodigo", TurmaAluno.class);
		query.setParameter("codigo", id);

		TurmaAluno turma = null;
		try{
			turma = (TurmaAluno)query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return turma;	
	}

	public List<TurmaAluno> consultaPorNomeCurso(String nomeCurso){
		TypedQuery<TurmaAluno> query = getEntityManager().createNamedQuery("TurmaAluno.consultaPorNomeCurso", TurmaAluno.class);
		query.setParameter("nome", "%"+ nomeCurso +"%");

		List<TurmaAluno> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}

	public List<TurmaAluno> consultaPorNomeTurmaAluno(String nomeTurmaAluno){
		TypedQuery<TurmaAluno> query = getEntityManager().createNamedQuery("TurmaAluno.consultaPorNomeTurmaAluno", TurmaAluno.class);
		query.setParameter("nome", "%"+ nomeTurmaAluno +"%");

		List<TurmaAluno> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}

	public List<TurmaAluno> consultaPorCidade(String nomeCidade){
		TypedQuery<TurmaAluno> query = getEntityManager().createNamedQuery("TurmaAluno.consultaPorCidade", TurmaAluno.class);
		query.setParameter("cidade", "%"+ nomeCidade +"%");

		List<TurmaAluno> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}
	
	public List<TurmaAluno> consultaPorUf(String uf){
		TypedQuery<TurmaAluno> query = getEntityManager().createNamedQuery("TurmaAluno.consultaPorUf", TurmaAluno.class);
		query.setParameter("uf", "%"+ uf +"%");

		List<TurmaAluno> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}
	
	public List<TurmaAluno> consultaPorData(String data){
		TypedQuery<TurmaAluno> query = getEntityManager().createNamedQuery("TurmaAluno.consultaPorData", TurmaAluno.class);
		query.setParameter("data", data);

		List<TurmaAluno> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}
	
}
