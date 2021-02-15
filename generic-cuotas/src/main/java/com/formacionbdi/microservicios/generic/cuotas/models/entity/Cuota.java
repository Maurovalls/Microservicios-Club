package com.formacionbdi.microservicios.generic.cuotas.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuotas")
@Setter
@Getter
public class Cuota {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre_socio")
	@NotEmpty
	private String nombreSocio;
	@Column(name = "importe")
	private int importe;
	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Cuota)) {
			return false;
		}
		Cuota c = (Cuota)obj;
		return this.id != null && this.id.equals(c.getId());
	}	
	
}
