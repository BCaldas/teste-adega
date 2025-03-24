package br.com.digio.adega.resource.v1;

import br.com.digio.adega.service.impl.CompraService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class ClienteResourceTest {

    public MockMvc mockMvc;

    @InjectMocks
    private CompraResource compraResource;

    @Mock
    private CompraService comprasService;

    @BeforeEach
    void init() {
        mockMvc = standaloneSetup(compraResource).build();
    }

    @Test
    @SneakyThrows
    void getListOfCompra_Ok() {
        mockMvc.perform(get("/v1/compras")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        verify(comprasService).getAllOrderedByValue();
    }

    @Test
    @SneakyThrows
    void getTopCompraByAno_Ok() {
        mockMvc.perform(get("/v1/maior-compra/2020")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(comprasService).getTopCompraByAno(Short.valueOf("2020"));
    }
}