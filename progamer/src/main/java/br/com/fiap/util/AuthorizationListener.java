package br.com.fiap.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.model.User;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		if (SessionContextUtil.getCurrentPage().equals("/login.xhtml"))
			return;

		User user = SessionContextUtil.getSessionUser();
		if (user != null)
			return;

		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigator = context.getApplication().getNavigationHandler();
		navigator.handleNavigation(context, null, "login?faces-redirect=true");
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
