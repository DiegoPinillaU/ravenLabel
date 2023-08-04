package com.raven.label.springboot.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "videofiles")
public class VideoFile implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String serverPath;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime fechaInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime fechaTermino;

	private String channel;
	
	private int revisado;
	
	private static final long serialVersionUID = 1L;
	

	@OneToMany(mappedBy = "videoFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EventoClasificado> eventosClasificados;


	@ManyToMany
	@JoinTable(name = "video_signal",
	joinColumns = {
			@JoinColumn(name = "video_file_id")},
	inverseJoinColumns = {
			@JoinColumn(name = "reference_signal_file_id")
			}
	)
	private List<ReferenceSignalFile> referenceSignalFiles;

	

	public int getRevisado() {
		return revisado;
	}

	public void setRevisado(int revisado) {
		this.revisado = revisado;
	}

	public List<ReferenceSignalFile> getReferenceSignalFiles() {
		return referenceSignalFiles;
	}

	public void setReferenceSignalFiles(List<ReferenceSignalFile> referenceSignalFiles) {
		this.referenceSignalFiles = referenceSignalFiles;
	}

	public List<EventoClasificado> getEventosClasificados() {
		return eventosClasificados;
	}

	public void setEventosClasificados(List<EventoClasificado> eventosClasificados) {
		this.eventosClasificados = eventosClasificados;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(LocalDateTime fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}




}
