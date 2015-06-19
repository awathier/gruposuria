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

import br.com.gruposuria.entity.Cidade;
import br.com.gruposuria.entity.Estado;
import br.com.gruposuria.entity.Instituicao;
import br.com.gruposuria.model.CidadeModel;
import br.com.gruposuria.model.EstadoModel;
import br.com.gruposuria.model.InstituicaoModel;

@ManagedBean
@SessionScoped
public class InstituicaoMB {

	@Inject
	private InstituicaoModel instituicaoModel;

	@Inject
	private EstadoModel estadoModel;

	@Inject
	private CidadeModel cidadeModel;

	private Instituicao instituicao = new Instituicao();
	private Instituicao instituicaoSelecionada;
	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Cidade> cidades = new ArrayList<Cidade>();

	private Estado estadoSelecionado = new Estado();
	private Cidade cidadeSelecionada = new Cidade();

	private Estado estado = new Estado();
	private Cidade cidade = new Cidade();

	private boolean mostrarBotaoAlterar = false;
	private boolean acaoDeInclusao;

	private String id;
	private String idCidade;
	private String idEstado;

	@PostConstruct
	public void init() {
		listaEstados();
		this.instituicao = new Instituicao();
	}

	public List<Cidade> listaCidades() {
		this.cidades = cidadeModel.listaCidades();
		return this.cidades;
	}

	public List<Estado> listaEstados() {
		this.estados = estadoModel.listaEstados();
		return this.estados;
	}

	public List<Cidade> consultaCidadePorUf() {
		this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
		this.cidades = cidadeModel.consultaCidadePorUf(this.estadoSelecionado);
		return this.cidades;
	}

	public void excluir() {

		String resultado = null;

		try {
			this.instituicao = instituicaoModel.excluir(instituicaoSelecionada);
			if (this.instituicao != null) {
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Excluido com sucesso!", "."));
				setInstituicao(new Instituicao());
				setInstituicoes(new ArrayList<Instituicao>());
				listaInstituicoes();
				setAcaoDeInclusao(false);
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Erro ao Excluir!", "."));
			}
			resultado = "pesquisar-instituicao.jsf?faces-redirect=true";
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
				listaEstados();
				mostrarBotaoAlterar = true;
				this.instituicao = instituicaoModel
						.consultarPorCodigo(instituicaoSelecionada.getCodigo());
				this.cidades.add(this.instituicao.getCidadeInstituicao());
				this.idEstado = this.instituicao.getCidadeInstituicao()
						.getEstado().getIdEstado().toString();
				this.idCidade = this.instituicao.getCidadeInstituicao()
						.getIdCidade().toString();
				resultado = "cadastrar-instituicao.jsf?faces-redirect=true";
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
		setInstituicao(new Instituicao());
		setEstados(new ArrayList<Estado>());
		setIdEstado("");
		setCidades(new ArrayList<Cidade>());
		setIdCidade("");
		setAcaoDeInclusao(false);
	}

	public List<Instituicao> listar() {

		setInstituicoes(new ArrayList<Instituicao>());
		if (instituicao != null) {
			this.instituicao.setCnpj(this.instituicao.getCnpj()
					.replaceAll("\\.", "").replaceAll("\\-", "")
					.replaceAll("/", ""));
			this.instituicao.setNome(this.instituicao.getNome().toUpperCase());
			this.instituicoes = instituicaoModel.listar(this.instituicao);
		}
		setInstituicao(new Instituicao());
		return this.instituicoes;
	}

	public String salvar() {

		String resultado = null;

		try {
			System.out.println("incluir");

			if ((this.instituicao.getNome() != null)
					&& (!"".equals(this.instituicao.getNome()))) {
				this.instituicao.setNome(this.instituicao.getNome()
						.toUpperCase());
			}

			if ((this.instituicao.getCnpj() != null)
					&& (!"".equals(this.instituicao.getCnpj()))) {
				this.instituicao.setCnpj(this.instituicao.getCnpj()
						.replaceAll("\\.", "").replaceAll("\\-", "")
						.replaceAll("/", ""));
			}

			if ((this.instituicao.getInscricaoEstadual() != null)
					&& (!"".equals(this.instituicao.getInscricaoEstadual()))) {
				this.instituicao.setInscricaoEstadual(this.instituicao
						.getInscricaoEstadual().replaceAll("\\.", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getEndereco() != null)
					&& (!"".equals(this.instituicao.getEndereco()))) {
				this.instituicao.setEndereco(this.instituicao.getEndereco()
						.toUpperCase());
			}

			if ((this.instituicao.getNomeResponsavel() != null)
					&& (!"".equals(this.instituicao.getNomeResponsavel()))) {
				this.instituicao.setNomeResponsavel(this.instituicao
						.getNomeResponsavel().toUpperCase());
			}

			if ((this.instituicao.getSetorResponsavel() != null)
					&& (!"".equals(this.instituicao.getSetorResponsavel()))) {
				this.instituicao.setSetorResponsavel(this.instituicao
						.getSetorResponsavel().toUpperCase());
			}

			if ((this.instituicao.getTelefoneResponsavel() != null)
					&& (!"".equals(this.instituicao.getTelefoneResponsavel()))) {
				this.instituicao.setTelefoneResponsavel(this.instituicao
						.getTelefoneResponsavel().replaceAll("\\_", "")
						.replaceAll("\\(", "").replaceAll("\\)", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getCelularResponsavel() != null)
					&& (!"".equals(this.instituicao.getCelularResponsavel()))) {
				this.instituicao.setCelularResponsavel(this.instituicao
						.getCelularResponsavel().replaceAll("\\_", "")
						.replaceAll("\\(", "").replaceAll("\\)", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getEmailResponsavel() != null)
					&& (!"".equals(this.instituicao.getEmailResponsavel()))) {
				this.instituicao.setEmailResponsavel(this.instituicao
						.getEmailResponsavel());
			}

			if ((this.instituicao.getNomeFinanceiro() != null)
					&& (!"".equals(this.instituicao.getNomeFinanceiro()))) {
				this.instituicao.setNomeFinanceiro(this.instituicao
						.getNomeFinanceiro().toUpperCase());
			}

			if ((this.instituicao.getTelefoneFinanceiro() != null)
					&& (!"".equals(this.instituicao.getTelefoneFinanceiro()))) {
				this.instituicao.setTelefoneFinanceiro(this.instituicao
						.getTelefoneFinanceiro().replaceAll("\\_", "")
						.replaceAll("\\(", "").replaceAll("\\)", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getCelularFinanceiro() != null)
					&& (!"".equals(this.instituicao.getCelularFinanceiro()))) {
				this.instituicao.setCelularFinanceiro(this.instituicao
						.getCelularFinanceiro().replaceAll("\\_", "")
						.replaceAll("\\(", "").replaceAll("\\)", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getEmailFinanceiro() != null)
					&& (!"".equals(this.instituicao.getEmailFinanceiro()))) {
				this.instituicao.setEmailFinanceiro(this.instituicao
						.getEmailFinanceiro());
			}

			if ((this.idEstado != null) && (!"".equals(this.idEstado))
					&& (this.idCidade != null) && (!"".equals(this.idCidade))) {
				this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
				this.cidadeSelecionada.setIdCidade(Long.parseLong(idCidade));
				this.cidadeSelecionada.setEstado(estadoSelecionado);
				this.instituicao.setCidadeInstituicao(this.cidadeSelecionada);
			}

			if ((this.instituicao.getCep() != null)
					&& (!"".equals(this.instituicao.getCep()))) {
				this.instituicao.setCep(this.instituicao.getCep()
						.replaceAll("\\.", "").replaceAll("\\-", "")
						.replaceAll("/", ""));
			}

			this.instituicao = instituicaoModel.salvar(this.instituicao);

			setInstituicao(new Instituicao());
			setInstituicoes(new ArrayList<Instituicao>());
			setEstados(new ArrayList<Estado>());
			setIdEstado("");
			setCidades(new ArrayList<Cidade>());
			setIdCidade("");
			setAcaoDeInclusao(false);
			// listaInstituicoes();
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Gravado com sucesso!", "."));
			resultado = "pesquisar-instituicao.jsf?faces-redirect=true";
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

			if ((this.instituicao.getNome() != null)
					&& (!"".equals(this.instituicao.getNome()))) {
				this.instituicao.setNome(this.instituicao.getNome()
						.toUpperCase());
			}

			if ((this.instituicao.getCnpj() != null)
					&& (!"".equals(this.instituicao.getCnpj()))) {
				this.instituicao.setCnpj(this.instituicao.getCnpj()
						.replaceAll("\\.", "").replaceAll("\\-", "")
						.replaceAll("/", ""));
			}

			if ((this.instituicao.getInscricaoEstadual() != null)
					&& (!"".equals(this.instituicao.getInscricaoEstadual()))) {
				this.instituicao.setInscricaoEstadual(this.instituicao
						.getInscricaoEstadual().replaceAll("\\.", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getEndereco() != null)
					&& (!"".equals(this.instituicao.getEndereco()))) {
				this.instituicao.setEndereco(this.instituicao.getEndereco()
						.toUpperCase());
			}

			if ((this.instituicao.getNomeResponsavel() != null)
					&& (!"".equals(this.instituicao.getNomeResponsavel()))) {
				this.instituicao.setNomeResponsavel(this.instituicao
						.getNomeResponsavel().toUpperCase());
			}

			if ((this.instituicao.getSetorResponsavel() != null)
					&& (!"".equals(this.instituicao.getSetorResponsavel()))) {
				this.instituicao.setSetorResponsavel(this.instituicao
						.getSetorResponsavel().toUpperCase());
			}

			if ((this.instituicao.getTelefoneResponsavel() != null)
					&& (!"".equals(this.instituicao.getTelefoneResponsavel()))) {
				this.instituicao.setTelefoneResponsavel(this.instituicao
						.getTelefoneResponsavel().replaceAll("\\_", "")
						.replaceAll("\\(", "").replaceAll("\\)", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getCelularResponsavel() != null)
					&& (!"".equals(this.instituicao.getCelularResponsavel()))) {
				this.instituicao.setCelularResponsavel(this.instituicao
						.getCelularResponsavel().replaceAll("\\_", "")
						.replaceAll("\\(", "").replaceAll("\\)", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getEmailResponsavel() != null)
					&& (!"".equals(this.instituicao.getEmailResponsavel()))) {
				this.instituicao.setEmailResponsavel(this.instituicao
						.getEmailResponsavel());
			}

			if ((this.instituicao.getNomeFinanceiro() != null)
					&& (!"".equals(this.instituicao.getNomeFinanceiro()))) {
				this.instituicao.setNomeFinanceiro(this.instituicao
						.getNomeFinanceiro().toUpperCase());
			}

			if ((this.instituicao.getTelefoneFinanceiro() != null)
					&& (!"".equals(this.instituicao.getTelefoneFinanceiro()))) {
				this.instituicao.setTelefoneFinanceiro(this.instituicao
						.getTelefoneFinanceiro().replaceAll("\\_", "")
						.replaceAll("\\(", "").replaceAll("\\)", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getCelularFinanceiro() != null)
					&& (!"".equals(this.instituicao.getCelularFinanceiro()))) {
				this.instituicao.setCelularFinanceiro(this.instituicao
						.getCelularFinanceiro().replaceAll("\\_", "")
						.replaceAll("\\(", "").replaceAll("\\)", "")
						.replaceAll("\\-", "").replaceAll("/", ""));
			}

			if ((this.instituicao.getEmailFinanceiro() != null)
					&& (!"".equals(this.instituicao.getEmailFinanceiro()))) {
				this.instituicao.setEmailFinanceiro(this.instituicao
						.getEmailFinanceiro());
			}

			if ((this.idEstado != null) && (!"".equals(this.idEstado))
					&& (this.idCidade != null) && (!"".equals(this.idCidade))) {
				this.estadoSelecionado.setIdEstado(Long.parseLong(idEstado));
				this.cidadeSelecionada.setIdCidade(Long.parseLong(idCidade));
				this.cidadeSelecionada.setEstado(estadoSelecionado);
				this.instituicao.setCidadeInstituicao(this.cidadeSelecionada);
			}

			if ((this.instituicao.getCep() != null)
					&& (!"".equals(this.instituicao.getCep()))) {
				this.instituicao.setCep(this.instituicao.getCep()
						.replaceAll("\\.", "").replaceAll("\\-", "")
						.replaceAll("/", ""));
			}

			this.instituicao = instituicaoModel.alterar(this.instituicao);

			setInstituicao(new Instituicao());
			setInstituicoes(new ArrayList<Instituicao>());
			setEstados(new ArrayList<Estado>());
			setIdEstado("");
			setCidades(new ArrayList<Cidade>());
			setIdCidade("");
			setAcaoDeInclusao(false);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Gravado com sucesso!", "."));
			resultado = "pesquisar-instituicao.jsf?faces-redirect=true";
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

	public List<Instituicao> listaInstituicoes() {
		this.instituicoes = instituicaoModel.listaInstituicoes();
		return this.instituicoes;
	}

	public String acaoInclusao() {
		listaEstados();
		setAcaoDeInclusao(true);
		setMostrarBotaoAlterar(false);
		setInstituicao(new Instituicao());
		return "cadastrar-instituicao.jsf?faces-redirect=true";

	}

	public void cancelar() {
		setInstituicao(new Instituicao());
		setInstituicoes(null);
		setEstados(new ArrayList<Estado>());
		setIdEstado("");
		setCidades(new ArrayList<Cidade>());
		setIdCidade("");
		setAcaoDeInclusao(false);
		setMostrarBotaoAlterar(false);
		setCidades(new ArrayList<Cidade>());
		String resultado = "pesquisar-instituicao.jsf?faces-redirect=true";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(resultado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Instituicao getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}

	public void setInstituicaoSelecionada(Instituicao instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public String getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(String idCidade) {
		this.idCidade = idCidade;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
