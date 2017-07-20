package br.com.api.lab03.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Serie {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String image;
	private String year;
	private int nota;
	private int lastChapter;
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public int getLastChapter() {
		return lastChapter;
	}
	public void setLastChapter(int lastChapter) {
		this.lastChapter = lastChapter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	

}
