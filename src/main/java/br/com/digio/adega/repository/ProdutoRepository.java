package br.com.digio.adega.repository;

import br.com.digio.adega.domain.entity.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT p FROM Produto p WHERE p.tipoVinho = :tipoVinho ORDER BY FUNCTION('RAND')")
    List<Produto> findRandomWineByType(@Param("tipoVinho") String tipoVinho, Pageable pageable);
}
