package br.com.digio.adega.exception.handler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardError {

    private String erro;
    private String reason;

    public StandardError() {
    }

    public StandardError(String erro) {
        this.erro = erro;
    }

    public StandardError(String erro, String reason) {
        this.erro = erro;
        this.reason = reason;
    }

}
