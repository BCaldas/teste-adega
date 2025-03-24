package br.com.digio.adega.service.impl;

import br.com.digio.adega.domain.entity.Compra;
import br.com.digio.adega.exception.ResourceNotFoundException;
import br.com.digio.adega.repository.CompraRepository;
import br.com.digio.adega.service.ICompraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<Compra> getAllOrderedByValue() {
        return compraRepository.findAllByOrderByValorTotalAsc();
    }

    @Override
    public Compra getTopCompraByAno(Short ano) {
        return compraRepository.findFirstByProdutoAnoCompraOrderByValorTotalDesc(ano).orElseThrow(() -> {
            var msg = "Compra não encontrada para o ano " + ano;
            log.error(msg);
            return new ResourceNotFoundException(msg);
        });
    }

    //TODO: Teste
    @Override
    public List<String> getRandomMostPurchasedWineTypeByClient(Integer clienteId, Pageable pageable) {
        return compraRepository.findRandomMostPurchasedWineTypeByClient(clienteId, pageable);
    }
}
