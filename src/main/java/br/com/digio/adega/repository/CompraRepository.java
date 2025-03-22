package br.com.digio.adega.repository;

import br.com.digio.adega.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
}
