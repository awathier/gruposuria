package br.com.gruposuria.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.com.gruposuria.entity.Turma;
import br.com.gruposuria.enums.StatusTurma;
import br.com.gruposuria.model.TurmaModel;

@ManagedBean
@SessionScoped
public class ConsultaTurmaMB {
	
	@Inject
	private TurmaModel turmaModel;
	
//	@Inject
//	private CursoModel cursoModel;
//	
//	@Inject
//	private EstadoModel estadoModel;
//	
//	@Inject
//	private CidadeModel cidadeModel;
	
	private Turma turma = new Turma();
	private Turma turmaSelecionada;
//	private Instrutor instrutorSelecionado = new Instrutor();
//	private List<Instrutor> instrutores = new ArrayList<Instrutor>();
//	private Curso cursoSelecionado = new Curso();
//	private Estado estadoSelecionado = new Estado();
//	private Cidade cidadeSelecionada = new Cidade();
//	private List<Curso> cursos = new ArrayList<Curso>();
	private List<Turma> turmas = new ArrayList<Turma>();
//	private List<Estado> estados = new ArrayList<Estado>();
//	private List<Cidade> cidades = new ArrayList<Cidade>();
	
//	private Estado estado = new Estado();
//	private Cidade cidade = new Cidade();

//	private boolean mostrarBotaoAlterar = false;
//	private boolean acaoDeInclusao;
	private String id;
	private String idCurso;
	private String idCidade;
	private String idEstado;
	
	@PostConstruct
	public void init() {
		this.turmas = turmaModel.listaTurmas();
	}
	
	public StatusTurma[] getStatusTurma(){  
        return StatusTurma.values();  
    } 
	

	public void limpaFiltroDePesquisa() {
	       setTurma(new Turma());
	}

	public List<Turma> listar(){
		
//		setturmas(new ArrayList<Turma>());
		if(turma != null) {
			this.turmas = turmaModel.listaTurmas();
		}
		
//		setTurma(new Turma());
		return this.turmas;
	}
	
	public List<Turma> listaTurmas(){
		this.turmas = turmaModel.listaTurmas();
		return this.turmas;
	}
	
//	public List<Curso> listaCursos(){
//		this.cursos = cursoModel.listaCursos();
//		return this.cursos;
//	}
//	
//	public List<Cidade> consultaCidadePorUf(){
//		this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
//		this.cidades = cidadeModel.consultaCidadePorUf(this.estadoSelecionado);
//		return this.cidades;
//	}
//	
//	public List<Cidade> listaCidades(){
//		this.cidades = cidadeModel.listaCidades();
//		return this.cidades;
//	}
//	
//	public List<Estado> listaEstados(){
//		this.estados = estadoModel.listaEstados();
//		return this.estados;
//	}

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

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}


//	public Curso getCursoSelecionado() {
//		return cursoSelecionado;
//	}
//
//	public void setCursoSelecionado(Curso cursoSelecionado) {
//		this.cursoSelecionado = cursoSelecionado;
//	}
//
//	public Cidade getCidadeSelecionada() {
//		return cidadeSelecionada;
//	}
//
//	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
//		this.cidadeSelecionada = cidadeSelecionada;
//	}
//
//	public List<Curso> getCursos() {
//		return cursos;
//	}
//
//	public void setCursos(List<Curso> cursos) {
//		this.cursos = cursos;
//	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

//	public List<Cidade> getCidades() {
//		return cidades;
//	}
//
//	public void setCidades(List<Cidade> cidades) {
//		this.cidades = cidades;
//	}

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

	public String getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(String idCidade) {
		this.idCidade = idCidade;
	}

//	public Estado getEstadoSelecionado() {
//		return estadoSelecionado;
//	}
//
//	public void setEstadoSelecionado(Estado estadoSelecionado) {
//		this.estadoSelecionado = estadoSelecionado;
//	}
//
//	public List<Estado> getEstados() {
//		return estados;
//	}
//
//	public void setEstados(List<Estado> estados) {
//		this.estados = estados;
//	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

//	public Cidade getCidade() {
//		return cidade;
//	}
//
//	public void setCidade(Cidade cidade) {
//		this.cidade = cidade;
//	}
//
//	public Estado getEstado() {
//		return estado;
//	}
//
//	public void setEstado(Estado estado) {
//		this.estado = estado;
//	}

}
