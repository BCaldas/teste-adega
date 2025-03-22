package br.com.digio.adega.service;

import br.com.digio.adega.entity.Produto;
import br.com.digio.adega.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto save(Produto cliente) {
        return produtoRepository.save(cliente);
    }
}
