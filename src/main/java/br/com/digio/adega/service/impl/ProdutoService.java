package br.com.digio.adega.service.impl;

import br.com.digio.adega.domain.entity.Produto;
import br.com.digio.adega.exception.ResourceNotFoundException;
import br.com.digio.adega.repository.ProdutoRepository;
import br.com.digio.adega.service.ICompraService;
import br.com.digio.adega.service.IProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;
    
    private final ICompraService compraService;

    @Override
    public void saveAll(List<Produto> produtos) {
        produtoRepository.saveAll(produtos);
    }

    @Override
    public Produto getById(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> {
            var msg = "Nenhum produto encontrado para o id " + id;
            log.error(msg);
            return new ResourceNotFoundException(msg);
        });
    }

    @Override
    public Produto recommendWineToClient(Integer clienteId) {

        //TODO: Caso todas as compras do cliente sejam da mesma quantidade, randomizar a compra escolhida para basear a recomendação

        log.info("Buscando os tipos de vinhos comprados pelo cliente " + clienteId);
        var purchasedWines = compraService.getMostPurchasedWineTypeByClient(clienteId);

        if (purchasedWines.isEmpty()) {
            log.info("Não foi encontrada nenhuma compra para o cliente " + clienteId);
            throw new ResourceNotFoundException("Não foi possível encontrar uma recomendação");
        }

        var mostPurhasedType = purchasedWines.get(0);

        return getRandomWineByType(mostPurhasedType.tipoVinho());
    }

    private Produto getRandomWineByType(String tipoVinho) {

        return Optional.of(produtoRepository.findRandomWineByType(tipoVinho, Pageable.ofSize(1)).get(0)).orElseThrow(() -> {
            var msg = "Nenhum vinho encontrado para o tipo " + tipoVinho;
            log.error(msg);
            return new ResourceNotFoundException(msg);
        });
    }
}
