package br.com.digio.adega.service;

import br.com.digio.adega.entity.Compra;
import br.com.digio.adega.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;

    public Compra save(Compra cliente) {
        return compraRepository.save(cliente);
    }
}
