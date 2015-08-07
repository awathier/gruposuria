package br.com.gruposuria.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.gruposuria.entity.Curso;
import br.com.gruposuria.entity.GestaoContato;
import br.com.gruposuria.entity.Turma;
import br.com.gruposuria.entity.Usuario;
import br.com.gruposuria.enums.Meses;
import br.com.gruposuria.enums.StatusContato;
import br.com.gruposuria.enums.TipoContato;
import br.com.gruposuria.enums.ValorLogico;
import br.com.gruposuria.model.CursoModel;
import br.com.gruposuria.model.GestaoContatoModel;
import br.com.gruposuria.model.TurmaModel;

@ManagedBean
@SessionScoped
public class GestaoContatoMB {
	
	@Inject
	private GestaoContatoModel gestaoContatoModel;
	
	@Inject
	private TurmaModel turmaModel;

	@Inject
	private CursoModel cursoModel;

	private GestaoContato gestaoContato = new GestaoContato();
	private GestaoContato gestaoContatoSelecionado;
	private List<GestaoContato> gestaoContatos = new ArrayList<GestaoContato>();
	private List<Curso> cursos = new ArrayList<Curso>();
	private List<Turma> turmas = new ArrayList<Turma>();
//	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private boolean mostrarBotaoAlterar = false;
	private boolean acaoDeInclusao;
	private String id;
	private String idGestaoContato;
	private String idCurso;
	private String idTurma;
	
	
	public ValorLogico[] getValorLogico() {
		return ValorLogico.values();
	}
	
	public StatusContato[] getStatusContato() {
		return StatusContato.values();
	}
	
	public TipoContato[] getTipoContato() {
		return TipoContato.values();
	}
	
	public Meses[] getMeses() {
		return Meses.values();
	}
	
	public void excluir() {
		
		String resultado = null;
		
		    try {
		    	this.gestaoContato =  gestaoContatoModel.excluir(gestaoContatoSelecionado);
		    	if(this.gestaoContato!=null){
		    		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "."));
		    		setGestaoContato(new GestaoContato());
		    		setGestaoContatos(new ArrayList<GestaoContato>());
		    		listaGestaoContatos();
		    		setAcaoDeInclusao(false);
		    	} else {
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir!", "."));
		    	}
		    	resultado  = "pesquisar-gestao-contato.jsf?faces-redirect=true";
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
				this.idGestaoContato = "";
				this.gestaoContato = gestaoContatoModel.consultarPorCodigo(gestaoContatoSelecionado.getCodigo());

				resultado  = "cadastrar-gestao-contato.jsf?faces-redirect=true";
			    FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
			} catch (Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Excluir!", "."));
				System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void limpaFiltroDePesquisa() {
	       setGestaoContato(new GestaoContato());
	       setAcaoDeInclusao(false);
	}
	
	public List<GestaoContato> listar(){
		
		setGestaoContatos(new ArrayList<GestaoContato>());
		if(gestaoContato != null) {
			this.gestaoContato.setCidade(gestaoContato.getCidade());
			this.gestaoContato.setCodigo(gestaoContato.getCodigo());
			this.gestaoContato.setDataAcao(gestaoContato.getDataAcao());
			this.gestaoContato.setDataContato(gestaoContato.getDataContato());
			this.gestaoContato.setEmail(gestaoContato.getEmail());
			this.gestaoContato.setHoraAtendimento(gestaoContato.getHoraAtendimento());
			this.gestaoContato.setInscricaoSite(gestaoContato.getInscricaoSite());
			this.gestaoContato.setMesCurso(gestaoContato.getMesCurso());
			this.gestaoContato.setNomeContato(gestaoContato.getNomeContato());
			this.gestaoContato.setNomeCurso(gestaoContato.getNomeCurso());
			this.gestaoContato.setNomeInstituicao(gestaoContato.getNomeInstituicao());
			this.gestaoContato.setObs(gestaoContato.getObs());
			this.gestaoContato.setQtdeParticipantes(gestaoContato.getQtdeParticipantes());
			this.gestaoContato.setStatus(gestaoContato.getStatus());
			this.gestaoContato.setTelefone(gestaoContato.getTelefone());
			this.gestaoContato.setTipoContato(gestaoContato.getTipoContato());
			this.gestaoContato.setTurma(gestaoContato.getTurma());
			this.gestaoContato.setUsuario(gestaoContato.getUsuario());
			this.gestaoContato.setValorNegociado(gestaoContato.getValorNegociado());
			
			this.gestaoContatos = gestaoContatoModel.listar(this.gestaoContato);
		}
		setGestaoContato(new GestaoContato());
		return this.gestaoContatos;
	}
	
	public String salvar(){
		
		String resultado = null;
		
		try {
			System.out.println("incluir");
			/*this.gestaoContato.setCidade(gestaoContato.getCidade());
			this.gestaoContato.setCodigo(gestaoContato.getCodigo());
			this.gestaoContato.setDataAcao(gestaoContato.getDataAcao());
			this.gestaoContato.setDataContato(gestaoContato.getDataContato());
			this.gestaoContato.setEmail(gestaoContato.getEmail());
			this.gestaoContato.setHoraAtendimento(gestaoContato.getHoraAtendimento());
			this.gestaoContato.setInscricaoSite(gestaoContato.getInscricaoSite());
			this.gestaoContato.setMesCurso(gestaoContato.getMesCurso());
			this.gestaoContato.setNomeContato(gestaoContato.getNomeContato());
			this.gestaoContato.setNomeCurso(gestaoContato.getNomeCurso());
			this.gestaoContato.setNomeInstituicao(gestaoContato.getNomeInstituicao());
			this.gestaoContato.setObs(gestaoContato.getObs());
			this.gestaoContato.setQtdeParticipantes(gestaoContato.getQtdeParticipantes());
			this.gestaoContato.setStatus(gestaoContato.getStatus());
			this.gestaoContato.setTelefone(gestaoContato.getTelefone());
			this.gestaoContato.setTipoContato(gestaoContato.getTipoContato());
			this.gestaoContato.setTurma(gestaoContato.getTurma());
			this.gestaoContato.setUsuario(gestaoContato.getUsuario());
			this.gestaoContato.setValorNegociado(gestaoContato.getValorNegociado());
*/
			this.gestaoContato = gestaoContatoModel.salvar(this.gestaoContato);
			
			setGestaoContato(new GestaoContato());
			setGestaoContatos(new ArrayList<GestaoContato>());
			setAcaoDeInclusao(false);
			//listaGestaoContatos();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-gestao-contato.jsf?faces-redirect=true";
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
			this.gestaoContato.setCidade(gestaoContato.getCidade());
			this.gestaoContato.setCodigo(gestaoContato.getCodigo());
			this.gestaoContato.setDataAcao(gestaoContato.getDataAcao());
			this.gestaoContato.setDataContato(gestaoContato.getDataContato());
			this.gestaoContato.setEmail(gestaoContato.getEmail());
			this.gestaoContato.setHoraAtendimento(gestaoContato.getHoraAtendimento());
			this.gestaoContato.setInscricaoSite(gestaoContato.getInscricaoSite());
			this.gestaoContato.setMesCurso(gestaoContato.getMesCurso());
			this.gestaoContato.setNomeContato(gestaoContato.getNomeContato());
			this.gestaoContato.setNomeCurso(gestaoContato.getNomeCurso());
			this.gestaoContato.setNomeInstituicao(gestaoContato.getNomeInstituicao());
			this.gestaoContato.setObs(gestaoContato.getObs());
			this.gestaoContato.setQtdeParticipantes(gestaoContato.getQtdeParticipantes());
			this.gestaoContato.setStatus(gestaoContato.getStatus());
			this.gestaoContato.setTelefone(gestaoContato.getTelefone());
			this.gestaoContato.setTipoContato(gestaoContato.getTipoContato());
			this.gestaoContato.setTurma(gestaoContato.getTurma());
			this.gestaoContato.setUsuario(gestaoContato.getUsuario());
			this.gestaoContato.setValorNegociado(gestaoContato.getValorNegociado());

			this.gestaoContato = gestaoContatoModel.alterar(this.gestaoContato);
			setGestaoContato(new GestaoContato());
			setGestaoContatos(new ArrayList<GestaoContato>());
			setAcaoDeInclusao(false);
			listaGestaoContatos();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-gestao-contato.jsf?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Gravar!", "."));
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}
	
	public List<GestaoContato> listaGestaoContatos(){
		this.gestaoContatos = gestaoContatoModel.listaGestaoContatos();
		return this.gestaoContatos;
	}
	
	public List<Turma> listaTurmas() {
		this.turmas = turmaModel.listaTurmas();
		return this.turmas;
	}

	public List<Curso> listaCursos() {
		this.cursos = cursoModel.listaCursos();
		return this.cursos;
	}

	public String acaoInclusao() {
		
		setAcaoDeInclusao(true);
		setMostrarBotaoAlterar(false);
		setGestaoContato(new GestaoContato());
		return "cadastrar-gestao-contato.jsf?faces-redirect=true";
		
	}
	
	public void cancelar(){
		setGestaoContato(new GestaoContato());
		setGestaoContatos(null);
		setAcaoDeInclusao(false);
		setMostrarBotaoAlterar(false);
		String resultado  = "pesquisar-gestao-contato.jsf?faces-redirect=true";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	public GestaoContato getGestaoContato() {
		return gestaoContato;
	}

	public void setGestaoContato(GestaoContato gestaoContato) {
		this.gestaoContato = gestaoContato;
	}

	public List<GestaoContato> getGestaoContatos() {
		return gestaoContatos;
	}

	public void setGestaoContatos(List<GestaoContato> gestaoContatos) {
		this.gestaoContatos = gestaoContatos;
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

	public GestaoContato getGestaoContatoSelecionado() {
		return gestaoContatoSelecionado;
	}

	public void setGestaoContatoSelecionado(GestaoContato gestaoContatoSelecionado) {
		this.gestaoContatoSelecionado = gestaoContatoSelecionado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdInstituicao() {
		return idGestaoContato;
	}

	public void setIdInstituicao(String idInstituicao) {
		this.idGestaoContato = idInstituicao;
	}

	public GestaoContatoModel getGestaoContatoModel() {
		return gestaoContatoModel;
	}

	public void setGestaoContatoModel(GestaoContatoModel gestaoContatoModel) {
		this.gestaoContatoModel = gestaoContatoModel;
	}

	public String getIdGestaoContato() {
		return idGestaoContato;
	}

	public void setIdGestaoContato(String idGestaoContato) {
		this.idGestaoContato = idGestaoContato;
	}
	
	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public String getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}

	public List<Usuario> getUsuarios() {
		return gestaoContatoModel.listaUsuario();
	}

}
