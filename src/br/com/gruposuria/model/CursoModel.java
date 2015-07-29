package br.com.gruposuria.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.gruposuria.dao.CursoDAO;
import br.com.gruposuria.entity.Curso;

@SuppressWarnings("serial")
@Stateless
public class CursoModel implements Serializable {

	@Inject
	private CursoDAO cursoDAO;
	
	public Curso consultarPorCodigo(long codigo){
		return cursoDAO.consultarPorCodigo(codigo);
	}
	
	public Curso salvar(Curso curso){
		try {
			curso = cursoDAO.incluir(curso);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensagem",  "Gravado com sucesso!") );
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensagem",  "Erro ao salvar: " + e.getMessage()));
			System.out.println("Erro: " + e.getMessage());
		}
		return curso;
	}
	
	public Curso alterar(Curso curso){
		try {
			curso = cursoDAO.alterar(curso);
		} catch (Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		return curso;
	}

	public Curso excluir(Curso curso){
		try {
			cursoDAO.excluir(curso.getCodigo());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensagem",  "Excluído com sucesso!") );
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensagem",  "Erro ao excluir: " + e.getMessage()));
			System.out.println("Erro: " + e.getMessage());
		}
		return curso;
	}
	
	public List<Curso> listaCursos(){
		return cursoDAO.listaTodos();
	}
	
	public List<Curso> consultarPorNome(String nome){
		return cursoDAO.consultarPorNome(nome);
	}
}
