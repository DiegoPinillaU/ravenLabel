package com.raven.label.springboot.app.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vacas")
public class Vaca implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String UrlGaleriaFotos;
	
	@OneToMany(mappedBy = "vaca")
	private List<Run> runs;
	
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlGaleriaFotos() {
		return UrlGaleriaFotos;
	}

	public void setUrlGaleriaFotos(String urlGaleriaFotos) {
		UrlGaleriaFotos = urlGaleriaFotos;
	}
	
	
	

}
