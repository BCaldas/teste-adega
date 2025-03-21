package br.com.digio.adega.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompraCliente {

    private Integer codigo;

    private Integer quantidade;
}
