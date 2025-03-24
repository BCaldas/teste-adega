package br.com.digio.adega.service;

import br.com.digio.adega.domain.entity.Produto;
import br.com.digio.adega.exception.ResourceNotFoundException;
import br.com.digio.adega.repository.ProdutoRepository;
import br.com.digio.adega.service.impl.CompraService;
import br.com.digio.adega.service.impl.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CompraService compraService;

    @Spy
    private ObjectMapper mapper;

    @Test
    void saveListOfProdutos_Ok() {
        var produtosMock = Arrays.asList(new Produto(null, "Tinto", BigDecimal.valueOf(100.00), Short.valueOf("1800"), Short.valueOf("2024")),
                new Produto(null, "Branco", BigDecimal.valueOf(200.00), Short.valueOf("1900"), Short.valueOf("2025")));

        var produtosReturn = Arrays.asList(new Produto(1, "Tinto", BigDecimal.valueOf(100.00), Short.valueOf("1800"), Short.valueOf("2024")),
                new Produto(2, "Branco", BigDecimal.valueOf(200.00), Short.valueOf("1900"), Short.valueOf("2025")));

        when(produtoRepository.saveAll(produtosMock)).thenReturn(produtosReturn);
        var savedProdutos = service.saveAll(produtosMock);

        assertEquals(produtosReturn, savedProdutos);
    }

    @Test
    void getById_Ok() {
        var produtoMock = new Produto(1, "Tinto", BigDecimal.valueOf(100.00), Short.valueOf("1800"), Short.valueOf("2024"));

        when(produtoRepository.findById(any())).thenReturn(Optional.of(produtoMock));

        var produtoReturn = service.getById(1);

        assertEquals(produtoMock, produtoReturn);
    }

    @Test
    void getById_NotFound() {
        when(produtoRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(1));
    }

    @Test
    void recommendWineToClient_Ok() {
        var mockPurchasedWines = List.of("Tinto");
        var mockRandomizedWine = List.of(new Produto(1, "Tinto", BigDecimal.valueOf(100.00), Short.valueOf("1800"), Short.valueOf("2024")));

        when(compraService.getRandomMostPurchasedWineTypeByClient(any(), any())).thenReturn(mockPurchasedWines);
        when(produtoRepository.findRandomWineByType(any(), any())).thenReturn(mockRandomizedWine);

        var expected = new Produto(1, "Tinto", BigDecimal.valueOf(100.00), Short.valueOf("1800"), Short.valueOf("2024"));
        var returnedWine = service.recommendWineToClient(1);

        assertEquals(expected, returnedWine);
    }

    @Test
    void recommendWineToClient_ClientWithoutPurchase() {
        var mockPurchasedWines = new ArrayList<String>();

        when(compraService.getRandomMostPurchasedWineTypeByClient(any(), any())).thenReturn(mockPurchasedWines);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.recommendWineToClient(1));
        var expectedMessage = "Não foi possível encontrar uma recomendação. Cliente não possui compras";

        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    void recommendWineToClient_WineTypeNotFound() {
        var mockPurchasedWines = List.of("Tinto");

        when(compraService.getRandomMostPurchasedWineTypeByClient(any(), any())).thenReturn(mockPurchasedWines);
        when(produtoRepository.findRandomWineByType(any(), any())).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.recommendWineToClient(1));
        var expectedMessage = "Nenhum vinho encontrado para o tipo ";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}