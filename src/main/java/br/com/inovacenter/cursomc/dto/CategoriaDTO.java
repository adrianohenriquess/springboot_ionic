package br.com.inovacenter.cursomc.dto;

import java.io.Serializable;

import br.com.inovacenter.cursomc.entity.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 6311485579935094719L;
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
