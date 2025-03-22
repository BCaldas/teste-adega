package br.com.digio.adega.repository;

import br.com.digio.adega.domain.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
    List<Compra> findAllByOrderByValorTotalAsc();
}
