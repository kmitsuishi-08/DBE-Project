package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.UserDAO;
import br.com.fiap.model.User;
import br.com.fiap.util.SessionContextUtil;

@Named
@RequestScoped
public class UserBean {

	private User user = new User();
	private FacesContext context;

	public UserBean() {
		context = FacesContext.getCurrentInstance();
	}

	public void save() {
		new UserDAO().save(this.user);
		this.user = new User();
		context.addMessage(null, new FacesMessage("User successfully registered"));
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
		user = new UserDAO().getUserByEmailAndPassword(user.getEmail(), user.getPassword());
		if (user != null) {
			context.getExternalContext().getSessionMap().put("user", user);
			return "index?faces-redirect=true";
		} else {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido", "Erro"));
			return "login?faces-redirect=true";
		}
	}

	public String logout() {
		SessionContextUtil.removeUserSession();
		return "login?faces-redirect=true";
	}

}
