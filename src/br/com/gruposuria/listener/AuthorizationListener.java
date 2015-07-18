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
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Object usuario = session.getAttribute("usuario");
		Object usuarioLogado = session.getAttribute("usuarioLogado");

		if (!isLoginPage && usuario == null) {
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "loginPage");
		}
	}

	public void beforePhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}