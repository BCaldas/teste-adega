package br.com.digio.adega.resource.v1;

import br.com.digio.adega.resource.v1.dto.CompraDTO;
import br.com.digio.adega.resource.v1.mapper.CompraMapper;
import br.com.digio.adega.service.ICompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/compras")
@RestController
@RequiredArgsConstructor
@Tag(name = "Compras", description = "Endpoints com recursos relacionados as Compras dos clientes")
public class CompraResource {

    private final ICompraService compraService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    @Operation(summary = "Lista de Compras", description = "Retorna a lista com todas as compras ordenadas por valor em ordem crescente")
    public ResponseEntity<List<CompraDTO>> getAllCompras() {

        var dto = CompraMapper.INSTANCE.toDtoList(compraService.getAllOrderedByValue());
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/maior-compra/{ano}")
    @Operation(summary = "Maior compra do ano", description = "Retorna os dados da maior compra feita pelo ano")
    public ResponseEntity<CompraDTO> getBiggerCompraByYear(@PathVariable Short ano) {

        var dto = CompraMapper.INSTANCE.toDto(compraService.getTopCompraByAno(ano));
        return ResponseEntity
                .ok()
                .body(dto);
    }
}
