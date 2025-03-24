package br.com.digio.adega.repository;

import br.com.digio.adega.domain.entity.Compra;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    List<Compra> findAllByOrderByValorTotalAsc();

    Optional<Compra> findFirstByProdutoAnoCompraOrderByValorTotalDesc(Short ano);

//    @Query("SELECT new br.com.digio.adega.repository.model.PurchasedWine(c.produto.tipoVinho, COUNT(c.produto.tipoVinho)) " +
//            "FROM Compra c " +
//            "WHERE c.cliente.id = :clienteId " +
//            "GROUP BY c.produto.tipoVinho " +
//            "ORDER BY COUNT(c.produto.tipoVinho) DESC")
//    List<PurchasedWine> findMostPurchasedWineTypeByClient(@Param("clienteId") Integer clienteId);

    @Query("SELECT p.tipoVinho FROM Produto p " +
            "WHERE p.tipoVinho IN (" +
            "   SELECT p2.tipoVinho FROM Compra c2 " +
            "   JOIN c2.produto p2 " +
            "   WHERE c2.cliente.id = :clienteId " +
            "   GROUP BY p2.tipoVinho " +
            "   HAVING COUNT(c2) = (" +
            "       SELECT MAX(totalComprado) FROM (" +
            "           SELECT COUNT(c3) AS totalComprado FROM Compra c3 " +
            "           JOIN c3.produto p3 " +
            "           WHERE c3.cliente.id = :clienteId " +
            "           GROUP BY p3.tipoVinho" +
            "       )" +
            "   )" +
            ") " +
            "ORDER BY FUNCTION('RAND')")
    List<String> findRandomMostPurchasedWineTypeByClient(@Param("clienteId") Integer clienteId, Pageable pageable);
}
