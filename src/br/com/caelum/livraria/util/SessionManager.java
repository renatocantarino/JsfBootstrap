package br.com.caelum.livraria.util;

import javax.faces.context.FacesContext;

public class SessionManager {

	public static void setSession(String key, Object entity) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.getExternalContext().getSessionMap().put(key, entity);

	}

	public static Object getSession(String key) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		return ctx.getExternalContext().getSessionMap().get(key);
	}

	public static void clearSession(String key) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.getExternalContext().getSessionMap().remove(key);
	}

}
