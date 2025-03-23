package br.com.digio.adega.service.impl;

import br.com.digio.adega.domain.entity.Cliente;
import br.com.digio.adega.repository.ClienteRepository;
import br.com.digio.adega.service.IClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getTop3Clientes() {
        return clienteRepository.findTopLoyalClientes(Pageable.ofSize(3));
    }
}
