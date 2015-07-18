package br.com.gruposuria.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.gruposuria.constantes.SQL;
import br.com.gruposuria.entity.Instrutor;
import br.com.gruposuria.util.EditorDeConsultaSQL;

@Named
@RequestScoped
public class InstrutorDAO extends DAO<Instrutor> {
	
	public InstrutorDAO() {
	}

	@Override
	protected Class<Instrutor> novoTipoDeObjetoPersistente() {
		return Instrutor.class;
	}
	
	public List<Instrutor> listar(Instrutor instrutor) {
		
		//List<Object> parametros = new ArrayList<Object>();
		Map<String, Object> campos = new HashMap<String, Object>();
		String sql = SQL.getConsultaINSTRUTOR();
		EditorDeConsultaSQL editor = new EditorDeConsultaSQL(sql,campos);
		
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_CODIGO_INSTRUTOR, instrutor.getCodigo(), "codigo");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_NOME_INSTRUTOR_LIKE, instrutor.getNome(), "nome");
		editor.adicionarOrdenacaoConsulta(SQL.ORDENAR_POR_ID_INSTRUTOR_DECRESCENTE);
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

	public List<Instrutor> listaTodos(){
		TypedQuery<Instrutor> query = getEntityManager().createNamedQuery("Instrutor.listaTodos", Instrutor.class);

		List<Instrutor> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;	
	}

	public Instrutor consultarPorCodigo(Long id){
		TypedQuery<Instrutor> query = getEntityManager().createNamedQuery("Instrutor.consultaPorCodigo", Instrutor.class);
		query.setParameter("codigo", id);

		Instrutor instrutor = null;
		try{
			instrutor = query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return instrutor;	
	}

	public List<Instrutor> consultarPorNome(String nome){
		TypedQuery<Instrutor> query = getEntityManager().createNamedQuery("Instrutor.consultaPorNome", Instrutor.class);
		query.setParameter("nome", "%"+ nome +"%");

		List<Instrutor> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}

}
