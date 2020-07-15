package br.com.caelum.livraria.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.SessionManager;
import sun.security.validator.ValidatorException;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();

	private Integer autorId;

	public String gravar() {
		temAutor();
		persist(this.livro);
		
		return "/livros/lista?faces-redirect=true";

	}

	@PostConstruct
	public void Load() {
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("livroEdicao") == null)
			return;

		this.livro = (Livro)  SessionManager.getSession("livroEdicao");	
		SessionManager.clearSession("livroEdicao");	
		
	}

	private void persist(Livro livro) {

		if (livro.getId() == null)
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		else
			new DAO<Livro>(Livro.class).atualiza(this.livro);

	}

	public void remover(Livro livro) {

		new DAO<Livro>(Livro.class).remove(livro);
	}

	public String editar(Livro livro) {
		
		SessionManager.setSession("livroEdicao", livro);
		return "/livros/add?faces-redirect=true";
	}

	private void temAutor() {
		if (livro.getAutores().isEmpty())
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Informe o autor"));
	}

	public List<Livro> getAll() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public void vincularAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);

	}

	public void comecaComDigitoUm(FacesContext facesContext, UIComponent uiComponent, Object value)
			throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1"))
			throw new ValidatorException(new FacesMessage("Erro"));

	}

	public String formAutor() {
		return "autores/add?faces-redirect=true";
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

}
