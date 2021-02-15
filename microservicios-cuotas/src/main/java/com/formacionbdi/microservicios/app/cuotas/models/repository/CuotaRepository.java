package com.formacionbdi.microservicios.app.cuotas.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.formacionbdi.microservicios.generic.cuotas.models.entity.Cuota;

public interface CuotaRepository extends PagingAndSortingRepository<Cuota,Long> {
	@Query(value = "Select c FROM Cuota c WHERE c.nombreSocio LIKE %:filtro%")
	List<Cuota> searchCuotas(@Param("filtro")String filtro);
	public Iterable<Cuota> findAllByOrderByIdAsc();
	public Page<Cuota> findAllByOrderByIdAsc(Pageable pageable);
}
