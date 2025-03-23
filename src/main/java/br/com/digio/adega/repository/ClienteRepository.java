package br.com.digio.adega.repository;

import br.com.digio.adega.domain.entity.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c " +
            "LEFT JOIN c.compras co " +
            "GROUP BY c.id, c.nome " +
            "ORDER BY SUM(co.valorTotal) DESC")
    List<Cliente> findTopLoyalClientes(Pageable pageable);
}
