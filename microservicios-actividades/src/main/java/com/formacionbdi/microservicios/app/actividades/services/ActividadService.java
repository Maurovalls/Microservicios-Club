package com.formacionbdi.microservicios.app.actividades.services;

import java.util.List;


import com.formacionbdi.microservicios.generic.actividades.models.entity.Actividad;
import com.formacionbdi.microservicios.genericclub.services.BaseService;

public interface ActividadService extends BaseService<Actividad> {
	List<Actividad> searchActividades(String filtro) throws Exception;
}
