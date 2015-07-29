/**
 * Copyright: (c) 2010 - ECT
 */
package br.com.gruposuria.constantes;


public class SQL {

	/*FILTROS ALUNO*/
	public static String FILTRO_POR_CODIGO_ALUNO = " AND a.codigo =:codigo " ;
	public static String FILTRO_POR_NOME_ALUNO_LIKE = " AND a.nome LIKE '%'||:nome||'%' " ;
	public static String FILTRO_POR_CPF_ALUNO = " AND a.cpf =:cpf " ;
	
	public static String ORDENAR_POR_ID_ALUNO_CRESCENTE = " ORDER BY a.codigo " ;
	public static String ORDENAR_POR_ID_ALUNO_DECRESCENTE = " ORDER BY a.codigo DESC " ;
	public static String ORDENAR_POR_NOME_ALUNO_CRESCENTE = " ORDER BY a.nome " ;
	public static String ORDENAR_POR_NOME_ALUNO_DECRESCENTE = " ORDER BY a.nome DESC " ;
	public static String ORDENAR_POR_CPF_ALUNO_CRESCENTE = " ORDER BY a.cpf " ;
	public static String ORDENAR_POR_CPF_ALUNO_DECRESCENTE = " ORDER BY a.cpf DESC " ;
	
	/*FILTROS TURMA*/
	public static String FILTRO_POR_CODIGO_TURMA = " AND t.codigo =:codigo " ;
	public static String FILTRO_POR_MES_TURMA = " AND MONTH(t.data) = :mesTurma " ;
	public static String FILTRO_POR_CURSO_TURMA = " AND t.curso.codigo = :codigoCurso " ;
	public static String FILTRO_POR_CODIGO_NOME_CURSO_TURMA = " AND t.curso.nome LIKE '%'||:nomeCurso||'%' " ;
	public static String FILTRO_POR_CODIGO_NOME_INTRUTOR_TURMA = " AND t.instrutor.nome LIKE '%'||:nomeInstrutor||'%' " ;
	public static String FILTRO_POR_UF_TURMA = " AND t.uf =:codigoUF " ;

	public static String ORDENAR_POR_ID_TURMA_CRESCENTE = " ORDER BY t.codigo " ;
	public static String ORDENAR_POR_ID_TURMA_DECRESCENTE = " ORDER BY t.codigo DESC " ;
	
	/*FILTROS CURSO*/
	public static String FILTRO_POR_CODIGO_CURSO = " AND c.codigo =:codigo " ;
	public static String FILTRO_POR_NOME_CURSO_LIKE = " AND c.nome LIKE '%'||:nome||'%' " ;
	
	public static String ORDENAR_POR_ID_CURSO_CRESCENTE = " ORDER BY c.codigo " ;
	public static String ORDENAR_POR_ID_CURSO_DECRESCENTE = " ORDER BY c.codigo DESC " ;
	
	/*FILTROS INSTRUTOR*/
	public static String FILTRO_POR_CODIGO_INSTRUTOR = " AND i.codigo =:codigo " ;
	public static String FILTRO_POR_NOME_INSTRUTOR_LIKE = " AND i.nome LIKE '%'||:nome||'%' " ;
	
	public static String ORDENAR_POR_ID_INSTRUTOR_CRESCENTE = " ORDER BY i.codigo " ;
	public static String ORDENAR_POR_ID_INSTRUTOR_DECRESCENTE = " ORDER BY i.codigo DESC " ;
	
	/*FILTROS INSTITUICAO*/
	public static String FILTRO_POR_CODIGO_INSTITUICAO = " AND i.codigo =:codigo " ;
	public static String FILTRO_POR_CNPJ_INSTITUICAO = " AND i.cnpj =:cnpj " ;
	public static String FILTRO_POR_NOME_INSTITUICAO_LIKE = " AND i.nome LIKE '%'||:nome||'%' " ;
	
	public static String ORDENAR_POR_ID_INSTITUICAO_CRESCENTE = " ORDER BY i.codigo " ;
	public static String ORDENAR_POR_ID_INSTITUICAO_DECRESCENTE = " ORDER BY i.codigo DESC " ;
	
	/*FILTROS TURMA_ALUNO*/
	public static String FILTRO_POR_CODIGO_TURMA_ALUNO = " AND ta.codigo =:codigo " ;
	public static String FILTRO_POR_CODIGO_TURMA_TURMA_ALUNO = " AND ta.turma.codigo =:codigoTurma " ;
	
	public static String ORDENAR_POR_ID_TURMA_ALUNO_CRESCENTE = " ORDER BY ta.codigo " ;
	public static String ORDENAR_POR_ID_TURMA_ALUNO_DECRESCENTE = " ORDER BY ta.codigo DESC " ;
	
	//**************************************************************************************************
	
	//public static String OBTER_ULTIMO_VALOR_CHAVE_TABELA_ALUNO = " SELECT MAX(alu_nu) alu_nu FROM ALUNO " ;
	//public static String CHAVE_TABELA_ALUNO = "alu_nu" ;

	//**************************************************************************************************
	
	public static String getConsultaALUNO() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM ");
		sql.append(" Aluno a ");
		sql.append(" WHERE 1=1 ");
		return sql.toString();
	}
	
	public static String getConsultaTURMA() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT t ");
		sql.append(" FROM ");
		sql.append(" Turma t ");
		sql.append(" WHERE 1=1 ");
		return sql.toString();
	}
	
	public static String getConsultaCURSO() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT c ");
		sql.append(" FROM ");
		sql.append(" Curso c ");
		sql.append(" WHERE 1=1 ");
		return sql.toString();
	}
	
	public static String getConsultaINSTRUTOR() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT i ");
		sql.append(" FROM ");
		sql.append(" Instrutor i ");
		sql.append(" WHERE 1=1 ");
		return sql.toString();
	}
	
	public static String getConsultaINTITUICAO() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT i ");
		sql.append(" FROM ");
		sql.append(" Instituicao i ");
		sql.append(" WHERE 1=1 ");
		return sql.toString();
	}
	
	public static String getConsultaTURMAALUNO() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ta ");
		sql.append(" FROM ");
		sql.append(" TurmaAluno ta ");
		sql.append(" WHERE 1=1 ");
		return sql.toString();
	}

}
