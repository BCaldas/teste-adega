package br.com.digio.adega.domain.dto;

import java.math.BigDecimal;

public record ProdutoDTO(
        String tipoVinho,
        BigDecimal preco,
        Short safra,
        Short anoCompra
) {
}
