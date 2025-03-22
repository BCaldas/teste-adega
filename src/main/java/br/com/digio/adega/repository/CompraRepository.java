package br.com.digio.adega.repository;

import br.com.digio.adega.domain.dto.CompraDTO;
import br.com.digio.adega.domain.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    @Query("""
        SELECT new br.com.digio.adega.domain.dto.CompraDTO(
            new br.com.digio.adega.domain.dto.ClienteDTO(c.cliente.nome, c.cliente.cpf),
            new br.com.digio.adega.domain.dto.ProdutoDTO(c.produto.tipoVinho, c.produto.preco, c.produto.safra, c.produto.anoCompra),
            c.quantidade,
            c.valorTotal           
        )
        FROM Compra c
        ORDER BY c.valorTotal ASC
    """)
    List<CompraDTO> findAllOrderByValorTotalAsc();
}
