package br.com.digio.adega.repository;

import br.com.digio.adega.domain.entity.Compra;
import br.com.digio.adega.repository.model.PurchasedWine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    List<Compra> findAllByOrderByValorTotalAsc();

    Optional<Compra> findFirstByProdutoAnoCompraOrderByValorTotalDesc(Short ano);

    @Query("SELECT new br.com.digio.adega.repository.model.PurchasedWine(c.produto.tipoVinho, COUNT(c.produto.tipoVinho)) " +
            "FROM Compra c " +
            "WHERE c.cliente.id = :clienteId " +
            "GROUP BY c.produto.tipoVinho " +
            "ORDER BY COUNT(c.produto.tipoVinho) DESC")
    List<PurchasedWine> findMostPurchasedWineTypeByClient(@Param("clienteId") Integer clienteId);
}
