package br.com.digio.adega.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Produto {

    private Integer codigo;

    @JsonProperty("tipo_vinho")
    private String tipoVinho;

    private BigDecimal preco;

    private Short safra;

    @JsonProperty("ano_compra")
    private Short anoCompra;
}
