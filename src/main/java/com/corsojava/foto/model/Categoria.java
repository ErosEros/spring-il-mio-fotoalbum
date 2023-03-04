package com.corsojava.foto.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Categoria {
	
	@Id  // IDENTIFICA LA CHIAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 2, max = 15, message = "Il Nome della Categoria deve contenere un numero di carattari compreso tra 2 e 15.")
	@NotNull(message="Il Nome della Categoria non può essere nullo!!")
	@NotEmpty(message="Il Nome della Categoria essere vuoto!!")
	private String nomeCategoria;

	@ManyToMany(mappedBy = "categorie")
	private List<Foto> foto;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
//	GETTERS AND SETTERS
	
}
