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

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;

import br.com.gruposuria.entity.Aluno;
import br.com.gruposuria.entity.Cidade;
import br.com.gruposuria.entity.Curso;
import br.com.gruposuria.entity.Estado;
import br.com.gruposuria.entity.Instrutor;
import br.com.gruposuria.entity.Turma;
import br.com.gruposuria.entity.TurmaAluno;
import br.com.gruposuria.enums.StatusAluno;
import br.com.gruposuria.enums.StatusTurma;
import br.com.gruposuria.model.CidadeModel;
import br.com.gruposuria.model.CursoModel;
import br.com.gruposuria.model.EstadoModel;
import br.com.gruposuria.model.InstrutorModel;
import br.com.gruposuria.model.TurmaAlunoModel;
import br.com.gruposuria.model.TurmaModel;

@ManagedBean
@SessionScoped
public class TurmaMB implements Serializable {

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

	private TagCloudModel tagCloudModel;

	private PieChartModel pieModel;
	
	private BarChartModel barModel;
	
	private HorizontalBarChartModel horizontalBarModel;

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
		setTurmas(new ArrayList<Turma>());
		if (turma != null) {
			this.turma.setCurso(this.cursoSelecionado);
			this.turma.setInstrutor(this.instrutorSelecionado);
			this.turmas = turmaModel.listar(this.turma);
			setTurmaAluno(new TurmaAluno());
			setAlunos(new ArrayList<Aluno>());
			setAluno(new Aluno());
			int i = 0;
			for (Turma t : this.turmas) {
				this.turmaAluno.setTurma(t);
				this.turmasAluno = turmaAlunoModel.listar(this.turmaAluno);
				this.turmas.get(i).setTurmaAlunos(turmasAluno);
				i++;
			}
		}
		setTurma(new Turma());
		listaEstados();
		// listaTotaisPorCurso();
		tagCloud();
		createPieModels();
		createBarModels();
	}

	public StatusTurma[] getStatusTurma() {
		return StatusTurma.values();
	}

	public StatusAluno[] getStatusAluno() {
		return StatusAluno.values();
	}
	
	private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
	
	/****************************************************************************************/
	
	public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
	
	private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
	
	private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries cursos = new ChartSeries();
        cursos.setLabel("Cursos");
        cursos.set("Curso A", 50);
        cursos.set("2005", 96);
        cursos.set("2006", 44);
 
        horizontalBarModel.addSeries(cursos);
         
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Total Alunos");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cursos");       
    }
	
	private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Qtd Alunos Curso");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Cursos");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total Alunos");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
	
	/***************************************************************************************************/

	public void onRowEdit(RowEditEvent event) {

		for (Turma t : this.turmas) {
			turmaModel.alterar(t);
		}

		FacesMessage msg = new FacesMessage("Dado(s) Alterados",
				((TurmaAluno) event.getObject()).getAluno().getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Alteração Cancelada",
				((TurmaAluno) event.getObject()).getAluno().getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {

		// String resultado = null;

		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {

			for (Turma t : this.turmas) {
				turmaModel.alterar(t);
			}

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Dado(s) alterados", "Antigo: " + oldValue + ", Novo:"
							+ newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

		// resultado = "pesquisar-turma.jsf?faces-redirect=true";
		// FacesContext.getCurrentInstance().getExternalContext().redirect(resultado);

	}

	public void onSelect(SelectEvent event) {
		TagCloudItem item = (TagCloudItem) event.getObject();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Item Selected", item.getLabel());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void excluir() {

		String resultado = null;

		try {
			this.turma = turmaModel.excluir(turmaSelecionada);
			if (this.turma != null) {
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Excluido com sucesso!", "."));
				setTurma(new Turma());
				setturmas(new ArrayList<Turma>());
				listaTurmas();
				setAcaoDeInclusao(false);
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Erro ao Excluir!", "."));
			}
			resultado = "pesquisar-turma.jsf?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void carregarCamposAlteracao() {

		System.out.println("carregarCamposAlteracao");
		String resultado;

		if ((!"".equals(acaoDeInclusao)) && (acaoDeInclusao == false)) {
			try {
				mostrarBotaoAlterar = true;
				this.turma = turmaModel.consultarPorCodigo(turmaSelecionada
						.getCodigo());
				this.idInstrutor = this.turma.getInstrutor().getCodigo()
						.toString();
				this.idCurso = this.turma.getCurso().getCodigo().toString();
				this.cidades.add(this.turma.getCidadeCurso());
				this.idEstado = this.turma.getUf().toString();
				this.idCidade = this.turma.getCidade().toString();
				listaCursos();
				listaInstrutores();
				resultado = "cadastrar-turma.jsf?faces-redirect=true";
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(resultado);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Falha ao Excluir!", "."));
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

	public List<Turma> listar() {

		setTurmas(new ArrayList<Turma>());
		if (turma != null) {
			this.turma.setCurso(this.cursoSelecionado);
			this.turma.setInstrutor(this.instrutorSelecionado);
			this.turmas = turmaModel.listar(this.turma);
			setTurmaAluno(new TurmaAluno());
			setAlunos(new ArrayList<Aluno>());
			setAluno(new Aluno());
			int i = 0;
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

	public void tagCloud() {

		List<Turma> listaTotaisPorCurso = listaTotaisPorCurso();
		tagCloudModel = new DefaultTagCloudModel();
		for (Turma turma : listaTotaisPorCurso) {
			tagCloudModel.addTag(new DefaultTagCloudItem(turma.getCurso()
					.getNome(), "#", turma.getTotal().intValue()));
		}

	}

	public void createPieModels() {
		createPieModel();
	}

	private void createPieModel() {

		pieModel = new PieChartModel();
		List<Turma> listaTotaisPorCurso = listaTotaisPorCurso();
		for (Turma turma : listaTotaisPorCurso) {
			pieModel.set(turma.getCurso().getNome(), turma.getTotal().intValue());
		}

		pieModel.setTitle("Totais Turmas por Cursos");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);

	}

	public List<Turma> listaTotaisPorCurso() {

		setTurmas(new ArrayList<Turma>());
		List<Turma> listaTotaisPorCurso = turmaModel.listaTotaisPorCurso();
		return listaTotaisPorCurso;
	}

	public String salvar() {

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
			setTurmas(new ArrayList<Turma>());
			setTurmasAluno(new ArrayList<TurmaAluno>());
			setAcaoDeInclusao(false);
			listaTurmas();
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Gravado com sucesso!", "..."));
			resultado = "pesquisar-turma.jsf?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(resultado);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Falha ao Gravar!", "."));
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}

	public String alterar() {

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
			setTurmas(new ArrayList<Turma>());
			setTurmasAluno(new ArrayList<TurmaAluno>());
			setAcaoDeInclusao(false);
			listaTurmas();
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Gravado com sucesso!", "."));
			resultado = "pesquisar-turma.jsf?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(resultado);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Falha ao Gravar!", "."));
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Turma> listaTurmas() {
		this.turmas = turmaModel.listaTurmas();
		return this.turmas;
	}

	public List<Curso> listaCursos() {
		this.cursos = cursoModel.listaCursos();
		return this.cursos;
	}

	public List<Instrutor> listaInstrutores() {
		this.instrutores = instrutorModel.listaInstrutores();
		return this.instrutores;
	}

	public List<Cidade> consultaCidadePorUf() {
		this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
		this.cidades = cidadeModel.consultaCidadePorUf(this.estadoSelecionado);
		return this.cidades;
	}

	public List<Cidade> listaCidades() {
		this.cidades = cidadeModel.listaCidades();
		return this.cidades;
	}

	public List<Estado> listaEstados() {
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
		// listaCidades();
		listaEstados();
		return "cadastrar-turma.jsf?faces-redirect=true";

	}

	public void cancelar() {
		setTurma(new Turma());
		setturmas(null);
		setAcaoDeInclusao(false);
		setMostrarBotaoAlterar(false);
		String resultado = "pesquisar-turma.jsf?faces-redirect=true";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(resultado);
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

	public TagCloudModel getTagCloudModel() {
		return tagCloudModel;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public void setTagCloudModel(TagCloudModel tagCloudModel) {
		this.tagCloudModel = tagCloudModel;
	}

}