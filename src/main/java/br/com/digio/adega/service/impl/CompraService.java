package br.com.digio.adega.service.impl;

import br.com.digio.adega.entity.Compra;
import br.com.digio.adega.repository.CompraRepository;
import br.com.digio.adega.service.ICompraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompraService implements ICompraService {

    private final CompraRepository compraRepository;

    @Override
    public List<Compra> saveAll(List<Compra> compras) {
        return compraRepository.saveAll(compras);
    }
}
