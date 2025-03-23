package br.com.digio.adega.resource.v1;

import br.com.digio.adega.resource.v1.dto.CompraDTO;
import br.com.digio.adega.resource.v1.mapper.CompraMapper;
import br.com.digio.adega.service.ICompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class CompraResource {

    private final ICompraService compraService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/compras")
    public ResponseEntity<List<CompraDTO>> getAllCompras() {

        var dto = CompraMapper.INSTANCE.toDtoList(compraService.getAllOrderedByValue());
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<CompraDTO> getBiggerCompraByYear(@PathVariable Short ano) {

        var dto = CompraMapper.INSTANCE.toDto(compraService.getTopCompraByAno(ano));
        return ResponseEntity
                .ok()
                .body(dto);
    }
}
