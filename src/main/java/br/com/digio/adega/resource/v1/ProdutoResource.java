package br.com.digio.adega.resource.v1;

import br.com.digio.adega.resource.v1.dto.ProdutoDTO;
import br.com.digio.adega.resource.v1.mapper.ProdutoMapper;
import br.com.digio.adega.service.IProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/produtos")
@RestController
@RequiredArgsConstructor
public class ProdutoResource {

    private final IProdutoService produtoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/recomendacao/cliente/tipo")
    public ResponseEntity<ProdutoDTO> getRecomendacao(@RequestParam("cliente_id") Integer clienteId) {

        var dto = ProdutoMapper.INSTANCE.toDto(produtoService.recommendWineToClient(clienteId));
        return ResponseEntity
                .ok()
                .body(dto);
    }
}
