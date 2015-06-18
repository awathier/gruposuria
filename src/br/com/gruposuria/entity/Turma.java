package br.com.gruposuria.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.gruposuria.enums.StatusTurma;

@Entity
@Table(name="TURMA")
@NamedQueries({
	@NamedQuery(name="Turma.consultaPorCodigo", query="SELECT t FROM Turma t WHERE t.codigo = :codigo"),
	@NamedQuery(name="Turma.consultaPorNomeCurso", query="SELECT t FROM Turma t WHERE t.curso.nome LIKE :nomeCurso"),
	@NamedQuery(name="Turma.consultaPorNomeInstrutor", query="SELECT t FROM Turma t WHERE t.instrutor.nome LIKE :nomeInstrutor"),
	@NamedQuery(name="Turma.consultaPorCidade", query="SELECT t FROM Turma t WHERE t.cidade = :cidade"),
	@NamedQuery(name="Turma.consultaPorUf", query="SELECT t FROM Turma t WHERE t.uf = :uf"),
	@NamedQuery(name="Turma.consultaPorData", query="SELECT t FROM Turma t WHERE t.data = :data"),
	@NamedQuery(name="Turma.listaTodos", query="SELECT t FROM Turma t ORDER BY t.codigo DESC")
})
public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TUR_NU")
	private Long codigo;

	@Column(name="TUR_CIDADE")
	private Long cidade;

	@Column(name="TUR_UF")
	private Long uf;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TUR_DATA")
	private Date data;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TUR_DATA_FIM")
	private Date dataFim;

	@Column(name="TUR_VALOR")
	private BigDecimal valor;
	
	@Column(name="TUR_STATUS")
	@Enumerated(EnumType.STRING)
	private StatusTurma status;
	
	@Column(name="TUR_QT_MINIMA")
	private Integer qtdeMinima;

	@ManyToOne
	@JoinColumn(name="CUR_NU")
	private Curso curso;
 
	@ManyToOne
	@JoinColumn(name="INS_NU")
	private Instrutor instrutor;

	@OneToMany(mappedBy="turma", cascade=CascadeType.ALL)
	private List<TurmaAluno> turmaAlunos;
	
	@ManyToOne
	@JoinColumn(name="CID_ID")
	private Cidade cidadeCurso;
	
	public Turma(Long codigo, Long cidade, Long uf, Date data,
			BigDecimal valor, StatusTurma status, Integer qtdeMinima,
			Curso curso, Instrutor instrutor, List<TurmaAluno> turmaAlunos,
			Cidade cidadeCurso) {
		super();
		this.codigo = codigo;
		this.cidade = cidade;
		this.uf = uf;
		this.data = data;
		this.valor = valor;
		this.status = status;
		this.qtdeMinima = qtdeMinima;
		this.curso = curso;
		this.instrutor = instrutor;
		this.turmaAlunos = turmaAlunos;
		this.cidadeCurso = cidadeCurso;
	}

	public Turma() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Long getCidade() {
		return cidade;
	}

	public void setCidade(Long cidade) {
		this.cidade = cidade;
	}

	public Long getUf() {
		return uf;
	}

	public void setUf(Long uf) {
		this.uf = uf;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public StatusTurma getStatus() {
		return status;
	}

	public void setStatus(StatusTurma status) {
		this.status = status;
	}

	public Integer getQtdeMinima() {
		return qtdeMinima;
	}

	public void setQtdeMinima(Integer qtdeMinima) {
		this.qtdeMinima = qtdeMinima;
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

	public List<TurmaAluno> getTurmaAlunos() {
		return this.turmaAlunos;
	}

	public void setTurmaAlunos(List<TurmaAluno> turmaAlunos) {
		this.turmaAlunos = turmaAlunos;
	}

	public Cidade getCidadeCurso() {
		return cidadeCurso;
	}

	public void setCidadeCurso(Cidade cidadeCurso) {
		this.cidadeCurso = cidadeCurso;
	}

	public TurmaAluno addTurmaAluno(TurmaAluno turmaAluno) {
		getTurmaAlunos().add(turmaAluno);
		turmaAluno.setTurma(this);

		return turmaAluno;
	}

	public TurmaAluno removeTurmaAluno(TurmaAluno turmaAluno) {
		getTurmaAlunos().remove(turmaAluno);
		turmaAluno.setTurma(null);

		return turmaAluno;
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
		Turma other = (Turma) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		//return "Turma [codigo=" + codigo + "]";
		return this.codigo.toString();
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}