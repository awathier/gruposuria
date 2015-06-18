package br.com.gruposuria.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CURSO")
@NamedQueries({
		@NamedQuery(name="Curso.consultaPorCodigo", query="SELECT c FROM Curso c WHERE c.codigo = :codigo"),
		@NamedQuery(name="Curso.consultaPorNome", query="SELECT c FROM Curso c WHERE c.nome LIKE :nome"),
		@NamedQuery(name="Curso.listaTodos", query="SELECT c FROM Curso c")
})
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CUR_NU")
	private Long codigo;

	@Column(name="CUR_NOME")
	private String nome;

	@Column(name="CUR_EMENTA")
	private String ementa;

	@Column(name="CUR_CH")
	private int cargaHoraria;

	@Column(name="CUR_PUBLICO_ALVO")
	private String publicoAlvo;
	
	@OneToMany(mappedBy="curso", cascade=CascadeType.ALL)
	private List<InteresseCurso> interesseCursos;

	@OneToMany(mappedBy="curso", cascade=CascadeType.ALL)
	private List<Turma> turmas;

	@OneToMany(mappedBy="curso", cascade=CascadeType.ALL)
	private List<InstrutorCurso> instrutorCursos;

	public Curso() {
	}

	public Long getCodigo() {
		return codigo;
	}

	

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public List<InteresseCurso> getInteresseCursos() {
		return this.interesseCursos;
	}

	public void setInteresseCursos(List<InteresseCurso> interesseCursos) {
		this.interesseCursos = interesseCursos;
	}

	public InteresseCurso addInteresseCurso(InteresseCurso interesseCurso) {
		getInteresseCursos().add(interesseCurso);
		interesseCurso.setCurso(this);

		return interesseCurso;
	}

	public InteresseCurso removeInteresseCurso(InteresseCurso interesseCurso) {
		getInteresseCursos().remove(interesseCurso);
		interesseCurso.setCurso(null);

		return interesseCurso;
	}

	public List<Turma> getTurmas() {
		return this.turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Turma addTurma(Turma turma) {
		getTurmas().add(turma);
		turma.setCurso(this);

		return turma;
	}

	public Turma removeTurma(Turma turma) {
		getTurmas().remove(turma);
		turma.setCurso(null);

		return turma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	public List<InstrutorCurso> getInstrutorCursos() {
		return instrutorCursos;
	}

	public void setInstrutorCursos(List<InstrutorCurso> instrutorCursos) {
		this.instrutorCursos = instrutorCursos;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}