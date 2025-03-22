package br.com.digio.adega.exception;

//TODO: Tratar essa exceção
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Recurso não encontrado.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
