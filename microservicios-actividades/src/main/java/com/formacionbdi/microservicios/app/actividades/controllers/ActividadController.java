package com.formacionbdi.microservicios.app.actividades.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.actividades.services.ActividadService;
import com.formacionbdi.microservicios.generic.actividades.models.entity.Actividad;
import com.formacionbdi.microservicios.generic.actividades.models.entity.Horario;
import com.formacionbdi.microservicios.genericclub.controllers.BaseController;

@RestController
public class ActividadController extends BaseController<Actividad, ActividadService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Actividad actividad, BindingResult result, @PathVariable Long id){
		if(result.hasErrors()) {
			return this.validarAtributo(result);
		}
		Optional <Actividad> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Actividad actividadDb = o.get();
		actividadDb.setNombre(actividad.getNombre());
		
		
		List<Horario> eliminadas = actividadDb.getHorarios()
		.stream()
		.filter(pdb -> !actividad.getHorarios().contains(pdb))
		.collect(Collectors.toList());
		
		eliminadas.forEach(actividadDb::removeHorario);	
		actividadDb.setHorarios(actividad.getHorarios());		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(actividadDb));
	}
	
	@GetMapping("/searchActividades")
	public ResponseEntity<?> search(@RequestParam String filtro){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.searchActividades(filtro));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
		}
	}
	
}
