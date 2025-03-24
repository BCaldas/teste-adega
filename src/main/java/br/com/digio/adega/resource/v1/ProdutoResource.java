package br.com.digio.adega.resource.v1;

import br.com.digio.adega.resource.v1.dto.ProdutoDTO;
import br.com.digio.adega.resource.v1.mapper.ProdutoMapper;
import br.com.digio.adega.service.IProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/produtos")
@RestController
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "Endpoints com recursos relacionados aos Produtos")
public class ProdutoResource {

    private final IProdutoService produtoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/recomendacao/cliente/tipo")
    @Operation(summary = "Recomendação de Compra", description = "Retorna uma recomendação de compra para o cliente baseada nos tipos de vinhos mais comprados pelo mesmo.")
    public ResponseEntity<ProdutoDTO> getRecommendation(@RequestParam("cliente_id") Integer clienteId) {

        var dto = ProdutoMapper.INSTANCE.toDto(produtoService.recommendWineToClient(clienteId));
        return ResponseEntity
                .ok()
                .body(dto);
    }
}
