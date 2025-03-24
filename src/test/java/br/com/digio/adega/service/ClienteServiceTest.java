package br.com.digio.adega.service;

import br.com.digio.adega.domain.entity.Cliente;
import br.com.digio.adega.repository.ClienteRepository;
import br.com.digio.adega.service.impl.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService service;

    @Mock
    private ClienteRepository clienteRepository;

    @Spy
    private ObjectMapper mapper;

    private static final String RETURN_LOYAL_CLIENTES = "[{\"nome\":\"Ian Joaquim Giovanni Santos\",\"cpf\":\"96718391344\"},{\"nome\":\"Geraldo Pedro Julio Nascimento\",\"cpf\":\"05870189179\"},{\"nome\":\"Andreia Emanuelly da Mata\",\"cpf\":\"27737287426\"}]";

    @Test
    void saveCliente_Ok() {
        var clienteMock = Cliente.builder()
                .cpf("11111111111")
                .nome("Fulano")
                .build();

        var clienteReturn = Cliente.builder()
                .id(1)
                .cpf("11111111111")
                .nome("Fulano")
                .build();

        when(clienteRepository.save(clienteMock)).thenReturn(clienteReturn);
        var clienteSalvo = service.save(clienteMock);

        assertEquals(clienteReturn, clienteSalvo);
    }

    @Test
    void getLoyalClientes_Ok() throws JsonProcessingException {

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        var clienteList = Arrays.asList(mapper.readValue(RETURN_LOYAL_CLIENTES, Cliente[].class));

        when(clienteRepository.findTopLoyalClientes(any())).thenReturn(clienteList);

        var comprasReturn = service.getTop3Clientes();

        assertEquals(clienteList, comprasReturn);

    }
}