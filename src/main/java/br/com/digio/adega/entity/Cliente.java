package br.com.digio.adega.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Cliente {

    private String nome;

    private String cpf;

    private List<CompraCliente> compras;
}
