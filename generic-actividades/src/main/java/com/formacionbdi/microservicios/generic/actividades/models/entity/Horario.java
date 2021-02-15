package com.formacionbdi.microservicios.generic.actividades.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "horarios")
@Setter
@Getter
public class Horario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "dia_hora")
	private String diaHora;
	@JsonIgnoreProperties(value = {"horarios"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actividad_id")
	private Actividad actividad;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Horario)) {
			return false;
		}
		Horario a = (Horario)obj;
		return this.id != null && this.id.equals(a.getId());
	}
	
}
