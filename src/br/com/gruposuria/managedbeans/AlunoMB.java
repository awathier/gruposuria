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

import br.com.gruposuria.entity.Aluno;
import br.com.gruposuria.model.AlunoModel;

@ManagedBean
@SessionScoped
public class AlunoMB {
	
	@Inject
	private AlunoModel alunoModel;
	
	private Aluno aluno = new Aluno();
	private Aluno alunoSelecionado;
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private boolean mostrarBotaoAlterar = false;
	private boolean acaoDeInclusao;
	private String id;
	
	
	@PostConstruct
	public void init() {

	}
	
	public void excluir() {
		
		String resultado = null;
		
		    try {
		    	this.aluno =  alunoModel.excluir(alunoSelecionado);
		    	if(this.aluno!=null){
		    		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "."));
		    		setAluno(new Aluno());
		    		setAlunos(new ArrayList<Aluno>());
		    		listaAlunos();
		    		setAcaoDeInclusao(false);
		    	} else {
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir!", "."));
		    	}
		    	resultado  = "pesquisar-aluno.jsf?faces-redirect=true";
	    		FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
			} catch (IOException e) {
				e.printStackTrace();
			}  
		    
	}
	
	public void carregarCamposAlteracao() {
		
		System.out.println("carregarCamposAlteracao");
		String resultado;
		
		if( (!"".equals(acaoDeInclusao)) && (acaoDeInclusao == false) ){
			try {
				mostrarBotaoAlterar = true;
				this.aluno = alunoModel.consultarPorCodigo(alunoSelecionado.getCodigo());
				resultado  = "cadastrar-aluno.jsf?faces-redirect=true";
			    FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
			} catch (Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Excluir!", "."));
				System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void limpaFiltroDePesquisa() {
	       setAluno(new Aluno());
	       setAcaoDeInclusao(false);
	}
	
	public List<Aluno> listar(){
		
		setAlunos(new ArrayList<Aluno>());
		if(aluno != null) {
			this.aluno.setCpf(this.aluno.getCpf().replaceAll("\\.", "").replaceAll("\\-", ""));
			this.aluno.setNome(this.aluno.getNome().toUpperCase());
			this.alunos = alunoModel.listar(this.aluno);
		}
		setAluno(new Aluno());
		return this.alunos;
	}
	
	public String salvar(){
		
		String resultado = null;
		
		try {
			System.out.println("incluir");
			this.aluno.setCpf(this.aluno.getCpf().replaceAll("\\.", "").replaceAll("\\-", ""));
			this.aluno.setNome(this.aluno.getNome().toUpperCase());
			this.aluno = alunoModel.salvar(this.aluno);
			setAluno(new Aluno());
			setAlunos(new ArrayList<Aluno>());
			setAcaoDeInclusao(false);
			//listaAlunos();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-aluno.jsf?faces-redirect=true";
		    FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Gravar!", "."));
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}
	
	public String alterar(){
		
		String resultado = null;
		
		try {
			System.out.println("alterar");
			this.aluno.setCpf(this.aluno.getCpf().replaceAll("\\.", "").replaceAll("\\-", ""));
			this.aluno.setNome(this.aluno.getNome().toUpperCase());
			this.aluno = alunoModel.alterar(this.aluno);
			setAluno(new Aluno());
			setAlunos(new ArrayList<Aluno>());
			setAcaoDeInclusao(false);
			listaAlunos();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-aluno.jsf?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Gravar!", "."));
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}
	
	public List<Aluno> listaAlunos(){
		this.alunos = alunoModel.listaAlunos();
		return this.alunos;
	}
	
	public String acaoInclusao() {
		
		setAcaoDeInclusao(true);
		setMostrarBotaoAlterar(false);
		setAluno(new Aluno());
		return "cadastrar-aluno.jsf?faces-redirect=true";
		
	}
	
	public void cancelar(){
		setAluno(new Aluno());
		setAlunos(null);
		setAcaoDeInclusao(false);
		setMostrarBotaoAlterar(false);
		String resultado  = "pesquisar-aluno.jsf?faces-redirect=true";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
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

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
