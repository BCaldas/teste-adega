package br.com.digio.adega.resource.v1.impl;

import br.com.digio.adega.resource.v1.ICompraResource;
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
public class CompraResource implements ICompraResource {

    private final ICompraService compraService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/compras")
    public ResponseEntity<List<CompraDTO>> compras() {

        var dto = CompraMapper.INSTANCE.toDtoList(compraService.getAllOrderedByValue());
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<CompraDTO> compras(@PathVariable Short ano) {

        var dto = CompraMapper.INSTANCE.toDto(compraService.getTopCompraByAno(ano));
        return ResponseEntity
                .ok()
                .body(dto);
    }
}
