package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.SetupDAO;
import br.com.fiap.model.Setup;
import br.com.fiap.model.User;
import br.com.fiap.util.SessionContextUtil;

@Named
@RequestScoped
public class SetupBean {

	private Setup setup = new Setup();

	public String save() {
		User user = SessionContextUtil.getSessionUser();
		if (user != null) {
			this.setup.setUser(user);
			new SetupDAO().save(this.setup);
			this.setup = new Setup();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Setup successfully registered"));
			return null;
		}
		return SessionContextUtil.sessionExpired();
	}

	public List<Setup> getSetups() {
		if (SessionContextUtil.getCurrentPage().equals("/index.xhtml"))
			return new SetupDAO().getAll();

		User user = SessionContextUtil.getSessionUser();
		return new SetupDAO().getAllByUser(user);
	}

	public void execute() {
		System.out.println("Trigger bean");
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}
}
