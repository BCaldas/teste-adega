package br.com.digio.adega;

import br.com.digio.adega.entity.Cliente;
import br.com.digio.adega.entity.Compra;
import br.com.digio.adega.entity.Produto;
import br.com.digio.adega.repository.http.ClienteHttpRepository;
import br.com.digio.adega.repository.http.ProdutoHttpRepository;
import br.com.digio.adega.repository.http.dto.ClienteDTO;
import br.com.digio.adega.repository.http.dto.ProdutoDTO;
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
        List<ProdutoDTO> produtoListFromEndpoint = produtoHttpRepository.getAllProdutos().getBody();
        log.info(objectMapper.writeValueAsString(produtoListFromEndpoint));

        log.info("Salvando produtos do endpoint no banco de dados...");
        var newProdutos = produtoListFromEndpoint.stream().map(produtoDTO -> Produto.builder()
                        .id(produtoDTO.codigo())
                        .tipoVinho(produtoDTO.tipoVinho())
                        .preco(produtoDTO.preco())
                        .safra(produtoDTO.safra().shortValue())
                        .anoCompra(produtoDTO.anoCompra().shortValue())
                        .build())
                .toList();
        produtoService.saveAll(newProdutos);
        log.info("Produtos salvos com sucesso!");

        log.info("Pegando clientes e compras dos endpoints...");
        List<ClienteDTO> clienteListFromEndpoint = clienteHttpRepository.getAllClientes().getBody();
        log.info(objectMapper.writeValueAsString(clienteListFromEndpoint));

        log.info("Salvando clientes e compras do endpoint no banco de dados...");

        clienteListFromEndpoint.forEach(clienteDTO -> {
            var newCliente = Cliente.builder()
                    .nome(clienteDTO.nome())
                    .cpf(clienteDTO.cpf())
                    .build();

            clienteService.save(newCliente);

            var comprasCliente = clienteDTO.compras().stream().map(compraDTO -> {
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
