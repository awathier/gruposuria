package br.com.gruposuria.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="INTERESSE_CURSO")
@NamedQuery(name="InteresseCurso.findAll", query="SELECT i FROM InteresseCurso i")
public class InteresseCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InteresseCursoPK id;

	@ManyToOne
	@JoinColumn(name="ALU_NU", insertable=false, updatable=false)
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name="CUR_NU", insertable=false, updatable=false)
	private Curso curso;

	public InteresseCurso() {
	}

	public InteresseCursoPK getId() {
		return this.id;
	}

	public void setId(InteresseCursoPK id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}