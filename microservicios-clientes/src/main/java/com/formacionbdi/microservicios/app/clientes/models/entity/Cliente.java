package com.formacionbdi.microservicios.app.clientes.models.entity;

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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formacionbdi.microservicios.generic.actividades.models.entity.Actividad;
import com.formacionbdi.microservicios.generic.cuotas.models.entity.Cuota;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Setter
@Getter
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	@Column(name = "apellido")
	@NotEmpty
	private String apellido;
	@Column(name = "telefono")
	@NotEmpty
	private String telefono;
	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;	
	@Lob
	@JsonIgnore
	private byte[] foto;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cuota> cuotas;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Actividad> actividades;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Cliente() {
		this.cuotas = new ArrayList<>();
		this.actividades = new ArrayList<>();
	}
	
	public void addCuota(Cuota cuota) {
		this.cuotas.add(cuota);
	}
	
	public void removeCuota(Cuota cuota) {
		this.cuotas.remove(cuota);
	}
	
	public void addActividad(Actividad actividad) {
		this.actividades.add(actividad);
	}
	
	public void removeActividad(Actividad actividad) {
		this.actividades.remove(actividad);
	}

	public Integer getFotoHashCode() {
		return (this.foto != null)? this.foto.hashCode():null;
	}

}
