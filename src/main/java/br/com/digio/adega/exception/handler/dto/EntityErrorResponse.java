package br.com.digio.adega.exception.handler.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EntityErrorResponse extends AbstractErrorResponse {

    private List<StandardError> erros;

    public EntityErrorResponse() {

    }

    public EntityErrorResponse(String mensagem, int codigo, String status,
                               List<StandardError> erros) {
        super(mensagem, codigo, status);
        this.erros = erros;
    }
}
