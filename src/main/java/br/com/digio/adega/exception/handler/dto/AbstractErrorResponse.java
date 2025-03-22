package br.com.digio.adega.exception.handler.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public abstract class AbstractErrorResponse {

	private String mensagem;
	private int codigo;
	private String status;
	private LocalDateTime data;

	public AbstractErrorResponse() {
		this.data = LocalDateTime.now();
	}

	public AbstractErrorResponse(String mensagem, int codigo, String status) {
		this.mensagem = mensagem;
		this.codigo = codigo;
		this.status = status;
		this.data = LocalDateTime.now();
	}
}
