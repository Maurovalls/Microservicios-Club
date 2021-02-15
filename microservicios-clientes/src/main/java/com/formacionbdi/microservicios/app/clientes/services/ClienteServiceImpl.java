package com.formacionbdi.microservicios.app.clientes.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.clientes.models.entity.Cliente;
import com.formacionbdi.microservicios.app.clientes.models.repository.ClienteRepository;
import com.formacionbdi.microservicios.genericclub.services.BaseServiceImpl;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, ClienteRepository> implements ClienteService {

	@Override
	public List<Cliente> searchClientes(String filtro) throws Exception {
		try {
			List<Cliente> clientes = repository.searchClientes(filtro);
			return clientes;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Cliente> findAll() {
		return repository.findAllByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Cliente> findAll(Pageable pageable) {
		return repository.findAllByOrderByIdAsc(pageable);
	}
	
}
