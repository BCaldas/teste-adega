package br.com.digio.adega.service;

import br.com.digio.adega.domain.entity.Compra;
import br.com.digio.adega.repository.model.PurchasedWine;

import java.util.List;

public interface ICompraService {
    List<Compra> saveAll(List<Compra> compras);

    List<Compra> getAllOrderedByValue();

    Compra getTopCompraByAno(Short ano);

    List<PurchasedWine> getMostPurchasedWineTypeByClient(Integer clientId);
}
