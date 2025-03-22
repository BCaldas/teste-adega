package br.com.digio.adega.repository.http.dto;

import java.util.List;

public record ClienteMockDTO(

        String nome,
        String cpf,
        List<CompraMockDTO> compras
) {
}
