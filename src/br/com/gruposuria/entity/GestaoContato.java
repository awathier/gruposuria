package br.com.gruposuria.entity;

import java.io.Serializable;

import javax.persistence.*;

import br.com.gruposuria.enums.Meses;
import br.com.gruposuria.enums.StatusContato;
import br.com.gruposuria.enums.TipoContato;
import br.com.gruposuria.enums.ValorLogico;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="GESTAO_CONTATO")
@NamedQueries({
	@NamedQuery(name="GestaoContato.listaTodos", query="SELECT g FROM GestaoContato g"),
	@NamedQuery(name="GestaoContato.consultarPorCodigo", query="SELECT g FROM GestaoContato g WHERE g.codigo =:codigo"),
	@NamedQuery(name="GestaoContato.consultaPorNome", query="SELECT g FROM GestaoContato g WHERE g.nomeContato LIKE :nome")
})
public class GestaoContato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GCO_NU")
	private long codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GCO_DATA_ACAO")
	private Date dataAcao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GCO_DATA_CONTATO")
	private Date dataContato;

	@Column(name="GCO_EMAIL")
	private String email;

	@Column(name="GCO_FONE")
	private String telefone;

	@Column(name="GCO_HORA_ATENDIMENTO")
	private String horaAtendimento;

	@Column(name="GCO_INSCRICAO_SITE")
	@Enumerated(EnumType.STRING)
	private ValorLogico inscricaoSite;

	@Column(name="GCO_MES_CURSO")
	@Enumerated(EnumType.STRING)
	private Meses mesCurso;

	@Column(name="GCO_NOME_CONTATO")
	private String nomeContato;

	@Column(name="GCO_NOME_CURSO")
	private String nomeCurso;

	@Column(name="GCO_NOME_INSTITUICAO")
	private String nomeInstituicao;

	@Column(name="GCO_OBS")
	private String obs;

	@Column(name="GCO_QT_PARTICIPANTES")
	private String qtdeParticipantes;

	@Column(name="GCO_STATUS")
	@Enumerated(EnumType.STRING)
	private StatusContato status;

	@Column(name="GCO_TIPO_CONTATO")
	@Enumerated(EnumType.STRING)
	private TipoContato tipoContato;

	@Column(name="GCO_VALOR_NEGOCIADO")
	private BigDecimal valorNegociado;

	@ManyToOne
	@JoinColumn(name="TUR_NU")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name="CID_ID")
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name="USU_NU")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name="CUR_NU")
	private Curso curso;

	public GestaoContato() {
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Date getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(Date dataAcao) {
		this.dataAcao = dataAcao;
	}

	public Date getDataContato() {
		return dataContato;
	}

	public void setDataContato(Date dataContato) {
		this.dataContato = dataContato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}

	public ValorLogico getInscricaoSite() {
		return inscricaoSite;
	}

	public void setInscricaoSite(ValorLogico inscricaoSite) {
		this.inscricaoSite = inscricaoSite;
	}

	public Meses getMesCurso() {
		return mesCurso;
	}

	public void setMesCurso(Meses mesCurso) {
		this.mesCurso = mesCurso;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getQtdeParticipantes() {
		return qtdeParticipantes;
	}

	public void setQtdeParticipantes(String qtdeParticipantes) {
		this.qtdeParticipantes = qtdeParticipantes;
	}

	public StatusContato getStatus() {
		return status;
	}

	public void setStatus(StatusContato status) {
		this.status = status;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public BigDecimal getValorNegociado() {
		return valorNegociado;
	}

	public void setValorNegociado(BigDecimal valorNegociado) {
		this.valorNegociado = valorNegociado;
	}

	public Turma getTurma() {
		return this.turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}