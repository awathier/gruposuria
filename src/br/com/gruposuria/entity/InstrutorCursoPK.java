package br.com.gruposuria.entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class InstrutorCursoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="INS_NU")
	private long instrutor;

	@Column(name="CUR_NU")
	private long curso;

	public InstrutorCursoPK() {
	}

	public InstrutorCursoPK(int instrutor, int curso) {
		this.instrutor = instrutor;
		this.curso = curso;
	}

	public long getInstrutor() {
		return this.instrutor;
	}
	
	public void setInstrutor(long codigoInstrutor) {
		this.instrutor = codigoInstrutor;
	}
	
	public long getCurso() {
		return this.curso;
	}
	
	public void setCurso(long codigoCurso) {
		this.curso = codigoCurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (curso ^ (curso >>> 32));
		result = prime * result + (int) (instrutor ^ (instrutor >>> 32));
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
		InstrutorCursoPK other = (InstrutorCursoPK) obj;
		if (curso != other.curso)
			return false;
		if (instrutor != other.instrutor)
			return false;
		return true;
	}

}