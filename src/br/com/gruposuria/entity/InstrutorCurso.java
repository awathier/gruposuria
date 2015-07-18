package br.com.gruposuria.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="INSTRUTOR_CURSO")
@NamedQuery(name="InstrutorCurso.findAll", query="SELECT i FROM InstrutorCurso i")
public class InstrutorCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InstrutorCursoPK id;

	@ManyToOne
	@JoinColumn(name="CUR_NU", insertable=false, updatable=false)
	private Curso curso;

	@ManyToOne
	@JoinColumn(name="INS_NU", insertable=false, updatable=false)
	private Instrutor instrutor;

	public InstrutorCurso() {
	}

	public InstrutorCursoPK getId() {
		return this.id;
	}

	public void setId(InstrutorCursoPK id) {
		this.id = id;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Instrutor getInstrutor() {
		return this.instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}
}