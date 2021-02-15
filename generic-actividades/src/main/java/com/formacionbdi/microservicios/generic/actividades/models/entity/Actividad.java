package com.formacionbdi.microservicios.generic.actividades.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "actividades")
public class Actividad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;
	
	@JsonIgnoreProperties(value = {"actividad"}, allowSetters = true)
	@OneToMany(mappedBy = "actividad",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)	
	private List<Horario> horarios;

	public Actividad() {
		this.horarios = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios.clear();
		horarios.forEach(this::addHorario);
	}

	public void addHorario(Horario horario) {		
		this.horarios.add(horario);
		horario.setActividad(this);
	}
	
	public void removeHorario(Horario horario) {
		this.horarios.remove(horario);
		horario.setActividad(null);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Actividad)) {
			return false;
		}
		Actividad a = (Actividad)obj;
		return this.id != null && this.id.equals(a.getId());
	}
	
}
