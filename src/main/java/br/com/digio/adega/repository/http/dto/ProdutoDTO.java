package br.com.digio.adega.repository.http.dto;

import java.math.BigDecimal;

public record ProdutoDTO (

        Integer codigo,
        String tipoVinho,
        BigDecimal preco,
        Integer safra,
        Integer anoCompra
) {
}
