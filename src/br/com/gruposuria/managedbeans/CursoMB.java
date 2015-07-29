package br.com.gruposuria.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.gruposuria.entity.Curso;
import br.com.gruposuria.model.CursoModel;

@ManagedBean
@SessionScoped
public class CursoMB {
	
	@Inject
	private CursoModel cursoModel;
	
	private Curso curso = new Curso();
	private Curso cursoSelecionado;
	private List<Curso> cursos = new ArrayList<Curso>();
	private boolean mostrarBotaoAlterar = false;
	private boolean acaoDeInclusao;
	private String id;
	
	
	@PostConstruct
	public void init() {

	}
	
	public void excluir() {
		
		String resultado = null;
		
		    try {
		    	this.curso =  cursoModel.excluir(cursoSelecionado);
		    	if(this.curso!=null){
		    		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "."));
		    		setCurso(new Curso());
		    		setCursos(new ArrayList<Curso>());
		    		listaCursos();
		    		setAcaoDeInclusao(false);
		    	} else {
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir!", "."));
		    	}
		    	resultado  = "pesquisar-curso.jsf?faces-redirect=true";
	    		FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
			} catch (IOException e) {
				System.out.println("Erro ao excluir: " + e.getMessage());
			}  
		    
	}
	
	public void carregarCamposAlteracao() {
		
		System.out.println("carregarCamposAlteracao");
		String resultado;
		
		if( (!"".equals(acaoDeInclusao)) && (acaoDeInclusao == false) ){
			try {
				mostrarBotaoAlterar = true;
				this.curso = cursoModel.consultarPorCodigo(cursoSelecionado.getCodigo());
				resultado  = "cadastrar-curso.jsf?faces-redirect=true";
			    FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
			} catch (Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Excluir!", "."));
				System.out.println("Erro ao carregar campos para alteração: " + e.getMessage());
			}
		}
	}
	
	public void limpaFiltroDePesquisa() {
	       setCurso(new Curso());
	       setAcaoDeInclusao(false);
	}

	public List<Curso> consultar(){
		
		setCursos(new ArrayList<Curso>());
		if( (curso != null) && (curso.getNome() != null) && ("".equals(curso.getNome()))) {

			this.cursos = cursoModel.listaCursos();
		
		} else if( (curso != null) && (curso.getNome()!=null) && (!"".equals(curso.getNome()))) {
			
			this.cursos = cursoModel.consultarPorNome(curso.getNome().toUpperCase());
			
		}
		
		setCurso(new Curso());
		return this.cursos;
	}
	
	public List<Curso> listar(){
		
		setCursos(new ArrayList<Curso>());
		if(curso != null) {
			this.curso.setNome(this.curso.getNome().toUpperCase());
			this.cursos = cursoModel.consultarPorNome(this.curso.getNome());
		}
		setCurso(new Curso());
		return this.cursos;
	}
	
	public String salvar(){
		
		String resultado = null;
		
		try {
			System.out.println("incluir");
			this.curso.setNome(this.curso.getNome().toUpperCase());
			this.curso.setEmenta(this.curso.getEmenta());
			this.curso.setPublicoAlvo(this.curso.getPublicoAlvo());
			this.curso.setCargaHoraria(this.curso.getCargaHoraria());
			
			this.curso = cursoModel.salvar(this.curso);
			setCurso(new Curso());
			setCursos(new ArrayList<Curso>());
			setAcaoDeInclusao(false);

			//listaCursos();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-curso.jsf?faces-redirect=true";
		    FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Gravar!", "."));
			System.out.println("Erro: " + e.getMessage());
		}
		return resultado;
	}
	
	public String alterar(){
		
		String resultado = null;
		
		try {
			System.out.println("alterar");
			this.curso.setNome(this.curso.getNome().toUpperCase());
			this.curso.setEmenta(this.curso.getEmenta());
			this.curso.setPublicoAlvo(this.curso.getPublicoAlvo());
			this.curso.setCargaHoraria(this.curso.getCargaHoraria());

			this.curso = cursoModel.alterar(this.curso);
			setCurso(new Curso());
			setCursos(new ArrayList<Curso>());
			setAcaoDeInclusao(false);
			listaCursos();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-curso.jsf?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Gravar!", "."));
			System.out.println("Erro ao alterar: " + e.getMessage());
		}
		return resultado;
	}
	
	public List<Curso> listaCursos(){
		this.cursos = cursoModel.listaCursos();
		return this.cursos;
	}
	
	public String acaoInclusao() {
		
		setAcaoDeInclusao(true);
		setMostrarBotaoAlterar(false);
		setCurso(new Curso());
		return "cadastrar-curso.jsf?faces-redirect=true";
		
	}
	
	public void cancelar(){
		setCurso(new Curso());
		setCursos(null);
		setAcaoDeInclusao(false);
		setMostrarBotaoAlterar(false);
		String resultado  = "pesquisar-curso.jsf?faces-redirect=true";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
		} catch (IOException e) {
			System.out.println("Erro ao cancelar: " + e.getMessage());
		} 
		
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public boolean isMostrarBotaoAlterar() {
		return mostrarBotaoAlterar;
	}

	public void setMostrarBotaoAlterar(boolean mostrarBotaoAlterar) {
		this.mostrarBotaoAlterar = mostrarBotaoAlterar;
	}

	public boolean isAcaoDeInclusao() {
		return acaoDeInclusao;
	}

	public void setAcaoDeInclusao(boolean acaoDeInclusao) {
		this.acaoDeInclusao = acaoDeInclusao;
	}

	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
