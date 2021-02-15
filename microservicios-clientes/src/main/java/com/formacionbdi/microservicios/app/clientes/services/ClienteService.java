package com.formacionbdi.microservicios.app.clientes.services;


import java.util.List;


import com.formacionbdi.microservicios.app.clientes.models.entity.Cliente;
import com.formacionbdi.microservicios.genericclub.services.BaseService;

public interface ClienteService extends BaseService<Cliente> {
	List<Cliente> searchClientes(String filtro) throws Exception;
}
