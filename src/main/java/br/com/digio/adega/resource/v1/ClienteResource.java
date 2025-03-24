package br.com.digio.adega.resource.v1;

import br.com.digio.adega.resource.v1.dto.ClienteDTO;
import br.com.digio.adega.resource.v1.mapper.ClienteMapper;
import br.com.digio.adega.service.IClienteService;
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
public class ClienteResource {

    private final IClienteService clienteService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<ClienteDTO>> getLoyalClientes() {

        var dto = ClienteMapper.INSTANCE.toDtoList(clienteService.getTop3Clientes());
        return ResponseEntity
                .ok()
                .body(dto);
    }
}
