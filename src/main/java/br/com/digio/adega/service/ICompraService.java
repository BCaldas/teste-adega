package br.com.digio.adega.service;

import br.com.digio.adega.domain.entity.Compra;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompraService {
    List<Compra> saveAll(List<Compra> compras);

    List<Compra> getAllOrderedByValue();

    Compra getTopCompraByAno(Short ano);

    List<String> getRandomMostPurchasedWineTypeByClient(Integer clienteId, Pageable pageable);

//    List<PurchasedWine> getMostPurchasedWineTypeByClient(Integer clientId);
}
