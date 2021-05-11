package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.UserDAO;
import br.com.fiap.model.User;

@Named
@RequestScoped
public class UserBean {

	private User user = new User();

	public void save() {
		new UserDAO().save(this.user);
		user = new User();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User successfully registered"));
		System.out.println("Saving user..." + this.user);
	}

	public List<User> getUsers() {
		return new UserDAO().getAll();
	}

	public void execute() {
		System.out.println("Trigger bean");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login() {

		boolean exist = new UserDAO().exist(this.user);
		if (exist) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
			return "index?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido", "Erro"));

			return "login?faces-redirect=true";
		}

	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
		return "login?faces-redirect=true";
	}

}
