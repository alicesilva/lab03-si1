package br.com.api.lab03.model;


import java.util.Set;

import javax.persistence.*;

@Entity
public class UserSystem {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String password;
	private String email;
	
	 @ManyToMany
	private Set<Serie> seriesProfile;
	 
	 

	@ManyToMany
		private Set<Serie> seriesWacthList;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Set<Serie> getSeriesProfile() {
		return seriesProfile;
	}

	public void setSeriesProfile(Set<Serie> seriesProfile) {
		this.seriesProfile = seriesProfile;
	}

	public Set<Serie> getSeriesWacthList() {
		return seriesWacthList;
	}

	public void setSeriesWacthList(Set<Serie> seriesWacthList) {
		this.seriesWacthList = seriesWacthList;
	}
	
	

}
