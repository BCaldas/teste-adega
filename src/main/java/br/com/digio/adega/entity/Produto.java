package br.com.digio.adega.entity;

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
    private Integer id;

    private String tipoVinho;

    private BigDecimal preco;

    private Short safra;

    private Short anoCompra;
}
