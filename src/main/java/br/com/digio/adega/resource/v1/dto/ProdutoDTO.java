package br.com.digio.adega.resource.v1.dto;

import java.math.BigDecimal;

public record ProdutoDTO(
        String tipoVinho,
        BigDecimal preco,
        Short safra,
        Short anoCompra
) {
}
