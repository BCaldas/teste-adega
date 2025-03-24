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

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;
    
    private final ICompraService compraService;

    @Override
    public List<Produto> saveAll(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
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

        //TODO: Caso mais de uma compra do cliente seja da mesma quantidade, randomizar a compra escolhida para basear a recomendação

        log.info("Buscando os tipos de vinhos comprados pelo cliente " + clienteId);
        var purchasedWines = compraService.getMostPurchasedWineTypeByClient(clienteId);

        if (purchasedWines.isEmpty()) {
            log.info("Não foi encontrada nenhuma compra para o cliente " + clienteId);
            throw new ResourceNotFoundException("Não foi possível encontrar uma recomendação. Cliente não possui compras");
        }

        var mostPurhasedType = purchasedWines.get(0);

        return getRandomWineByType(mostPurhasedType.tipoVinho());
    }

    private Produto getRandomWineByType(String tipoVinho) {

        var produtosReturned = produtoRepository.findRandomWineByType(tipoVinho, Pageable.ofSize(1));

        if (produtosReturned.isEmpty()) {
            var msg = "Nenhum vinho encontrado para o tipo " + tipoVinho;
            log.error(msg);
            throw new ResourceNotFoundException(msg);
        }

        return produtosReturned.get(0);
    }
}
