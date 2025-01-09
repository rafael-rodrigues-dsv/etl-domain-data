package br.com.etldomaindata.transformation.converter;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConverterCatalogTest {

    private ConverterCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new ConverterCatalog();  // Cria uma nova instância do catálogo antes de cada teste
    }

    @Test
    void testRegisterConverter() {
        // Cria um conversor fictício com dois parâmetros
        Converter<RequestDTO, DataModel> converter = (input, tagEnum) -> new DataModel();  // Conversor simples para fins de teste

        // Registra o conversor
        catalog.registerConverter(RequestDTO.class, DataModel.class, converter);

        // Recupera o conversor registrado
        Converter<RequestDTO, DataModel> retrievedConverter = catalog.getConverter(RequestDTO.class, DataModel.class);

        // Verifica se o conversor foi registrado corretamente
        assertNotNull(retrievedConverter);
        assertEquals(converter, retrievedConverter);
    }

    @Test
    void testGetConverter_ReturnsNull_WhenNotRegistered() {
        // Tenta recuperar um conversor que não foi registrado
        Converter<RequestDTO, ResponseDTO> converter = catalog.getConverter(RequestDTO.class, ResponseDTO.class);

        // Verifica se retorna null
        assertNull(converter);
    }

    @Test
    void testGenerateKey() {
        // Verifica se a chave gerada para o par de classes é correta
        String expectedKey = RequestDTO.class.getName() + "->" + DataModel.class.getName();
        String actualKey = catalog.generateKey(RequestDTO.class, DataModel.class);

        assertEquals(expectedKey, actualKey);
    }

    @Test
    void testRegisterMultipleConverters() {
        // Cria conversores fictícios com dois parâmetros
        Converter<RequestDTO, DataModel> converter1 = (input, tagEnum) -> new DataModel();
        Converter<DataModel, ResponseDTO> converter2 = (input, tagEnum) -> new ResponseDTO();

        // Registra os conversores
        catalog.registerConverter(RequestDTO.class, DataModel.class, converter1);
        catalog.registerConverter(DataModel.class, ResponseDTO.class, converter2);

        // Verifica se ambos os conversores foram registrados corretamente
        assertNotNull(catalog.getConverter(RequestDTO.class, DataModel.class));
        assertNotNull(catalog.getConverter(DataModel.class, ResponseDTO.class));
    }

    @Test
    void testRegisterConverter_OverwritesExistingConverter() {
        // Cria e registra o primeiro conversor com dois parâmetros
        Converter<RequestDTO, DataModel> converter1 = (input, tagEnum) -> new DataModel();
        catalog.registerConverter(RequestDTO.class, DataModel.class, converter1);

        // Cria e registra um novo conversor, que deve substituir o anterior
        Converter<RequestDTO, DataModel> converter2 = (input, tagEnum) -> new DataModel();
        catalog.registerConverter(RequestDTO.class, DataModel.class, converter2);

        // Verifica se o novo conversor foi registrado corretamente
        assertEquals(converter2, catalog.getConverter(RequestDTO.class, DataModel.class));
    }
}
