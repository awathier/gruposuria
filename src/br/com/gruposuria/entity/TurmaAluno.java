package br.com.gruposuria.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.gruposuria.enums.FormaPagamento;
import br.com.gruposuria.enums.StatusAluno;

@Entity
@Table(name="TURMA_ALUNO")
@NamedQueries({
	@NamedQuery(name="TurmaAluno.consultaPorCodigo", query="SELECT ta FROM TurmaAluno ta WHERE ta.codigo = :codigo"),
	@NamedQuery(name="TurmaAluno.listaTodos", query="SELECT ta FROM TurmaAluno ta")
})

public class TurmaAluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TAL_NU")
	private Long codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TAL_DATA")
	private Date talData;
	
	@Column(name="TAL_VALOR_PAGO")
	private BigDecimal talValorPago;

	@Column(name="TAL_STATUS_ALUNO")
	@Enumerated(EnumType.STRING)
	private StatusAluno statusAluno;

	@Column(name="TAL_FORMA_PAGAMENTO")
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(name="TUR_NU")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name="ALU_NU")
	private Aluno aluno;

	public TurmaAluno() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getTalData() {
		return talData;
	}

	public void setTalData(Date talData) {
		this.talData = talData;
	}

	public BigDecimal getTalValorPago() {
		return talValorPago;
	}

	public void setTalValorPago(BigDecimal talValorPago) {
		this.talValorPago = talValorPago;
	}

	public StatusAluno getStatusAluno() {
		return statusAluno;
	}

	public void setStatusAluno(StatusAluno statusAluno) {
		this.statusAluno = statusAluno;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return this.turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		TurmaAluno other = (TurmaAluno) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}
	
	
}