package br.com.gruposuria.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.com.gruposuria.constantes.SQL;
import br.com.gruposuria.entity.Aluno;
import br.com.gruposuria.util.EditorDeConsultaSQL;

@Named
@RequestScoped
public class AlunoDAO extends DAO<Aluno> {
	
	public AlunoDAO() {

	}

	@Override
	protected Class<Aluno> novoTipoDeObjetoPersistente() {
		return Aluno.class;
	}
	
	public List<Aluno> listar(Aluno aluno) {
		
		//List<Object> parametros = new ArrayList<Object>();
		Map<String, Object> campos = new HashMap<String, Object>();
		String sql = SQL.getConsultaALUNO();
		EditorDeConsultaSQL editor = new EditorDeConsultaSQL(sql,campos);
		
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_CODIGO_ALUNO, aluno.getCodigo(), "codigo");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_CPF_ALUNO, aluno.getCpf(), "cpf");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_NOME_ALUNO_LIKE, aluno.getNome(), "nome");
		editor.adicionarOrdenacaoConsulta(SQL.ORDENAR_POR_ID_ALUNO_DECRESCENTE);
		sql = editor.getSql();
		//parametros = editor.getParams();
		campos = editor.getCampos();
		Set<String> chaves = campos.keySet();
//		Query query = getEntityManager().createQuery(sql);
		TypedQuery<Aluno> query = getEntityManager().createQuery(sql, Aluno.class);
		for (String chave : chaves) {  
            if(chave != null)  
                //System.out.println(chave + campos.get(chave));
            	query.setParameter(chave, campos.get(chave));
        	}  
		
		return query.getResultList();
	}

	public List<Aluno> listaTodos(){
		TypedQuery<Aluno> query = getEntityManager().createNamedQuery("Aluno.listaTodos", Aluno.class);

		List<Aluno> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;	
	}

	public Aluno consultarPorCpf(String cpf){
		TypedQuery<Aluno> query = getEntityManager().createNamedQuery("Aluno.consultaPorCpf", Aluno.class);
		query.setParameter("cpf", cpf);

		Aluno aluno = null;
		try{
			aluno = query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return aluno;	
	}
	
	public Aluno consultarPorNomeCpf(String nome, String cpf){
		TypedQuery<Aluno> query = getEntityManager().createNamedQuery("Aluno.consultaPorNomeCpf", Aluno.class);
		query.setParameter("nome", "%"+ nome +"%");
		query.setParameter("cpf", cpf);
		
		Aluno aluno = null;
		try{
			aluno = query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return aluno;	
	}
	
	public Aluno consultarPorCodigo(long id){
		TypedQuery<Aluno> query = getEntityManager().createNamedQuery("Aluno.consultaPorCodigo", Aluno.class);
		query.setParameter("codigo", id);

		Aluno aluno = null;
		try{
			aluno = query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return aluno;	
	}

	public List<Aluno> consultarPorNome(String nome){
		TypedQuery<Aluno> query = getEntityManager().createNamedQuery("Aluno.consultaPorNome", Aluno.class);
		query.setParameter("nome", "%"+ nome +"%");

		List<Aluno> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}

}
