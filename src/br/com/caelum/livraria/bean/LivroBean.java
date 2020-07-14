package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import sun.security.validator.ValidatorException;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();

	private Integer autorId;

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		temAutor();
		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

	private void temAutor() {
		if (livro.getAutores().isEmpty())
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Informe o autor"));
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
		return "autor?faces-redirect=true";
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
