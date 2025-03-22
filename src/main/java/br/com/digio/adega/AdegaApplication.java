package br.com.digio.adega;

import br.com.digio.adega.domain.entity.Cliente;
import br.com.digio.adega.domain.entity.Compra;
import br.com.digio.adega.domain.entity.Produto;
import br.com.digio.adega.repository.http.ClienteHttpRepository;
import br.com.digio.adega.repository.http.ProdutoHttpRepository;
import br.com.digio.adega.repository.http.dto.ClienteMockDTO;
import br.com.digio.adega.repository.http.dto.ProdutoMockDTO;
import br.com.digio.adega.service.IClienteService;
import br.com.digio.adega.service.ICompraService;
import br.com.digio.adega.service.IProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationListener;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@Slf4j
@RequiredArgsConstructor
public class AdegaApplication implements ApplicationListener<ApplicationReadyEvent> {

    private final ClienteHttpRepository clienteHttpRepository;
    private final ProdutoHttpRepository produtoHttpRepository;
    private final IProdutoService produtoService;
    private final IClienteService clienteService;
    private final ICompraService compraService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(AdegaApplication.class, args);
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Pegando produtos dos endpoints...");
        List<ProdutoMockDTO> produtoListFromEndpoint = produtoHttpRepository.getAllProdutos().getBody();
        log.info(objectMapper.writeValueAsString(produtoListFromEndpoint));

        log.info("Salvando produtos do endpoint no banco de dados...");
        var newProdutos = produtoListFromEndpoint.stream().map(produtoMockDTO -> Produto.builder()
                        .id(produtoMockDTO.codigo())
                        .tipoVinho(produtoMockDTO.tipoVinho())
                        .preco(produtoMockDTO.preco())
                        .safra(produtoMockDTO.safra().shortValue())
                        .anoCompra(produtoMockDTO.anoCompra().shortValue())
                        .build())
                .toList();
        produtoService.saveAll(newProdutos);
        log.info("Produtos salvos com sucesso!");

        log.info("Pegando clientes e compras dos endpoints...");
        List<ClienteMockDTO> clienteListFromEndpoint = clienteHttpRepository.getAllClientes().getBody();
        log.info(objectMapper.writeValueAsString(clienteListFromEndpoint));

        log.info("Salvando clientes e compras do endpoint no banco de dados...");

        clienteListFromEndpoint.forEach(clienteMockDTO -> {
            var newCliente = Cliente.builder()
                    .nome(clienteMockDTO.nome())
                    .cpf(clienteMockDTO.cpf())
                    .build();

            clienteService.save(newCliente);

            var comprasCliente = clienteMockDTO.compras().stream().map(compraDTO -> {
                        var produto = produtoService.getById(compraDTO.codigo());
                        return Compra.builder()
                                .produto(produto)
                                .quantidade(compraDTO.quantidade())
                                .valorTotal(produto.getPreco().multiply(BigDecimal.valueOf(compraDTO.quantidade())))
                                .cliente(newCliente)
                                .build();
                    }).toList();

            compraService.saveAll(comprasCliente);
        });

        log.info("Clientes e compras salvos com sucesso!");
    }
}
