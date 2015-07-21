package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gruposuria.dao.TurmaDAO;
import br.com.gruposuria.entity.Turma;

@SuppressWarnings("serial")
@Stateless
public class TurmaModel implements Serializable {

	@Inject
	private TurmaDAO turmaDAO;
	
	public Turma consultarPorCodigo(Long id){
		return turmaDAO.consultarPorCodigo(id);
	}
	
	public Turma salvar(Turma Turma){
		try {
			Turma = turmaDAO.incluir(Turma);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return Turma;
	}
	
	public Turma alterar(Turma Turma){
		try {
			Turma = turmaDAO.alterar(Turma);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return Turma;
	}

	public Turma excluir(Turma Turma){
		try {
			turmaDAO.excluir(Turma.getCodigo());
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return Turma;
	}

	public List<Turma> listaTurmas(){
		return turmaDAO.listaTodos();
	}
	
	public List<Turma> listaTurmasVigentes(){
		return turmaDAO.listaTodosVigentes();
	}
	
	public List<Turma> listar(Turma Turma){
		return turmaDAO.listar(Turma);
	}
	
	public List<Turma> listarTurmaPorCidadeCursoMes(Turma Turma){
		return turmaDAO.listar(Turma);
	}
	
}
