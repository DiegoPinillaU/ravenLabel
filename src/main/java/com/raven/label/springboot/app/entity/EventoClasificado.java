package com.raven.label.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "eventosclasificados")
public class EventoClasificado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date horaInicio;
	
	private Date horaFin;
	
	private Date fecha;
	
	private boolean revisado;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "errorEventoClasificado_id")
	private ErrorEventoClasificado errorEventoClasificado;
	
	
	@ManyToOne
	@JoinColumn(name = "tipo_evento_id")
	private TipoEvento tipoEvento;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "video_file_id")
	private VideoFile videoFile;
	

	public VideoFile getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(VideoFile videoFile) {
		this.videoFile = videoFile;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ErrorEventoClasificado getErrorEventoClasificado() {
		return errorEventoClasificado;
	}

	public void setErrorEventoClasificado(ErrorEventoClasificado errorEventoClasificado) {
		this.errorEventoClasificado = errorEventoClasificado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isRevisado() {
		return revisado;
	}

	public void setRevisado(boolean revisado) {
		this.revisado = revisado;
	}
	
	

}
