package br.com.digio.adega.resource.v1;

import br.com.digio.adega.service.impl.ProdutoService;
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
class ProdutoResourceTest {

    public MockMvc mockMvc;

    @InjectMocks
    private ProdutoResource produtoResource;

    @Mock
    private ProdutoService produtoService;

    @BeforeEach
    void init() {
        mockMvc = standaloneSetup(produtoResource).build();
    }

    @Test
    @SneakyThrows
    void getRecommendation_Ok() {
        mockMvc.perform(get("/v1/produtos/recomendacao/cliente/tipo?cliente_id=1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        verify(produtoService).recommendWineToClient(1);
    }
}