package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.TurmaAlunoDAO;
import br.com.gruposuria.entity.TurmaAluno;

@SuppressWarnings("serial")
@Stateless
public class TurmaAlunoModel implements Serializable {

	@Inject
	private TurmaAlunoDAO turmaAlunoDAO;
	
	public TurmaAluno consultarPorCodigo(Long id){
		return turmaAlunoDAO.consultarPorCodigo(id);
	}
	
	public TurmaAluno salvar(TurmaAluno TurmaAluno){
		try {
			TurmaAluno = turmaAlunoDAO.incluir(TurmaAluno);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return TurmaAluno;
	}
	
	public TurmaAluno alterar(TurmaAluno TurmaAluno){
		try {
			TurmaAluno = turmaAlunoDAO.alterar(TurmaAluno);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return TurmaAluno;
	}

	public TurmaAluno excluir(TurmaAluno TurmaAluno){
		try {
			turmaAlunoDAO.excluir(TurmaAluno.getCodigo());
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return TurmaAluno;
	}

	public List<TurmaAluno> listaTurmaAlunos(){
		return turmaAlunoDAO.listaTodos();
	}
	
	public List<TurmaAluno> listar(TurmaAluno TurmaAluno){
		return turmaAlunoDAO.listar(TurmaAluno);
	}
	
}
