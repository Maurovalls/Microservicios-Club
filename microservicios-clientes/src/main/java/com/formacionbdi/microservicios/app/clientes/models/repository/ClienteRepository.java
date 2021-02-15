package com.formacionbdi.microservicios.app.clientes.models.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.formacionbdi.microservicios.app.clientes.models.entity.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente,Long> {
	@Query(value = "Select c FROM Cliente c WHERE c.nombre LIKE %:filtro% OR c.apellido LIKE %:filtro%")
	List<Cliente> searchClientes(@Param("filtro")String filtro);
	public Iterable<Cliente> findAllByOrderByIdAsc();
	public Page<Cliente> findAllByOrderByIdAsc(Pageable pageable);
}
