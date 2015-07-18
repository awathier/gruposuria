package br.com.gruposuria.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "INSTITUICAO")
@NamedQueries({
		@NamedQuery(name = "Instituicao.consultaPorCodigo", query = "SELECT i FROM Instituicao i WHERE i.codigo = :codigo"),
		@NamedQuery(name = "Instituicao.consultaPorCnpj", query = "SELECT i FROM Instituicao i WHERE i.cnpj = :cnpj"),
		@NamedQuery(name = "Instituicao.consultaPorNome", query = "SELECT i FROM Instituicao i WHERE i.nome LIKE :nome"),
		@NamedQuery(name = "Instituicao.listaTodos", query = "SELECT i FROM Instituicao i") })
public class Instituicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INT_NU")
	private Long codigo;

	@Column(name = "INT_CNPJ")
	private String cnpj;

	@Column(name = "INT_IE")
	private String inscricaoEstadual;

	@Column(name = "INT_NOME")
	private String nome;

	@Column(name = "INT_ENDERECO")
	private String endereco;

	@Column(name = "INT_NOME_RESPONSAVEL")
	private String nomeResponsavel;

	@Column(name = "INT_FONE_RESPONSAVEL")
	private String telefoneResponsavel;

	@Column(name = "INT_CELULAR_RESPONSAVEL")
	private String celularResponsavel;

	@Column(name = "INT_CEP")
	private String cep;

	@Column(name = "INT_SETOR_RESPONSAVEL")
	private String setorResponsavel;

	@Column(name = "INT_EMAIL_RESPONSAVEL")
	private String emailResponsavel;

	@Column(name = "INT_NOME_FINANCEIRO")
	private String nomeFinanceiro;

	@Column(name = "INT_EMAIL_FINANCEIRO")
	private String emailFinanceiro;

	@Column(name = "INT_FONE_FINANCEIRO")
	private String telefoneFinanceiro;

	@Column(name = "INT_CELULAR_FINANCEIRO")
	private String celularFinanceiro;

	@ManyToOne
	@JoinColumn(name = "CID_ID")
	private Cidade cidadeInstituicao;

	public Instituicao() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public void setTelefoneResponsavel(String telefoneResponsavel) {
		this.telefoneResponsavel = telefoneResponsavel;
	}

	public String getCelularResponsavel() {
		return celularResponsavel;
	}

	public void setCelularResponsavel(String celularResponsavel) {
		this.celularResponsavel = celularResponsavel;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNomeFinanceiro() {
		return nomeFinanceiro;
	}

	public void setNomeFinanceiro(String nomeFinanceiro) {
		this.nomeFinanceiro = nomeFinanceiro;
	}

	public String getEmailFinanceiro() {
		return emailFinanceiro;
	}

	public void setEmailFinanceiro(String emailFinanceiro) {
		this.emailFinanceiro = emailFinanceiro;
	}

	public String getTelefoneFinanceiro() {
		return telefoneFinanceiro;
	}

	public void setTelefoneFinanceiro(String telefoneFinanceiro) {
		this.telefoneFinanceiro = telefoneFinanceiro;
	}

	public String getCelularFinanceiro() {
		return celularFinanceiro;
	}

	public void setCelularFinanceiro(String celularFinanceiro) {
		this.celularFinanceiro = celularFinanceiro;
	}

	public Cidade getCidadeInstituicao() {
		return cidadeInstituicao;
	}

	public void setCidadeInstituicao(Cidade cidadeInstituicao) {
		this.cidadeInstituicao = cidadeInstituicao;
	}

	public String getSetorResponsavel() {
		return setorResponsavel;
	}

	public void setSetorResponsavel(String setorResponsavel) {
		this.setorResponsavel = setorResponsavel;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Instituicao other = (Instituicao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}