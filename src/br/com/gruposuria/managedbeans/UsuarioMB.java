package br.com.gruposuria.managedbeans;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.gruposuria.entity.Usuario;
import br.com.gruposuria.model.UsuarioModel;

@ManagedBean(name="usuarioMB")
@SessionScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioMB {

	@Inject
	UsuarioModel usuarioModel;

	private Usuario usuario;
	private boolean usuarioLogado;

	/*public Usuario consultar() {

		List<Usuario> listaTodos = usuarioDAO.listaTodos();
		System.out.println("teste");
		// this.usuario =

		return usuario;
	}*/

	/*@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Usuario salvar() {
		try {
			// usuarioDAO.getEntityManager().getTransaction().begin();
			this.usuario = usuarioDAO.incluir(usuario);
			// usuarioDAO.getEntityManager().getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Mensagem", "Gravado com sucesso!"));
		} catch (Exception e) {
			// usuarioDAO.getEntityManager().getTransaction().rollback();
			System.out.println("Erro: " + e.getMessage());
		}
		return this.usuario;
	}*/

	/*public List<Usuario> listaUsuarios() {
		return usuarioDAO.listaTodos();
	}*/

	/*public void cancelar() {
		System.out.println("Cancelar");

		// FacesContext.getCurrentInstance().addMessage(null, new
		// FacesMessage("Mensagem", "Cancelar: ") );
	}*/

	public String efetuaLogin() {
		
		if (usuarioModel.loginValido(usuario)) {
			
			this.usuarioLogado = true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário Logado com Sucesso!") );
			return "default.jsf?faces-redirect=true";
		} else {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acesso Negado!") );
			return "Usuario nao encontrado";
		}

	}

	public String efetuaLogout() {
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		setUsuario(null);
		setUsuarioLogado(false);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
		return "default.jsf?faces-redirect=true";
		
	}
	
	public Usuario getUsuario() {
		setUsuario(new Usuario());
		this.usuario.setLogin("awathier");
		this.usuario.setSenha("12345");
		if (usuario == null) {
			usuario = new Usuario();
		}

		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(boolean usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
