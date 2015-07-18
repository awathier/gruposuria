package br.com.gruposuria.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.gruposuria.constantes.SQL;
import br.com.gruposuria.entity.Instituicao;
import br.com.gruposuria.util.EditorDeConsultaSQL;

public class InstituicaoDAO extends DAO<Instituicao> {
	public InstituicaoDAO() {
	}

	@Override
	protected Class<Instituicao> novoTipoDeObjetoPersistente() {
		return Instituicao.class;
	}
	
	public List<Instituicao> listar(Instituicao instituicao) {
		
		//List<Object> parametros = new ArrayList<Object>();
		Map<String, Object> campos = new HashMap<String, Object>();
		String sql = SQL.getConsultaINTITUICAO();
		EditorDeConsultaSQL editor = new EditorDeConsultaSQL(sql,campos);
		
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_CODIGO_INSTITUICAO, instituicao.getCodigo(), "codigo");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_CNPJ_INSTITUICAO, instituicao.getCnpj(), "cnpj");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_NOME_INSTITUICAO_LIKE, instituicao.getNome(), "nome");
		editor.adicionarOrdenacaoConsulta(SQL.ORDENAR_POR_ID_INSTITUICAO_DECRESCENTE);
		sql = editor.getSql();
		//parametros = editor.getParams();
		campos = editor.getCampos();
		Set<String> chaves = campos.keySet();
		Query query = getEntityManager().createQuery(sql);
		for (String chave : chaves) {  
            if(chave != null)  
                //System.out.println(chave + campos.get(chave));
            	query.setParameter(chave, campos.get(chave));
        	}  
		
		return query.getResultList();
	}

	public Instituicao consultarPorCodigo(Long id){
		TypedQuery<Instituicao> query = getEntityManager().createNamedQuery("Instituicao.consultaPorCodigo", Instituicao.class);
		query.setParameter("codigo", id);

		Instituicao instituicao = null;
		try{
			instituicao = query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return instituicao;	
	}

	public Instituicao consultarPorCnpj(String cnpj){
		TypedQuery<Instituicao> query = getEntityManager().createNamedQuery("Instituicao.consultaPorCnpj", Instituicao.class);
		query.setParameter("cnpj", cnpj);

		Instituicao curso = null;
		try{
			curso = (Instituicao)query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return curso;	
	}

	public List<Instituicao> consultarPorNome(String nome){
		TypedQuery<Instituicao> query = getEntityManager().createNamedQuery("Instituicao.consultaPorNome", Instituicao.class);
		query.setParameter("nome", "%"+ nome +"%");

		List<Instituicao> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}
	
	public List<Instituicao> listaTodos(){
		TypedQuery<Instituicao> query = getEntityManager().createNamedQuery("Instituicao.listaTodos", Instituicao.class);

		List<Instituicao> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;	
	}

}
