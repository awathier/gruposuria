package br.com.gruposuria.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	public void afterPhase(PhaseEvent event) {

		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();

		boolean isLoginPage = (currentPage.lastIndexOf("default.xhtml") > -1);
		boolean isAgendaPage = (currentPage.lastIndexOf("agenda.xhtml") > -1);
		boolean isPreInscricaoPage = (currentPage.lastIndexOf("preInscricao.xhtml") > -1);
		boolean isInscricaoPFPage = (currentPage.lastIndexOf("inscricaoPF.xhtml") > -1);
		boolean isInscricaoPJPage = (currentPage.lastIndexOf("inscricaoPJ.xhtml") > -1);
		boolean isResultadoInscricaoPage = (currentPage.lastIndexOf("resultado-inscricao.xhtml") > -1);
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Object usuario = session.getAttribute("usuario");
		Object usuarioLogado = session.getAttribute("usuarioLogado");
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);

		if ((!isLoginPage && usuario == null) 
				&& (!isAgendaPage) 
				&& (!isPreInscricaoPage) 
				&& (!isInscricaoPFPage)
				&& (!isInscricaoPJPage) 
				&& (!isResultadoInscricaoPage)) {
			
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "loginPage");
			//bem, se não está logado redireciona pra lógica que (navigatio rule) atende a loginPage
		} 
		/*else {
			// verificar se o usuario atual tem acesso a página atual.
			boolean temAcesso = usuario.temAcesso(new Pagina(currentPage));
			if (!temAcesso) {
				// aqui a logica de não ter acesso... redicione novamente? faça
				// algo... ???
			}
		}*/
	}

	public void beforePhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}