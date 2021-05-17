package br.com.fiap.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.fiap.model.User;

public class SessionContextUtil {

	public static User getSessionUser() {
		return (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}

	public static void removeUserSession() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
	}

	public static String sessionExpired() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sessão expirada", "Erro"));
		removeUserSession();
		return "login?faces-redirect=true";
	}

	public static String getCurrentPage() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}
}
