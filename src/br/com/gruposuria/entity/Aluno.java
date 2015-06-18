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
import javax.persistence.Table;

@Entity
@Table(name="ALUNO")
@NamedQueries({
		@NamedQuery(name="Aluno.listaTodos", query="SELECT a FROM Aluno a"),
		@NamedQuery(name="Aluno.consultaPorCodigo", query="SELECT a FROM Aluno a WHERE a.codigo = :codigo"),
		@NamedQuery(name="Aluno.consultaPorCpf", query="SELECT a FROM Aluno a WHERE a.cpf = :cpf"),
		@NamedQuery(name="Aluno.consultaPorNome", query="SELECT a FROM Aluno a WHERE a.nome LIKE :nome"),
		@NamedQuery(name="Aluno.consultaPorNomeCpf", query="SELECT a FROM Aluno a WHERE a.nome LIKE :nome AND a.cpf = :cpf")
})
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ALU_NU")
	private Long codigo;

	@Column(name="ALU_CPF")
	private String cpf;

	@Column(name="ALU_EMAIL")
	private String email;

	@Column(name="ALU_NOME")
	private String nome;

	@Column(name="ALU_TELEFONE")
	private String telefone;

	@Column(name="ALU_SENHA")
	private String senha;
	
	@Column(name="ALU_NECESSIDADE_ESPECIAL")
	private String especial;
	
	@ManyToOne
	@JoinColumn(name="INT_NU")
	private Instituicao instituicao;

	@OneToMany(mappedBy="aluno")
	private List<TurmaAluno> turmaAlunos;

	@OneToMany(mappedBy="aluno")
	private List<InteresseCurso> interesseCursos;

	public Aluno(Long codigo, String cpf, String email, String nome,
			String telefone, String senha, Instituicao instituicao,
			List<TurmaAluno> turmaAlunos, List<InteresseCurso> interesseCursos) {
		super();
		this.codigo = codigo;
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.telefone = telefone;
		this.senha = senha;
		this.instituicao = instituicao;
		this.turmaAlunos = turmaAlunos;
		this.interesseCursos = interesseCursos;
	}

	public Aluno() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Instituicao getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public List<TurmaAluno> getTurmaAlunos() {
		return this.turmaAlunos;
	}

	public void setTurmaAlunos(List<TurmaAluno> turmaAlunos) {
		this.turmaAlunos = turmaAlunos;
	}

	public TurmaAluno addTurmaAluno(TurmaAluno turmaAluno) {
		getTurmaAlunos().add(turmaAluno);
		turmaAluno.setAluno(this);

		return turmaAluno;
	}

	public TurmaAluno removeTurmaAluno(TurmaAluno turmaAluno) {
		getTurmaAlunos().remove(turmaAluno);
		turmaAluno.setAluno(null);

		return turmaAluno;
	}

	public List<InteresseCurso> getInteresseCursos() {
		return this.interesseCursos;
	}

	public void setInteresseCursos(List<InteresseCurso> interesseCursos) {
		this.interesseCursos = interesseCursos;
	}

	public InteresseCurso addInteresseCurso(InteresseCurso interesseCurso) {
		getInteresseCursos().add(interesseCurso);
		interesseCurso.setAluno(this);

		return interesseCurso;
	}

	public InteresseCurso removeInteresseCurso(InteresseCurso interesseCurso) {
		getInteresseCursos().remove(interesseCurso);
		interesseCurso.setAluno(null);

		return interesseCurso;
	}

	public int hashCode() {
        int hash = 1;
        if(nome != null)
            hash = hash * 31 + nome.hashCode();
         
        if(cpf != null)
            hash = hash * 29 + cpf.hashCode();
 
        return hash;
    }

	public boolean equals(Object obj) {
        if(!(obj instanceof Aluno))
            return false;
         
        Aluno aluno = (Aluno) obj;
         
        return (aluno.getNome() != null && aluno.getNome().equals(nome)) && (aluno.getCpf() != null && aluno.getCpf().equals(cpf));
    }
	
	@Override
	public String toString() {
		//return "Aluno [codigo=" + codigo + ", nome=" + nome + "]";
		return this.codigo.toString();
	}

	public String getEspecial() {
		return especial;
	}

	public void setEspecial(String especial) {
		this.especial = especial;
	}

}