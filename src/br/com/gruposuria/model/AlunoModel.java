package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.AlunoDAO;
import br.com.gruposuria.entity.Aluno;

@SuppressWarnings("serial")
@Stateless
public class AlunoModel implements Serializable {

	@Inject
	private AlunoDAO alunoDAO;

	public Aluno consultarPorNomeCpf(String nome, String cpf) {
		return alunoDAO.consultarPorNomeCpf(nome, cpf);
	}

	public List<Aluno> consultarPorNome(String nome) {
		return alunoDAO.consultarPorNome(nome);
	}

	public Aluno consultarPorCpf(String cpf) {
		return alunoDAO.consultarPorCpf(cpf);
	}

	public Aluno consultarPorCodigo(long id) {
		return alunoDAO.consultarPorCodigo(id);
	}

	public Aluno salvar(Aluno aluno) {
		aluno = alunoDAO.incluir(aluno);
		return aluno;
	}

	public Aluno alterar(Aluno aluno) {
		aluno = alunoDAO.alterar(aluno);
		return aluno;
	}

	public Aluno excluir(Aluno aluno) {
		try {
			alunoDAO.excluir(aluno.getCodigo());
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return aluno;
	}

	public List<Aluno> listaAlunos() {
		return alunoDAO.listaTodos();
	}

	public List<Aluno> listar(Aluno aluno) {
		return alunoDAO.listar(aluno);
	}

}
