package com.formacionbdi.microservicios.app.clientes.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formacionbdi.microservicios.app.clientes.models.entity.Cliente;
import com.formacionbdi.microservicios.app.clientes.services.ClienteService;
import com.formacionbdi.microservicios.generic.actividades.models.entity.Actividad;
import com.formacionbdi.microservicios.generic.cuotas.models.entity.Cuota;
import com.formacionbdi.microservicios.genericclub.controllers.BaseController;

@RestController
public class ClienteController extends BaseController<Cliente, ClienteService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Cliente cliente, BindingResult result,@PathVariable Long id){
		if(result.hasErrors()) {
			return this.validarAtributo(result);
		}
		Optional<Cliente> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		clienteDb.setNombre(cliente.getNombre());
		clienteDb.setApellido(cliente.getApellido());
		clienteDb.setDescripcion(cliente.getDescripcion());
		clienteDb.setTelefono(cliente.getTelefono());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteDb));		
	}
	
	@GetMapping("/searchClientes")
	public ResponseEntity<?> search(@RequestParam String filtro){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.searchClientes(filtro));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
		}
	}
	
	@PutMapping("/{id}/asignar-cuotas")
	public ResponseEntity<?> asignarCuotas(@RequestBody List<Cuota> cuotas,@PathVariable Long id){
		Optional<Cliente> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		cuotas.forEach(c ->{
			clienteDb.addCuota(c);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(clienteDb));
	}
	
	@PutMapping("/{id}/eliminar-cuota")
	public ResponseEntity<?> eliminarCuota(@RequestBody Cuota cuota,@PathVariable Long id){
		Optional<Cliente> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		clienteDb.removeCuota(cuota);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(clienteDb));
	}
	
	@PutMapping("/{id}/asignar-actividades")
	public ResponseEntity<?> asignarActividades(@RequestBody List<Actividad> actividades,@PathVariable Long id){
		Optional<Cliente> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		actividades.forEach(c ->{
			clienteDb.addActividad(c);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(clienteDb));
	}
	
	@PutMapping("/{id}/eliminar-actividad")
	public ResponseEntity<?> eliminarCuota(@RequestBody Actividad actividad,@PathVariable Long id){
		Optional<Cliente> o = this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		clienteDb.removeActividad(actividad);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(clienteDb));
	}
	
	@PostMapping("/crear-foto")
	public ResponseEntity<?> crearFotoCliente(@Valid Cliente cliente,
			BindingResult result,@RequestParam MultipartFile archivo) throws IOException{
		if(!archivo.isEmpty()) {
			cliente.setFoto(archivo.getBytes());
		}
		return super.crear(cliente, result);
	}
	
	@PutMapping("/editar-foto/{id}")
	public ResponseEntity<?> editarFotoCliente(@Valid Cliente cliente, BindingResult result,
			@PathVariable Long id,@RequestParam MultipartFile archivo) throws IOException{
		if(result.hasErrors()) {
			return this.validarAtributo(result);
		}
		Optional<Cliente> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		clienteDb.setNombre(cliente.getNombre());
		clienteDb.setApellido(cliente.getApellido());
		clienteDb.setDescripcion(cliente.getDescripcion());
		clienteDb.setTelefono(cliente.getTelefono());
		if(!archivo.isEmpty()) {
			clienteDb.setFoto(archivo.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteDb));
	}
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		Optional<Cliente> o = service.findById(id);
		if(!o.isPresent() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}
	
}	


