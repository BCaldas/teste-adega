package br.com.digio.adega.repository;

import br.com.digio.adega.domain.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    List<Compra> findAllByOrderByValorTotalAsc();

    Optional<Compra> findFirstByProdutoAnoCompraOrderByValorTotalDesc(Short ano);
}
