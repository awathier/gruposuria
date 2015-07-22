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
import br.com.gruposuria.entity.Cidade;
import br.com.gruposuria.entity.Curso;
import br.com.gruposuria.entity.Estado;
import br.com.gruposuria.entity.Instrutor;
import br.com.gruposuria.entity.Turma;
import br.com.gruposuria.entity.TurmaAluno;
import br.com.gruposuria.enums.StatusTurma;
import br.com.gruposuria.model.CidadeModel;
import br.com.gruposuria.model.CursoModel;
import br.com.gruposuria.model.EstadoModel;
import br.com.gruposuria.model.InstrutorModel;
import br.com.gruposuria.model.TurmaAlunoModel;
import br.com.gruposuria.model.TurmaModel;

@ManagedBean
@SessionScoped
public class TurmaMB {
	
	@Inject
	private TurmaModel turmaModel;
	
	@Inject
	private TurmaAlunoModel turmaAlunoModel;
	
	@Inject
	private CursoModel cursoModel;
	
	@Inject
	private InstrutorModel instrutorModel;
	
	@Inject
	private EstadoModel estadoModel;
	
	@Inject
	private CidadeModel cidadeModel;
	
	private Turma turma = new Turma();
	private Turma turmaSelecionada;
	private Instrutor instrutorSelecionado = new Instrutor();
	private List<Instrutor> instrutores = new ArrayList<Instrutor>();
	private Curso cursoSelecionado = new Curso();
	private Estado estadoSelecionado = new Estado();
	private Cidade cidadeSelecionada = new Cidade();
	private List<Curso> cursos = new ArrayList<Curso>();
	private List<Turma> turmas = new ArrayList<Turma>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	private Estado estado = new Estado();
	private Cidade cidade = new Cidade();
	
	private TurmaAluno turmaAluno = new TurmaAluno();
	private List<TurmaAluno> turmasAluno = new ArrayList<TurmaAluno>();
	
	private Aluno aluno = new Aluno();
	private List<Aluno> alunos = new ArrayList<Aluno>();

	private boolean mostrarBotaoAlterar = false;
	private boolean acaoDeInclusao;
	private String id;
	private String idCurso;
	private String idInstrutor;
	private String idCidade;
	private String idEstado;
	
	@PostConstruct
	public void init() {
		this.turmas = listaTurmas();
		int i=0;
		for (Turma t : this.turmas) {
			this.turmaAluno.setTurma(t);
			this.turmasAluno = turmaAlunoModel.listar(this.turmaAluno);
			this.turmas.get(i).setTurmaAlunos(turmasAluno);
			i++;
		}
		listaEstados();
	}
	
	public StatusTurma[] getStatusTurma(){  
        return StatusTurma.values();  
    } 
	
	public void excluir() {
		
		String resultado = null;
		
		    try {
		    	this.turma =  turmaModel.excluir(turmaSelecionada);
		    	if(this.turma!=null){
		    		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "."));
		    		setTurma(new Turma());
		    		setturmas(new ArrayList<Turma>());
		    		listaTurmas();
		    		setAcaoDeInclusao(false);
		    	} else {
		    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir!", "."));
		    	}
		    	resultado  = "pesquisar-turma.jsf?faces-redirect=true";
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
				this.turma = turmaModel.consultarPorCodigo(turmaSelecionada.getCodigo());
				this.idInstrutor = this.turma.getInstrutor().getCodigo().toString();
				this.idCurso = this.turma.getCurso().getCodigo().toString();
				this.cidades.add(this.turma.getCidadeCurso());
				this.idEstado = this.turma.getUf().toString();
				this.idCidade = this.turma.getCidade().toString();
				listaCursos();
				listaInstrutores();
				resultado  = "cadastrar-turma.jsf?faces-redirect=true";
			    FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);  
			} catch (Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Excluir!", "."));
				System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void limpaFiltroDePesquisa() {
	       setTurma(new Turma());
	       setTurmas(new ArrayList<Turma>());
	       setInstrutorSelecionado(new Instrutor());
	       setCursoSelecionado(new Curso());
	       setAcaoDeInclusao(false);
	}

	public List<Turma> listar(){
		
		setTurmas(new ArrayList<Turma>());
		if(turma != null) {
			this.turma.setCurso(this.cursoSelecionado);
			this.turma.setInstrutor(this.instrutorSelecionado);
			this.turmas = turmaModel.listar(this.turma);
			setTurmaAluno(new TurmaAluno());
			setAlunos(new ArrayList<Aluno>());
			setAluno(new Aluno());
			int i=0;
			for (Turma t : this.turmas) {
				this.turmaAluno.setTurma(t);
				this.turmasAluno = turmaAlunoModel.listar(this.turmaAluno);
				this.turmas.get(i).setTurmaAlunos(turmasAluno);
				i++;
			}
		}
		setTurma(new Turma());
		return this.turmas;
	}
	
	public String salvar(){
		
		String resultado = null;
		
		try {
			System.out.println("incluir");
			this.instrutorSelecionado.setCodigo(Long.parseLong(idInstrutor));
			this.turma.setInstrutor(instrutorSelecionado);
			
			this.cursoSelecionado.setCodigo(Long.parseLong(idCurso));
			this.turma.setCurso(cursoSelecionado);

			this.turma.setUf(Long.parseLong(idEstado));
			this.turma.setCidade(Long.parseLong(idCidade));
			
			this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
			this.cidadeSelecionada.setIdCidade(Long.parseLong(idCidade));
			this.cidadeSelecionada.setEstado(estadoSelecionado);
			
			
			this.turma.setCidadeCurso(this.cidadeSelecionada);
			
			this.turma = turmaModel.salvar(this.turma);
			
			setTurma(new Turma());
			setturmas(new ArrayList<Turma>());
			setAcaoDeInclusao(false);
			listaTurmas();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "..."));
			resultado  = "pesquisar-turma.jsf?faces-redirect=true";
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
			this.instrutorSelecionado.setCodigo(Long.parseLong(idInstrutor));
			this.turma.setInstrutor(instrutorSelecionado);
			
			this.cursoSelecionado.setCodigo(Long.parseLong(idCurso));
			this.turma.setCurso(cursoSelecionado);

			this.turma.setUf(Long.parseLong(idEstado));
			this.turma.setCidade(Long.parseLong(idCidade));
			
			this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
			this.cidadeSelecionada.setIdCidade(Long.parseLong(idCidade));
			this.cidadeSelecionada.setEstado(estadoSelecionado);
			
			this.turma.setCidadeCurso(this.cidadeSelecionada);
			
			this.turma = turmaModel.alterar(this.turma);
			setTurma(new Turma());
			setturmas(new ArrayList<Turma>());
			setAcaoDeInclusao(false);
			listaTurmas();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", "."));
			resultado  = "pesquisar-turma.jsf?faces-redirect=true";
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
	
	public List<Curso> listaCursos(){
		this.cursos = cursoModel.listaCursos();
		return this.cursos;
	}
	
	public List<Instrutor> listaInstrutores(){
		this.instrutores = instrutorModel.listaInstrutores();
		return this.instrutores;
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
		setTurma(new Turma());
		setIdInstrutor("");
		setIdCurso("");
		setIdEstado("");
		setIdCidade("");
		listaCursos();
		listaInstrutores();
		//listaCidades();
		listaEstados();
		return "cadastrar-turma.jsf?faces-redirect=true";
		
	}
	
	public void cancelar(){
		setTurma(new Turma());
		setturmas(null);
		setAcaoDeInclusao(false);
		setMostrarBotaoAlterar(false);
		String resultado  = "pesquisar-turma.jsf?faces-redirect=true";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma Turma) {
		this.turma = Turma;
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

	public Instrutor getInstrutorSelecionado() {
		return instrutorSelecionado;
	}

	public void setInstrutorSelecionado(Instrutor instrutorSelecionado) {
		this.instrutorSelecionado = instrutorSelecionado;
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public void setInstrutores(List<Instrutor> instrutores) {
		this.instrutores = instrutores;
	}

	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
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

	public List<TurmaAluno> getTurmasAluno() {
		return turmasAluno;
	}

	public void setTurmasAluno(List<TurmaAluno> turmasAluno) {
		this.turmasAluno = turmasAluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public TurmaAluno getTurmaAluno() {
		return turmaAluno;
	}

	public void setTurmaAluno(TurmaAluno turmaAluno) {
		this.turmaAluno = turmaAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}