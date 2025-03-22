package br.com.digio.adega.resource.v1.dto;

import java.math.BigDecimal;

public record CompraDTO(
        ClienteDTO cliente,
        ProdutoDTO produto,
        Integer quantidade,
        BigDecimal valorTotal
) {
}
