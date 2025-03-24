package br.com.digio.adega.repository.model;

public record PurchasedWine(
        String tipoVinho,
        Long totalComprado
) {
}
