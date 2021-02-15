package com.formacionbdi.microservicios.app.cuotas.controllers;

import java.util.Optional;

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

import com.formacionbdi.microservicios.app.cuotas.services.CuotaService;
import com.formacionbdi.microservicios.generic.cuotas.models.entity.Cuota;
import com.formacionbdi.microservicios.genericclub.controllers.BaseController;

@RestController
public class CuotaController extends BaseController<Cuota, CuotaService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Cuota cuota, BindingResult result, @PathVariable Long id){
		if(result.hasErrors()) {
			return this.validarAtributo(result);
		}
		Optional<Cuota> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cuota cuotaDb = o.get();
		cuotaDb.setNombreSocio(cuota.getNombreSocio());
		cuotaDb.setImporte(cuota.getImporte());
		cuotaDb.setDescripcion(cuota.getDescripcion());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cuotaDb));
	}
	
	@GetMapping("/searchCuotas")
	public ResponseEntity<?> search(@RequestParam String filtro){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.searchCuotas(filtro));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
		}
	}

}
