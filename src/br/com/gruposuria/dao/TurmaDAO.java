package br.com.gruposuria.dao;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import br.com.gruposuria.constantes.SQL;
import br.com.gruposuria.entity.Curso;
import br.com.gruposuria.entity.Turma;
import br.com.gruposuria.util.EditorDeConsultaSQL;

public class TurmaDAO extends DAO<Turma> {
	public TurmaDAO() {
	}

	@Override
	protected Class<Turma> novoTipoDeObjetoPersistente() {
		return Turma.class;
	}

	public List<Turma> listar(Turma turma) {

		Map<String, Object> campos = new HashMap<String, Object>();
		String sql = SQL.getConsultaTURMA();
		EditorDeConsultaSQL editor = new EditorDeConsultaSQL(sql, campos);

		editor.adicionarFiltroEParametrosNaConsultaJPQL(
				SQL.FILTRO_POR_CODIGO_TURMA, turma.getCodigo(), "codigo");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(
				SQL.FILTRO_POR_CODIGO_NOME_CURSO_TURMA, turma.getCurso()
						.getNome(), "nomeCurso");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(
				SQL.FILTRO_POR_CODIGO_NOME_INTRUTOR_TURMA, turma.getInstrutor()
						.getNome(), "nomeInstrutor");
		editor.adicionarOrdenacaoConsulta(SQL.ORDENAR_POR_ID_TURMA_DECRESCENTE);
		sql = editor.getSql();
		campos = editor.getCampos();
		Set<String> chaves = campos.keySet();

		TypedQuery<Turma> query = getEntityManager().createQuery(sql,
				Turma.class);
		for (String chave : chaves) {
			if (chave != null) {
				query.setParameter(chave, campos.get(chave));
			}
		}

		return query.getResultList();
	}

	public List<Turma> listarTurmaPorUFCursoMes(int mes, long codigoCurso,
			long codigoUF) {
		Map<String, Object> campos = new HashMap<String, Object>();
		String sql = SQL.getConsultaTURMA();
		EditorDeConsultaSQL editor = new EditorDeConsultaSQL(sql, campos);

		editor.adicionarFiltroEParametrosNaConsultaJPQL(
				SQL.FILTRO_POR_MES_TURMA, mes, "mesTurma");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(
				SQL.FILTRO_POR_CURSO_TURMA, codigoCurso, "codigoCurso");
		editor.adicionarFiltroEParametrosNaConsultaJPQL(
				SQL.FILTRO_POR_UF_TURMA, codigoUF, "codigoUF");

		editor.adicionarOrdenacaoConsulta(SQL.ORDENAR_POR_ID_TURMA_DECRESCENTE);
		sql = editor.getSql();
		campos = editor.getCampos();
		Set<String> chaves = campos.keySet();

		TypedQuery<Turma> query = getEntityManager().createQuery(sql,
				Turma.class);
		for (String chave : chaves) {
			if (chave != null) {
				query.setParameter(chave, campos.get(chave));
			}
		}

		List<Turma> listaRetorno = query.getResultList();

		return listaRetorno;
	}

	public List<Turma> listaTodos() {
		TypedQuery<Turma> query = getEntityManager().createNamedQuery(
				"Turma.listaTodos", Turma.class);

		List<Turma> lista = null;
		try {
			lista = query.getResultList();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return lista;
	}

	public List<Turma> listaTodosVigentes() {

		Calendar cal = Calendar.getInstance();
		cal.set(HOUR_OF_DAY, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
		cal.set(MILLISECOND, 0);
		System.out.println(cal.getTime());

		TypedQuery<Turma> query = getEntityManager().createNamedQuery(
				"Turma.listaTodosVigentes", Turma.class);
		query.setParameter("today", cal.getTime(), TemporalType.DATE);

		List<Turma> lista = null;
		try {
			lista = query.getResultList();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return lista;
	}

	public Turma consultarPorCodigo(long id) {
		TypedQuery<Turma> query = getEntityManager().createNamedQuery(
				"Turma.consultaPorCodigo", Turma.class);
		query.setParameter("codigo", id);

		Turma turma = null;
		try {
			turma = (Turma) query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return turma;
	}

	public List<Turma> consultaPorNomeCurso(String nomeCurso) {
		TypedQuery<Turma> query = getEntityManager().createNamedQuery(
				"Turma.consultaPorNomeCurso", Turma.class);
		query.setParameter("nome", "%" + nomeCurso + "%");

		List<Turma> lista = null;
		try {
			lista = query.getResultList();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return lista;
	}

	public List<Turma> consultaPorNomeTurma(String nomeTurma) {
		TypedQuery<Turma> query = getEntityManager().createNamedQuery(
				"Turma.consultaPorNomeTurma", Turma.class);
		query.setParameter("nome", "%" + nomeTurma + "%");

		List<Turma> lista = null;
		try {
			lista = query.getResultList();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return lista;
	}

	public List<Turma> consultaPorCidade(String nomeCidade) {
		TypedQuery<Turma> query = getEntityManager().createNamedQuery(
				"Turma.consultaPorCidade", Turma.class);
		query.setParameter("cidade", "%" + nomeCidade + "%");

		List<Turma> lista = null;
		try {
			lista = query.getResultList();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return lista;
	}

	public List<Turma> consultaPorUf(String uf) {
		TypedQuery<Turma> query = getEntityManager().createNamedQuery(
				"Turma.consultaPorUf", Turma.class);
		query.setParameter("uf", "%" + uf + "%");

		List<Turma> lista = null;
		try {
			lista = query.getResultList();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return lista;
	}

	public List<Turma> consultaPorData(String data) {
		TypedQuery<Turma> query = getEntityManager().createNamedQuery(
				"Turma.consultaPorData", Turma.class);
		query.setParameter("data", data);

		List<Turma> lista = null;
		try {
			lista = query.getResultList();
		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}
		return lista;
	}

	private Date getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Calendar c = GregorianCalendar.getInstance();

		System.out.println(dateFormat.format(c.getTime()));

		return c.getTime();
	}

	public List<Turma> listaTotaisPorCurso() {

		List<Turma> resultado = new ArrayList<Turma>();
		TypedQuery<Object[]> query = getEntityManager().createNamedQuery(
				"Turma.listaTotaisPorCurso", Object[].class);
		// query.setParameter("codigo", id);

		List<Object[]> lista = null;

		try {

			lista = query.getResultList();

			for (Object[] result : lista) {
				String nome = (String) result[0];
				Long count = ((Long) result[1]).longValue();
				Turma turma = new Turma();
				Curso curso = new Curso();
				curso.setNome(nome);
				turma.setCurso(curso);
				turma.setTotal(count);
				resultado.add(turma);
			}

		} catch (javax.persistence.NoResultException e) {
			// não é necessário tratamento
		}

		return resultado;
	}

	public static void main(String[] args) {
		TurmaDAO dao = new TurmaDAO();

		// Turma turmaConsulta = new Turma();
		// turmaConsulta.setData(dao.getDateTime());

		long codigoUF = 1;
		long codigoCurso = 9;
		int mes = 4;

		List<Turma> listaTodos = dao.listarTurmaPorUFCursoMes(mes, codigoCurso,
				codigoUF);
		for (Turma turma : listaTodos) {
			System.out.println("Turma " + turma);
		}
	}

}
