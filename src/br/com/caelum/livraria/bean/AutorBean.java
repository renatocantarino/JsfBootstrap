package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public void loadById() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);

	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer AutorId) {
		autorId = AutorId;
	}

}
