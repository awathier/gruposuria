package br.com.gruposuria.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import br.com.gruposuria.entity.Aluno;
import br.com.gruposuria.entity.Cidade;
import br.com.gruposuria.entity.Estado;
import br.com.gruposuria.entity.Instituicao;
import br.com.gruposuria.entity.Turma;
import br.com.gruposuria.entity.TurmaAluno;
import br.com.gruposuria.enums.FormaPagamento;
import br.com.gruposuria.enums.StatusAluno;
import br.com.gruposuria.enums.ValorLogico;
import br.com.gruposuria.model.AlunoModel;
import br.com.gruposuria.model.CidadeModel;
import br.com.gruposuria.model.EstadoModel;
import br.com.gruposuria.model.InstituicaoModel;
import br.com.gruposuria.model.TurmaAlunoModel;
import br.com.gruposuria.model.TurmaModel;

@ManagedBean
@SessionScoped
public class InscricaoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaAlunoModel turmaAlunoModel;
	
	@Inject
	private TurmaModel turmaModel;
	
	@Inject
	private InstituicaoModel instituicaoModel;
	
	@Inject
	private AlunoModel alunoModel;
	
	@Inject
	private EstadoModel estadoModel;
	
	@Inject
	private CidadeModel cidadeModel;
	
	private Instituicao instituicao = new Instituicao();
	
	private TurmaAluno turmaAluno = new TurmaAluno();
	private TurmaAluno turmaAlunoSelecionada = new TurmaAluno();
	private Turma turmaSelecionada = new Turma();
	private Aluno aluno = new Aluno();
	private Aluno alunoSelecionado = new Aluno();
	private Estado estadoSelecionado = new Estado();
	private Cidade cidadeSelecionada = new Cidade();
	
	private List<TurmaAluno> turmasAluno = new ArrayList<TurmaAluno>();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private List<Turma> turmas = new ArrayList<Turma>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	private Estado estado = new Estado();
	private Cidade cidade = new Cidade();

	private boolean mostrarBotaoAlterar = false;
	private boolean acaoDeInclusao;
	private String id;
	private String idCurso;
	private String idInstrutor;
	private String idCidade;
	private String idEstado;
	private String idTurma;
	private String idAluno;
	
	private boolean skip;
	
	@PostConstruct
	public void init() {
		listaEstados();
		aluno = new Aluno();
		alunos = new ArrayList<Aluno>();
		this.instituicao = new Instituicao();
	}
	
	public StatusAluno[] getStatusAluno(){  
        return StatusAluno.values();  
    } 
	
	public FormaPagamento[] getFormaPagamento(){  
        return FormaPagamento.values();  
    } 
	
	public ValorLogico[] getValorLogico(){  
        return ValorLogico.values();  
    } 
	
	public void onItemSelectAluno(SelectEvent event) {
        
		Aluno alunoTemporario = ((Aluno) event.getObject());
        //if(servico.getNome() == null){
        	alunoSelecionado.setCodigo(alunoTemporario.getCodigo());
        	alunoSelecionado.setNome(alunoTemporario.getNome());
        	//servicoTemporario = servico;
        //}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno:", alunoSelecionado.getNome() + " - " + alunoSelecionado.getCodigo()));
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servi�o Postal:", event.getObject().toString()));
    }	
	
	public void createNew() {
        if(this.alunos.contains(this.aluno)) {
            FacesMessage msg = new FacesMessage("Duplicado", "Participante j� inclu�do");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
        else {
        	alunos.add(aluno);
        	aluno = new Aluno();
        }
    }
	
	public String reinit() {
		aluno = new Aluno();
         
        return null;
    }
	
	public void excluir() {
		
		String resultado = null;
		
		    try {
		    	this.turmaAlunoSelecionada =  turmaAlunoModel.excluir(turmaAlunoSelecionada);
		    	if(this.turmaAlunoSelecionada!=null){
		    		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "."));
		    		setTurmaAluno(new TurmaAluno());
		    		setTurmasAluno(new ArrayList<TurmaAluno>());
		    		setAcaoDeInclusao(false);
		    	} else {
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir!", "."));
		    	}
		    	resultado  = "pesquisar-turma-aluno.jsf?faces-redirect=true";
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
				this.turmaAluno = turmaAlunoModel.consultarPorCodigo(turmaAlunoSelecionada.getCodigo());
				this.turmaSelecionada = this.turmaAluno.getTurma();
				this.idTurma = this.turmaAluno.getTurma().getCodigo().toString();
				this.alunoSelecionado = this.turmaAluno.getAluno();
				this.idAluno = this.turmaAluno.getAluno().getCodigo().toString();
				resultado  = "cadastrar-turma-aluno.jsf?faces-redirect=true";
			    FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
			} catch (Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Excluir!", "."));
				System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void limpaFiltroDePesquisa() {
	       setTurmaAluno(new TurmaAluno());
	       setAcaoDeInclusao(false);
	}

	public List<TurmaAluno> listar(){
		
		setTurmasAluno(new ArrayList<TurmaAluno>());
		if(turmasAluno != null) {
			this.turmasAluno = turmaAlunoModel.listar(this.turmaAluno);
		}
		setTurmaAluno(new TurmaAluno());
		return this.turmasAluno;
	}
	
	public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
	
	public void save() {        
        FacesMessage msg = new FacesMessage("Sucesso", "Cadastro Realizado!!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public String salvar(){
		
		String resultado = null;
		
		try {
			System.out.println("incluir");
			
			if( (this.instituicao.getCnpj()!=null) && (!"".equals(this.instituicao.getCnpj())) ){
				this.instituicao.setCnpj(this.instituicao.getCnpj().replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", ""));
			}
			
			if( (this.instituicao.getInscricaoEstadual()!=null) && (!"".equals(this.instituicao.getInscricaoEstadual())) ){
				this.instituicao.setInscricaoEstadual(this.instituicao.getInscricaoEstadual().replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", ""));
			}
			
			if( (this.instituicao.getNome()!=null) && (!"".equals(this.instituicao.getNome())) ){
				this.instituicao.setNome(this.instituicao.getNome().toUpperCase());
			}
			
			if( (this.instituicao.getEndereco()!=null) && (!"".equals(this.instituicao.getEndereco())) ){
				this.instituicao.setEndereco(this.instituicao.getEndereco().toUpperCase());
			}
			
			if( (this.instituicao.getNomeResponsavel()!=null) && (!"".equals(this.instituicao.getNomeResponsavel())) ){
				this.instituicao.setNomeResponsavel(this.instituicao.getNomeResponsavel().toUpperCase());
			}
			
			if( (this.instituicao.getTelefoneResponsavel()!=null) && (!"".equals(this.instituicao.getTelefoneResponsavel())) ){
				this.instituicao.setTelefoneResponsavel(this.instituicao.getTelefoneResponsavel().replaceAll("\\_", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "").replaceAll("/", ""));
			}
			
			if( (this.instituicao.getCelularResponsavel()!=null) && (!"".equals(this.instituicao.getCelularResponsavel())) ){
				this.instituicao.setCelularResponsavel(this.instituicao.getCelularResponsavel().replaceAll("\\_", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "").replaceAll("/", ""));
			}
			
			if( (this.instituicao.getEmailResponsavel()!=null) && (!"".equals(this.instituicao.getEmailResponsavel())) ){
				this.instituicao.setEmailResponsavel(this.instituicao.getEmailResponsavel());
			}
			
			if( (this.instituicao.getCep()!=null) && (!"".equals(this.instituicao.getCep())) ){
				this.instituicao.setCep(this.instituicao.getCep().replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", ""));
			}
			
			if( (this.instituicao.getNomeFinanceiro()!=null) && (!"".equals(this.instituicao.getNomeFinanceiro())) ){
				this.instituicao.setNomeFinanceiro(this.instituicao.getNomeFinanceiro().toUpperCase());
			}
			
			if( (this.instituicao.getTelefoneFinanceiro()!=null) && (!"".equals(this.instituicao.getTelefoneFinanceiro())) ){
				this.instituicao.setTelefoneFinanceiro(this.instituicao.getTelefoneFinanceiro().replaceAll("\\_", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "").replaceAll("/", ""));
			}
			
			if( (this.instituicao.getCelularFinanceiro()!=null) && (!"".equals(this.instituicao.getCelularFinanceiro())) ){
				this.instituicao.setCelularFinanceiro(this.instituicao.getCelularFinanceiro().replaceAll("\\_", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "").replaceAll("/", ""));
			}
			
			if( (this.instituicao.getEmailFinanceiro()!=null) && (!"".equals(this.instituicao.getEmailFinanceiro())) ){
				this.instituicao.setEmailFinanceiro(this.instituicao.getEmailFinanceiro());
			}
			
			if( (this.idEstado!=null) && (!"".equals(this.idEstado)) && (this.idCidade!=null) && (!"".equals(this.idCidade)) ){
				this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
				this.cidadeSelecionada.setIdCidade(Long.parseLong(idCidade));
				this.cidadeSelecionada.setEstado(estadoSelecionado);
				this.instituicao.setCidadeInstituicao(this.cidadeSelecionada);
			}
			
			this.instituicao = instituicaoModel.salvar(this.instituicao);
			
			this.turmaAlunoSelecionada = turmaAlunoModel.salvar(this.turmaAluno);
			
			setTurmaAluno(new TurmaAluno());
			setTurmasAluno(new ArrayList<TurmaAluno>());
			setAlunoSelecionado(new Aluno());
			setTurmaSelecionada(new Turma());
			setAcaoDeInclusao(false);
			listaTurmas();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-turma-aluno.jsf?faces-redirect=true";
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
			
			this.alunoSelecionado.setCodigo(Long.parseLong(idAluno));
			this.turmaAluno.setAluno(alunoSelecionado);
			
			this.turmaSelecionada.setCodigo(Long.parseLong(idTurma));
			this.turmaAluno.setTurma(turmaSelecionada);
			
			this.turmaAlunoSelecionada = turmaAlunoModel.alterar(this.turmaAluno);
			
			setTurmaAluno(new TurmaAluno());
			setTurmasAluno(new ArrayList<TurmaAluno>());
			setAcaoDeInclusao(false);
			
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-turma-aluno.jsf?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
		} catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Gravar!", "."));
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}
	
	public List<Turma> listaTurmas(){
		this.turmas = turmaModel.listaTurmas();
		return this.turmas;
	}
	
	public List<Aluno> listaAlunos(){
		this.alunos = alunoModel.listaAlunos();
		return this.alunos;
	}
	
	public List<Cidade> consultaCidadePorUf(){
		this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
		this.cidades = cidadeModel.consultaCidadePorUf(this.estadoSelecionado);
		return this.cidades;
	}
	
	public List<Cidade> listaCidades(){
		this.cidades = cidadeModel.listaCidades();
		return this.cidades;
	}
	
	public List<Estado> listaEstados(){
		this.estados = estadoModel.listaEstados();
		return this.estados;
	}
	
	public String acaoInclusao() {
		
		setAcaoDeInclusao(true);
		setMostrarBotaoAlterar(false);
		setTurmaAluno(new TurmaAluno());
		setAlunoSelecionado(new Aluno());
		setTurmaSelecionada(new Turma());
		setIdTurma("");
		setIdAluno("");
		listaTurmas();
		listaAlunos();
		return "cadastrar-turma-aluno.jsf?faces-redirect=true";
		
	}
	
	public void cancelar(){
		setTurmaSelecionada(new Turma());
		setTurmaAluno(new TurmaAluno());
		setturmas(null);
		setAcaoDeInclusao(false);
		setMostrarBotaoAlterar(false);
		String resultado  = "pesquisar-turma-aluno.jsf?faces-redirect=true";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public List<Turma> getturmas() {
		return turmas;
	}

	public void setturmas(List<Turma> turmas) {
		this.turmas = turmas;
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

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	public String getIdInstrutor() {
		return idInstrutor;
	}

	public void setIdInstrutor(String idInstrutor) {
		this.idInstrutor = idInstrutor;
	}

	public String getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(String idCidade) {
		this.idCidade = idCidade;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public TurmaAluno getTurmaAluno() {
		return turmaAluno;
	}

	public void setTurmaAluno(TurmaAluno turmaAluno) {
		this.turmaAluno = turmaAluno;
	}

	public TurmaAluno getTurmaAlunoSelecionada() {
		return turmaAlunoSelecionada;
	}

	public void setTurmaAlunoSelecionada(TurmaAluno turmaAlunoSelecionada) {
		this.turmaAlunoSelecionada = turmaAlunoSelecionada;
	}

	public List<TurmaAluno> getTurmasAluno() {
		return turmasAluno;
	}

	public void setTurmasAluno(List<TurmaAluno> turmasAluno) {
		this.turmasAluno = turmasAluno;
	}

	public String getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}

	public String getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(String idAluno) {
		this.idAluno = idAluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}