package br.com.digio.adega.repository.http;

import br.com.digio.adega.entity.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "data-produto", url="${data.produto}")
public interface ProdutoHttpRepository {

    @GetMapping
    ResponseEntity<Produto> getAllProdutos();
}
