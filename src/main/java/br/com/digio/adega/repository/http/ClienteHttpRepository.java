package br.com.digio.adega.repository.http;

import br.com.digio.adega.entity.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "data-cliente", url="${data.cliente}")
public interface ClienteHttpRepository {

    @GetMapping
    ResponseEntity<Cliente> getAllClientes();
}
