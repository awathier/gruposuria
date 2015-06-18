package br.com.gruposuria.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditorDeConsultaSQL {

	private String sql;
	private List<Object> params = new ArrayList<Object>();
	private Map<String, Object> campos = new HashMap<String, Object>();
	
	public EditorDeConsultaSQL(String sql, List<Object> params) {
		this.sql = sql;
		this.params = params;
	}
	
	public EditorDeConsultaSQL(String sql, Map<String, Object> campos) {
		this.sql = sql;
		this.campos = campos;
	}
	
	public EditorDeConsultaSQL(String sql, List<Object> params, Map<String, Object> campos) {
		this.sql = sql;
		this.params = params;
		this.campos = campos;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public Map<String, Object> getCampos() {
		return campos;
	}

	public void setCampos(Map<String, Object> campos) {
		this.campos = campos;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

	public void adicionarFiltroEParametrosNaConsultaJPQL( String filtro, Object param, String campo ){
		if(parametroFoiPreenchido(param)){
			campos.put(campo, param);
			params.add(param);
			sql = sql + filtro;
		}
	}
	
	public void adicionarFiltroEParametrosNaConsulta( String filtro, Object param ){
		if(parametroFoiPreenchido(param)){
			params.add(param);
			sql = sql + filtro;
		}
	}
	
	public void adicionarOrdenacaoConsulta(String ordenacao){
			sql = sql + ordenacao;
	}
	
	private boolean parametroFoiPreenchido(Object param){
		boolean result = false;
		if(param == null){
			return false;
		}
		
		if(param instanceof Number){
			if ( (param != null) && (!param.equals(0)) ){
				result = true;
			}
		}
		if(param instanceof String){
			if( (param != null) && (!"".equals(param) )){
				result = true;
			}
		}
		if(param instanceof Boolean){
			if( (param != null)){
				result = true;
			}
		}
		if(param instanceof Date){
			if( (param != null)){
				result = true;
			}
		}
		return result;
	}

}
