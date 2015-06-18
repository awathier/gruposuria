package br.com.gruposuria.entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class InteresseCursoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ALU_NU")
	private long codigoAluno;

	@Column(name="CUR_NU")
	private long codigoCurso;

	public InteresseCursoPK() {
	}
	
	public long getAluno() {
		return this.codigoAluno;
	}
	public void setAluno(long codigoAluno) {
		this.codigoAluno = codigoAluno;
	}
	public long getCurso() {
		return this.codigoCurso;
	}
	public void setCurso(long codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigoAluno ^ (codigoAluno >>> 32));
		result = prime * result + (int) (codigoCurso ^ (codigoCurso >>> 32));
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
		InteresseCursoPK other = (InteresseCursoPK) obj;
		if (codigoAluno != other.codigoAluno)
			return false;
		if (codigoCurso != other.codigoCurso)
			return false;
		return true;
	}

}