package br.com.digio.adega.repository.http;

import br.com.digio.adega.repository.http.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "data-cliente", url="${data.cliente}")
public interface ClienteHttpRepository {

    @GetMapping
    ResponseEntity<List<ClienteDTO>> getAllClientes();
}
