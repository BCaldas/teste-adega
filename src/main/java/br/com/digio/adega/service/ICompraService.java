package br.com.digio.adega.service;

import br.com.digio.adega.domain.dto.CompraDTO;
import br.com.digio.adega.domain.entity.Compra;

import java.util.List;

public interface ICompraService {
    List<Compra> saveAll(List<Compra> compras);

    List<CompraDTO> getAllOrderedByValue();
}
