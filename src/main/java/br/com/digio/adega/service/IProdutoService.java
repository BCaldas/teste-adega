package br.com.digio.adega.service;

import br.com.digio.adega.domain.entity.Produto;

import java.util.List;

public interface IProdutoService {
    List<Produto> saveAll(List<Produto> produtos);

    Produto getById(Integer id);

    Produto recommendWineToClient(Integer clienteId);
}
