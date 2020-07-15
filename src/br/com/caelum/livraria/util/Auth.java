package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Auth implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext facesContext = event.getFacesContext();

		String pageName = facesContext.getViewRoot().getViewId();

		if ("/login.xhtml".equals(pageName))
			return;

		Usuario usuario = (Usuario) new SessionManager().getSession("usuarioLogado");

		if (usuario != null)
			return;

		NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
		handler.handleNavigation(facesContext, null, "/login?faces-redirect=true");
		facesContext.renderResponse();

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;

	}

}
