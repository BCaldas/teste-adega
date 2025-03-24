package br.com.digio.adega.resource.v1;

import br.com.digio.adega.resource.v1.dto.ClienteDTO;
import br.com.digio.adega.resource.v1.mapper.ClienteMapper;
import br.com.digio.adega.service.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/clientes")
@RestController
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Endpoints com recursos relacionados aos clientes")
public class ClienteResource {

    private final IClienteService clienteService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/clientes-fieis")
    @Operation(summary = "Retorna os três clientes mais fiéis", description = "Este endpoint retorna os três clientes mais fiéis baseado no valor de suas compras")
    public ResponseEntity<List<ClienteDTO>> getLoyalClientes() {

        var dto = ClienteMapper.INSTANCE.toDtoList(clienteService.getTop3Clientes());
        return ResponseEntity
                .ok()
                .body(dto);
    }
}
