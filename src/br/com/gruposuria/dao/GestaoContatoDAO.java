package br.com.gruposuria.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.com.gruposuria.constantes.SQL;
import br.com.gruposuria.entity.GestaoContato;
import br.com.gruposuria.util.EditorDeConsultaSQL;

@Named
@RequestScoped
public class GestaoContatoDAO extends DAO<GestaoContato> {
	
	public GestaoContatoDAO() {

	}

	@Override
	protected Class<GestaoContato> novoTipoDeObjetoPersistente() {
		return GestaoContato.class;
	}
	
	public GestaoContato consultarPorCodigo(long codigo){
		TypedQuery<GestaoContato> query = getEntityManager().createNamedQuery("GestaoContato.consultarPorCodigo", GestaoContato.class);
		query.setParameter("codigo", codigo);

		GestaoContato gestaoContato = null;
		try{
			gestaoContato = (GestaoContato)query.getSingleResult();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return gestaoContato;
	}
	
	public List<GestaoContato> listar(GestaoContato GestaoContato) {
		
		//List<Object> parametros = new ArrayList<Object>();
		Map<String, Object> campos = new HashMap<String, Object>();
		String sql = SQL.getConsultaGestaoContato();
		EditorDeConsultaSQL editor = new EditorDeConsultaSQL(sql,campos);
		
		editor.adicionarFiltroEParametrosNaConsultaJPQL(SQL.FILTRO_POR_CODIGO_GESTAO_CONTATO, GestaoContato.getCodigo(), "codigo");
		sql = editor.getSql();
		//parametros = editor.getParams();
		campos = editor.getCampos();
		Set<String> chaves = campos.keySet();
//		Query query = getEntityManager().createQuery(sql);
		TypedQuery<GestaoContato> query = getEntityManager().createQuery(sql, GestaoContato.class);
		for (String chave : chaves) {  
            if(chave != null)  
                //System.out.println(chave + campos.get(chave));
            	query.setParameter(chave, campos.get(chave));
        	}  
		
		return query.getResultList();
	}

	public List<GestaoContato> listaTodos(){
		TypedQuery<GestaoContato> query = getEntityManager().createNamedQuery("GestaoContato.listaTodos", GestaoContato.class);

		List<GestaoContato> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}

	public List<GestaoContato> consultarPorNome(String nome){
		TypedQuery<GestaoContato> query = getEntityManager().createNamedQuery("GestaoContato.consultaPorNome", GestaoContato.class);
		query.setParameter("nome", "%"+ nome +"%");

		List<GestaoContato> lista = null;
		try{
			lista = query.getResultList();			
		}catch(javax.persistence.NoResultException e){
			//não é necessário tratamento
		}
		return lista;
	}

}
