package br.com.digio.adega.service;

import br.com.digio.adega.domain.entity.Cliente;

import java.util.List;

public interface IClienteService {
    Cliente save(Cliente cliente);

    List<Cliente> getTop3Clientes();
}
