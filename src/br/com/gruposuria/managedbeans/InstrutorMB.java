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

import br.com.gruposuria.entity.Instrutor;
import br.com.gruposuria.model.InstrutorModel;

@ManagedBean
@SessionScoped
public class InstrutorMB {
	
	@Inject
	private InstrutorModel instrutorModel;
	
	private Instrutor instrutor = new Instrutor();
	private Instrutor instrutorSelecionado;
	private List<Instrutor> instrutores = new ArrayList<Instrutor>();
	private boolean mostrarBotaoAlterar = false;
	private boolean acaoDeInclusao;
	private String id;
	
	
	@PostConstruct
	public void init() {

	}
	
	public void excluir() {
		
		String resultado = null;
		
		    try {
		    	this.instrutor =  instrutorModel.excluir(instrutorSelecionado);
		    	if(this.instrutor!=null){
		    		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "."));
		    		setInstrutor(new Instrutor());
		    		setInstrutores(new ArrayList<Instrutor>());
		    		listaInstrutores();
		    		setAcaoDeInclusao(false);
		    	} else {
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir!", "."));
		    	}
		    	resultado  = "pesquisar-instrutor.jsf?faces-redirect=true";
	    		FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
			} catch (IOException e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			}  
		    
	}
	
	public void carregarCamposAlteracao() {
		
		System.out.println("carregarCamposAlteracao");
		String resultado;
		
		if( (!"".equals(acaoDeInclusao)) && (acaoDeInclusao == false) ){
			try {
				mostrarBotaoAlterar = true;
				this.instrutor = instrutorModel.consultarPorCodigo(instrutorSelecionado.getCodigo());
				resultado  = "cadastrar-instrutor.jsf?faces-redirect=true";
			    FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
			} catch (Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Excluir!", "."));
				System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void limpaFiltroDePesquisa() {
	       setInstrutor(new Instrutor());
	       setAcaoDeInclusao(false);
	}
	
	public List<Instrutor> listar(){
		
		setInstrutores(new ArrayList<Instrutor>());
		if(instrutor != null) {
			this.instrutor.setNome(this.instrutor.getNome().toUpperCase());
			this.instrutores = instrutorModel.listar(this.instrutor);
		}
		setInstrutor(new Instrutor());
		return this.instrutores;
	}
	
	public String salvar(){
		
		String resultado = null;
		
		try {
			System.out.println("incluir");
			this.instrutor.setNome(this.instrutor.getNome().toUpperCase());
			this.instrutor = instrutorModel.salvar(this.instrutor);
			setInstrutor(new Instrutor());
			setInstrutores(new ArrayList<Instrutor>());
			setAcaoDeInclusao(false);
			//listaInstrutors();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-instrutor.jsf?faces-redirect=true";
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
			this.instrutor.setNome(this.instrutor.getNome().toUpperCase());
			this.instrutor = instrutorModel.alterar(this.instrutor);
			setInstrutor(new Instrutor());
			setInstrutores(new ArrayList<Instrutor>());
			setAcaoDeInclusao(false);
			listaInstrutores();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-instrutor.jsf?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Gravar!", "."));
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}
	
	public List<Instrutor> listaInstrutores(){
		this.instrutores = instrutorModel.listaInstrutores();
		return this.instrutores;
	}
	
	public String acaoInclusao() {
		
		setAcaoDeInclusao(true);
		setMostrarBotaoAlterar(false);
		setInstrutor(new Instrutor());
		return "cadastrar-instrutor.jsf?faces-redirect=true";
		
	}
	
	public void cancelar(){
		setInstrutor(new Instrutor());
		setInstrutores(null);
		setAcaoDeInclusao(false);
		setMostrarBotaoAlterar(false);
		String resultado  = "pesquisar-instrutor.jsf?faces-redirect=true";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
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

	public Instrutor getInstrutorSelecionado() {
		return instrutorSelecionado;
	}

	public void setInstrutorSelecionado(Instrutor instrutorSelecionado) {
		this.instrutorSelecionado = instrutorSelecionado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public void setInstrutores(List<Instrutor> instrutores) {
		this.instrutores = instrutores;
	}
	
}
