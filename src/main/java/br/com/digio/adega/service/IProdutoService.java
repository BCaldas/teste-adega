package br.com.digio.adega.service;

import br.com.digio.adega.entity.Produto;

import java.util.List;

public interface IProdutoService {
    void saveAll(List<Produto> produtos);

    Produto getById(Integer id);
}
