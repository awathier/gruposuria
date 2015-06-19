package br.com.gruposuria.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "Cidade.listaTodos", query = "SELECT c FROM Cidade c"),
		@NamedQuery(name = "Cidade.consultaCidadePorUf", query = "SELECT c FROM Cidade c WHERE c.estado.idEstado = :idEstado") })
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CID_ID")
	private long idCidade;

	@Column(name = "CID_NOME")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "EST_ID")
	private Estado estado;

	@OneToMany(mappedBy = "cidade")
	private List<Turma> turmas;

	public Cidade() {
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Turma> getTurmas() {
		return this.turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}