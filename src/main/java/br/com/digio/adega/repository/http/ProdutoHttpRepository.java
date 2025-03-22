package br.com.digio.adega.repository.http;

import br.com.digio.adega.repository.http.dto.ProdutoMockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "data-produto", url="${data.produto}")
public interface ProdutoHttpRepository {

    @GetMapping
    ResponseEntity<List<ProdutoMockDTO>> getAllProdutos();
}
