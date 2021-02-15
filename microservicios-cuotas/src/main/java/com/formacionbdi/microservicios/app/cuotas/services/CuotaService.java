package com.formacionbdi.microservicios.app.cuotas.services;

import java.util.List;

import com.formacionbdi.microservicios.generic.cuotas.models.entity.Cuota;
import com.formacionbdi.microservicios.genericclub.services.BaseService;

public interface CuotaService extends BaseService<Cuota> {
	List<Cuota> searchCuotas(String filtro)throws Exception;
}
