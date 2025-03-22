package br.com.digio.adega.domain.dto;

import java.math.BigDecimal;

public record CompraDTO(
        ClienteDTO cliente,
        ProdutoDTO produto,
        Integer quantidade,
        BigDecimal valorTotal


) {
}
