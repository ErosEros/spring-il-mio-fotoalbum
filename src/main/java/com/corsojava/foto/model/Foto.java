package com.corsojava.foto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Foto {

	@Id  // IDENTIFICA LA CHIAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 2, max = 15, message = "Il titolo deve contenere un numero di carattari compreso tra 2 e 15.")
	@NotNull(message="Il titolo non può essere nullo!!")
	@NotEmpty(message="Il titolo non può essere vuoto!!")
	private String titolo ;

	@Size(min = 5, max = 255, message = "La descrizione deve contenere un numero di carattari compreso tra 5 e 255.")
	@NotNull(message="La descrizione non può essere nulla!!")
	@NotEmpty(message="La descrizione non può essere vuota!!")
	private String descrizione;
	
	@NotNull(message="L'URL non può essere nulla!!")
	@NotEmpty(message="L'URL non può essere vuota!!")
	private String url;
	
	@Size(min = 2, max = 20, message = "Il tag deve contenere un numero di carattari compreso tra 2 e 20.")
	@NotNull(message="Il tag non può essere nullo!!")
	@NotEmpty(message="Il tag non può essere vuoto!!")
	private String tag;
	
	
	private boolean visibile;
	
//	GETTERS AND SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isVisibile() {
		return visibile;
	}

	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}

}
