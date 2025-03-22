package br.com.digio.adega.service.impl;

import br.com.digio.adega.entity.Produto;
import br.com.digio.adega.exception.ResourceNotFoundException;
import br.com.digio.adega.repository.ProdutoRepository;
import br.com.digio.adega.service.IProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public void saveAll(List<Produto> produtos) {
        produtoRepository.saveAll(produtos);
    }

    @Override
    public Produto getById(Integer id) {
        return produtoRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
