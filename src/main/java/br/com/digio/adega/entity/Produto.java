package br.com.digio.adega.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    private Integer codigo;

    @JsonProperty("tipo_vinho")
    private String tipoVinho;

    private BigDecimal preco;

    private Short safra;

    @JsonProperty("ano_compra")
    private Short anoCompra;
}
