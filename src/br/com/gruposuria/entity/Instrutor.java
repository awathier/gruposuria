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
@Table(name = "INSTRUTOR")
@NamedQueries({
		@NamedQuery(name = "Instrutor.consultaPorCodigo", query = "SELECT i FROM Instrutor i WHERE i.codigo = :codigo"),
		@NamedQuery(name = "Instrutor.consultaPorNome", query = "SELECT i FROM Instrutor i WHERE i.nome LIKE :nome"),
		@NamedQuery(name = "Instrutor.consultaPorCodigoCurso", query = "SELECT i FROM Instrutor i INNER JOIN i.instrutorCursos ic WHERE i.nome LIKE :nome"),
		@NamedQuery(name = "Instrutor.listaTodos", query = "SELECT i FROM Instrutor i") })
public class Instrutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INS_NU")
	private Long codigo;

	@Column(name = "INS_NOME")
	private String nome;

	@Column(name = "INS_CURRICULO")
	private String curriculo;

	public Instrutor() {
	}

	@OneToMany(mappedBy = "instrutor", cascade = CascadeType.ALL)
	private List<InstrutorCurso> instrutorCursos;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(String curriculo) {
		this.curriculo = curriculo;
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
		Instrutor other = (Instrutor) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

}