package br.com.digio.adega.service;

import br.com.digio.adega.entity.Compra;

import java.util.List;

public interface ICompraService {
    List<Compra> saveAll(List<Compra> compras);
}
