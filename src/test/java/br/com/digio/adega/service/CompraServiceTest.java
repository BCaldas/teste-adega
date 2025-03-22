package br.com.digio.adega.service;

import br.com.digio.adega.domain.entity.Cliente;
import br.com.digio.adega.domain.entity.Compra;
import br.com.digio.adega.domain.entity.Produto;
import br.com.digio.adega.exception.ResourceNotFoundException;
import br.com.digio.adega.repository.CompraRepository;
import br.com.digio.adega.service.impl.CompraService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompraServiceTest {

    @InjectMocks
    private CompraService service;

    @Mock
    private CompraRepository compraRepository;

    @Spy
    private ObjectMapper mapper;

    private static final String RETURN_COMPRA_LIST = "[{\"cliente\":{\"nome\":\"Hadassa Daniela Sales\",\"cpf\":\"1051252612\"},\"produto\":{\"tipo_vinho\":\"Branco\",\"preco\":106.50,\"safra\":2018,\"ano_compra\":2019},\"quantidade\":2,\"valor_total\":213.00},{\"cliente\":{\"nome\":\"Fabiana Melissa Nunes\",\"cpf\":\"824643755772\"},\"produto\":{\"tipo_vinho\":\"Rosé\",\"preco\":120.99,\"safra\":2018,\"ano_compra\":2019},\"quantidade\":2,\"valor_total\":241.98},{\"cliente\":{\"nome\":\"Andreia Emanuelly da Mata\",\"cpf\":\"27737287426\"},\"produto\":{\"tipo_vinho\":\"Rosé\",\"preco\":121.75,\"safra\":2019,\"ano_compra\":2020},\"quantidade\":2,\"valor_total\":243.50},{\"cliente\":{\"nome\":\"Geraldo Pedro Julio Nascimento\",\"cpf\":\"05870189179\"},\"produto\":{\"tipo_vinho\":\"Chardonnay\",\"preco\":130.75,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":2,\"valor_total\":261.50},{\"cliente\":{\"nome\":\"Teresinha Daniela Galvão\",\"cpf\":\"04372012950\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":134.25,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":2,\"valor_total\":268.50},{\"cliente\":{\"nome\":\"Kaique Danilo Alves\",\"cpf\":\"20634031392\"},\"produto\":{\"tipo_vinho\":\"Rosé\",\"preco\":120.99,\"safra\":2018,\"ano_compra\":2019},\"quantidade\":3,\"valor_total\":362.97},{\"cliente\":{\"nome\":\"Pietra Antônia Brenda Silva\",\"cpf\":\"74302668512\"},\"produto\":{\"tipo_vinho\":\"Rosé\",\"preco\":121.75,\"safra\":2019,\"ano_compra\":2020},\"quantidade\":3,\"valor_total\":365.25},{\"cliente\":{\"nome\":\"Andreia Emanuelly da Mata\",\"cpf\":\"27737287426\"},\"produto\":{\"tipo_vinho\":\"Branco\",\"preco\":125.25,\"safra\":2017,\"ano_compra\":2018},\"quantidade\":3,\"valor_total\":375.75},{\"cliente\":{\"nome\":\"Hadassa Daniela Sales\",\"cpf\":\"1051252612\"},\"produto\":{\"tipo_vinho\":\"Branco\",\"preco\":125.25,\"safra\":2017,\"ano_compra\":2018},\"quantidade\":3,\"valor_total\":375.75},{\"cliente\":{\"nome\":\"Geraldo Pedro Julio Nascimento\",\"cpf\":\"05870189179\"},\"produto\":{\"tipo_vinho\":\"Chardonnay\",\"preco\":128.99,\"safra\":2021,\"ano_compra\":2022},\"quantidade\":3,\"valor_total\":386.97},{\"cliente\":{\"nome\":\"Teresinha Daniela Galvão\",\"cpf\":\"04372012950\"},\"produto\":{\"tipo_vinho\":\"Chardonnay\",\"preco\":130.75,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":3,\"valor_total\":392.25},{\"cliente\":{\"nome\":\"Teresinha Daniela Galvão\",\"cpf\":\"04372012950\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":134.25,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":3,\"valor_total\":402.75},{\"cliente\":{\"nome\":\"Hadassa Daniela Sales\",\"cpf\":\"1051252612\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":135.50,\"safra\":2019,\"ano_compra\":2020},\"quantidade\":3,\"valor_total\":406.50},{\"cliente\":{\"nome\":\"Catarina Sebastiana Analu Almeida\",\"cpf\":\"88901767767\"},\"produto\":{\"tipo_vinho\":\"Branco\",\"preco\":126.50,\"safra\":2018,\"ano_compra\":2019},\"quantidade\":4,\"valor_total\":506.00},{\"cliente\":{\"nome\":\"Ian Joaquim Giovanni Santos\",\"cpf\":\"96718391344\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":134.25,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":4,\"valor_total\":537.00},{\"cliente\":{\"nome\":\"Natália Sandra da Cruz\",\"cpf\":\"03763001590\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":134.25,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":4,\"valor_total\":537.00},{\"cliente\":{\"nome\":\"Andreia Emanuelly da Mata\",\"cpf\":\"27737287426\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":134.25,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":4,\"valor_total\":537.00},{\"cliente\":{\"nome\":\"Gabriel Rafael Dias\",\"cpf\":\"85067950013\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":135.50,\"safra\":2019,\"ano_compra\":2020},\"quantidade\":4,\"valor_total\":542.00},{\"cliente\":{\"nome\":\"Andreia Emanuelly da Mata\",\"cpf\":\"27737287426\"},\"produto\":{\"tipo_vinho\":\"Rosé\",\"preco\":121.75,\"safra\":2019,\"ano_compra\":2020},\"quantidade\":5,\"valor_total\":608.75},{\"cliente\":{\"nome\":\"Geraldo Pedro Julio Nascimento\",\"cpf\":\"05870189179\"},\"produto\":{\"tipo_vinho\":\"Branco\",\"preco\":126.50,\"safra\":2018,\"ano_compra\":2019},\"quantidade\":5,\"valor_total\":632.50},{\"cliente\":{\"nome\":\"Andreia Emanuelly da Mata\",\"cpf\":\"27737287426\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":134.25,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":5,\"valor_total\":671.25},{\"cliente\":{\"nome\":\"Isis Isis Ramos\",\"cpf\":\"29457224965\"},\"produto\":{\"tipo_vinho\":\"Rosé\",\"preco\":120.99,\"safra\":2018,\"ano_compra\":2019},\"quantidade\":6,\"valor_total\":725.94},{\"cliente\":{\"nome\":\"Gabriel Rafael Dias\",\"cpf\":\"85067950013\"},\"produto\":{\"tipo_vinho\":\"Branco\",\"preco\":125.25,\"safra\":2017,\"ano_compra\":2018},\"quantidade\":6,\"valor_total\":751.50},{\"cliente\":{\"nome\":\"Geraldo Pedro Julio Nascimento\",\"cpf\":\"05870189179\"},\"produto\":{\"tipo_vinho\":\"Chardonnay\",\"preco\":188.99,\"safra\":2021,\"ano_compra\":2022},\"quantidade\":4,\"valor_total\":755.96},{\"cliente\":{\"nome\":\"Maitê Kamilly Corte Real\",\"cpf\":\"022484638124\"},\"produto\":{\"tipo_vinho\":\"Chardonnay\",\"preco\":188.99,\"safra\":2021,\"ano_compra\":2022},\"quantidade\":4,\"valor_total\":755.96},{\"cliente\":{\"nome\":\"Catarina Sebastiana Analu Almeida\",\"cpf\":\"88901767767\"},\"produto\":{\"tipo_vinho\":\"Tinto\",\"preco\":127.50,\"safra\":2016,\"ano_compra\":2017},\"quantidade\":6,\"valor_total\":765.00},{\"cliente\":{\"nome\":\"Andreia Emanuelly da Mata\",\"cpf\":\"27737287426\"},\"produto\":{\"tipo_vinho\":\"Chardonnay\",\"preco\":128.99,\"safra\":2021,\"ano_compra\":2022},\"quantidade\":6,\"valor_total\":773.94},{\"cliente\":{\"nome\":\"Maitê Kamilly Corte Real\",\"cpf\":\"022484638124\"},\"produto\":{\"tipo_vinho\":\"Espumante\",\"preco\":135.50,\"safra\":2019,\"ano_compra\":2020},\"quantidade\":6,\"valor_total\":813.00},{\"cliente\":{\"nome\":\"Isis Isis Ramos\",\"cpf\":\"29457224965\"},\"produto\":{\"tipo_vinho\":\"Tinto\",\"preco\":229.99,\"safra\":2017,\"ano_compra\":2018},\"quantidade\":4,\"valor_total\":919.96},{\"cliente\":{\"nome\":\"Ian Joaquim Giovanni Santos\",\"cpf\":\"96718391344\"},\"produto\":{\"tipo_vinho\":\"Chardonnay\",\"preco\":188.99,\"safra\":2021,\"ano_compra\":2022},\"quantidade\":6,\"valor_total\":1133.94},{\"cliente\":{\"nome\":\"Fabiana Melissa Nunes\",\"cpf\":\"824643755772\"},\"produto\":{\"tipo_vinho\":\"Chardonnay\",\"preco\":130.75,\"safra\":2020,\"ano_compra\":2021},\"quantidade\":10,\"valor_total\":1307.50},{\"cliente\":{\"nome\":\"Geraldo Pedro Julio Nascimento\",\"cpf\":\"05870189179\"},\"produto\":{\"tipo_vinho\":\"Tinto\",\"preco\":229.99,\"safra\":2017,\"ano_compra\":2018},\"quantidade\":6,\"valor_total\":1379.94},{\"cliente\":{\"nome\":\"Ian Joaquim Giovanni Santos\",\"cpf\":\"96718391344\"},\"produto\":{\"tipo_vinho\":\"Branco\",\"preco\":125.25,\"safra\":2017,\"ano_compra\":2018},\"quantidade\":13,\"valor_total\":1628.25},{\"cliente\":{\"nome\":\"Vitória Alícia Mendes\",\"cpf\":\"20623850567\"},\"produto\":{\"tipo_vinho\":\"Tinto\",\"preco\":229.99,\"safra\":2017,\"ano_compra\":2018},\"quantidade\":8,\"valor_total\":1839.92},{\"cliente\":{\"nome\":\"Ian Joaquim Giovanni Santos\",\"cpf\":\"96718391344\"},\"produto\":{\"tipo_vinho\":\"Branco\",\"preco\":126.50,\"safra\":2018,\"ano_compra\":2019},\"quantidade\":15,\"valor_total\":1897.50},{\"cliente\":{\"nome\":\"Natália Sandra da Cruz\",\"cpf\":\"03763001590\"},\"produto\":{\"tipo_vinho\":\"Tinto\",\"preco\":327.50,\"safra\":2016,\"ano_compra\":2017},\"quantidade\":6,\"valor_total\":1965.00},{\"cliente\":{\"nome\":\"Ian Joaquim Giovanni Santos\",\"cpf\":\"96718391344\"},\"produto\":{\"tipo_vinho\":\"Rosé\",\"preco\":121.75,\"safra\":2019,\"ano_compra\":2020},\"quantidade\":20,\"valor_total\":2435.00}]";
    private static final String RETURN_TOP_COMPRA_ANO = "{\"cliente\":{\"nome\":\"Vitória Alícia Mendes\",\"cpf\":\"20623850567\"},\"produto\":{\"tipo_vinho\":\"Tinto\",\"preco\":229.99,\"safra\":2017,\"ano_compra\":2018},\"quantidade\":8,\"valor_total\":1839.92}";

    @Test
    void saveListOfCompras_Ok() {
        var comprasMock = Arrays.asList(new Compra(null, Cliente.builder().id(1).build(), Produto.builder().id(1).build(), 1, BigDecimal.valueOf(10)),
                new Compra(null, Cliente.builder().id(2).build(), Produto.builder().id(2).build(), 2, BigDecimal.valueOf(20)));

        var comprasReturn = Arrays.asList(new Compra(1, Cliente.builder().id(1).build(), Produto.builder().id(1).build(), 1, BigDecimal.valueOf(10)),
                new Compra(2, Cliente.builder().id(2).build(), Produto.builder().id(2).build(), 2, BigDecimal.valueOf(20)));

        when(compraRepository.saveAll(comprasMock)).thenReturn(comprasReturn);
        var comprasSalvas = service.saveAll(comprasMock);

        assertEquals(comprasReturn, comprasSalvas);
    }

    @Test
    void getListOfCompra_Ok() throws JsonProcessingException {

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        var comprasList = Arrays.asList(mapper.readValue(RETURN_COMPRA_LIST, Compra[].class));

        when(compraRepository.findAllByOrderByValorTotalAsc()).thenReturn(comprasList);

        var comprasReturn = service.getAllOrderedByValue();

        assertEquals(comprasList, comprasReturn);

    }

    @Test
    void getTopCompraByAno_Ok() throws JsonProcessingException {

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        var compra = mapper.readValue(RETURN_TOP_COMPRA_ANO, Compra.class);

        when(compraRepository.findFirstByProdutoAnoCompraOrderByValorTotalDesc(any())).thenReturn(Optional.of(compra));

        var compraReturn = service.getTopCompraByAno(Short.valueOf("2020"));

        assertEquals(compra, compraReturn);

    }

    @Test
    void getTopCompraByAno_NotFound() {

        when(compraRepository.findFirstByProdutoAnoCompraOrderByValorTotalDesc(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getTopCompraByAno(Short.valueOf("2020")));
    }
}