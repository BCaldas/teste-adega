package br.com.digio.adega.exception.handler;

import br.com.digio.adega.exception.ResourceNotFoundException;
import br.com.digio.adega.exception.handler.dto.EntityErrorResponse;
import br.com.digio.adega.exception.handler.dto.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> handleRecursoNaoEncontradoException(ResourceNotFoundException exception) {
        List<StandardError> errors = new ArrayList<>();
        errors.add(new StandardError(exception.getLocalizedMessage()));
        EntityErrorResponse response = new EntityErrorResponse(
                "Erro na requisição", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), errors);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
