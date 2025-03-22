package br.com.digio.adega.resource.v1.impl;

import br.com.digio.adega.domain.dto.CompraDTO;
import br.com.digio.adega.resource.v1.ICompraResource;
import br.com.digio.adega.service.ICompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class CompraResource implements ICompraResource {

    private final ICompraService compraService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/compras")
    public ResponseEntity<List<CompraDTO>> compras() {
        return ResponseEntity
                .ok()
                .body(compraService.getAllOrderedByValue());
    }
}
