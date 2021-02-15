package com.formacionbdi.microservicios.app.actividades.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.formacionbdi.microservicios.generic.actividades.models.entity.Actividad;

public interface ActividadRepository extends PagingAndSortingRepository<Actividad,Long> {
	@Query(value = "Select a FROM Actividad a WHERE a.nombre LIKE %:filtro%")
	List<Actividad> searchActividades(@Param("filtro")String filtro);
	public Iterable<Actividad> findAllByOrderByIdAsc();
	public Page<Actividad> findAllByOrderByIdAsc(Pageable pageable);
}
