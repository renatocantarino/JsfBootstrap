package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.SessionManager;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {

		if (new DAO<Usuario>(Usuario.class).existe(this.usuario)) {
			 SessionManager.setSession("usuarioLogado", this.usuario);
			return "livros/lista?faces-redirect=true";

		}

		FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage("Usuario não encontrado"));
		return "login";
	}

	public String logOut() {

		SessionManager.clearSession("usuarioLogado");
		return "/login?faces-redirect=true";

	}

}
