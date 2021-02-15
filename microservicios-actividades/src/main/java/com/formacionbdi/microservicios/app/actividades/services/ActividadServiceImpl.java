package com.formacionbdi.microservicios.app.actividades.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.actividades.models.repository.ActividadRepository;
import com.formacionbdi.microservicios.generic.actividades.models.entity.Actividad;
import com.formacionbdi.microservicios.genericclub.services.BaseServiceImpl;

@Service
public class ActividadServiceImpl extends BaseServiceImpl<Actividad, ActividadRepository> implements ActividadService {
	
	@Override
	public List<Actividad> searchActividades(String filtro) throws Exception {
		try {
			List<Actividad> actividades = repository.searchActividades(filtro);
			return actividades;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Actividad> findAll() {
		return repository.findAllByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Actividad> findAll(Pageable pageable) {
		return repository.findAllByOrderByIdAsc(pageable);
	}

}
