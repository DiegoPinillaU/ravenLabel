package com.raven.label.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "runs")
public class Run implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date fechaPuesta;
	
	private Date fechaApagado;
	
	
	@OneToMany(mappedBy = "run")
	private List<ReferenceSignalFile> referenceSignalFiles;


	@OneToMany(mappedBy = "run")
	private List<RawSignalFile> rawSignalFiles;
	
	@ManyToOne
	@JoinColumn(name = "vaca_id")
	private Vaca vaca;
	
	
	
	
	
	public Vaca getVaca() {
		return vaca;
	}

	public void setVaca(Vaca vaca) {
		this.vaca = vaca;
	}

	public List<RawSignalFile> getRawSignalFiles() {
		return rawSignalFiles;
	}

	public void setRawSignalFiles(List<RawSignalFile> rawSignalFiles) {
		this.rawSignalFiles = rawSignalFiles;
	}

	public List<ReferenceSignalFile> getReferenceSignalFiles() {
		return referenceSignalFiles;
	}

	public void setReferenceSignalFiles(List<ReferenceSignalFile> referenceSignalFiles) {
		this.referenceSignalFiles = referenceSignalFiles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaPuesta() {
		return fechaPuesta;
	}

	public void setFechaPuesta(Date fechaPuesta) {
		this.fechaPuesta = fechaPuesta;
	}

	public Date getFechaApagado() {
		return fechaApagado;
	}

	public void setFechaApagado(Date fechaApagado) {
		this.fechaApagado = fechaApagado;
	}
	
	
	
	
	

}
