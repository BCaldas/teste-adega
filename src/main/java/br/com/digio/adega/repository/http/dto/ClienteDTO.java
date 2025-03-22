package br.com.digio.adega.repository.http.dto;

import java.util.List;

public record ClienteDTO(

        String nome,
        String cpf,
        List<CompraDTO> compras
) {
}
