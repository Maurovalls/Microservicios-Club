package com.formacionbdi.microservicios.app.cuotas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.cuotas.models.repository.CuotaRepository;
import com.formacionbdi.microservicios.generic.cuotas.models.entity.Cuota;
import com.formacionbdi.microservicios.genericclub.services.BaseServiceImpl;

@Service
public class CuotaServiceImpl extends BaseServiceImpl<Cuota, CuotaRepository> implements CuotaService {
	
	@Override
	public List<Cuota> searchCuotas(String filtro) throws Exception {
		try {
			List<Cuota> cuotas = repository.searchCuotas(filtro);
			return cuotas;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Cuota> findAll() {
		return repository.findAllByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Cuota> findAll(Pageable pageable) {
		return repository.findAllByOrderByIdAsc(pageable);
	}
	
}
