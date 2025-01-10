package br.com.etldomaindata.transformation.catalog;

import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.catalog.TransformModelToResponseCatalog;
import br.com.etldomaindata.transformation.mapper.response.AnotherTagResponseMapper;
import br.com.etldomaindata.transformation.mapper.response.CGFResponseMapper;
import br.com.etldomaindata.transformation.mapper.response.DefaultResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class TransformModelToResponseCatalogTest {

    private TransformModelToResponseCatalog transformer;

    @BeforeEach
    void setUp() {
        transformer = new TransformModelToResponseCatalog();
    }

    @Test
    void testConvert_CGFTag() {
        // Mockando o método estático do CGFResponseMapper
        try (MockedStatic<CGFResponseMapper> mockedMapper = mockStatic(CGFResponseMapper.class)) {
            // Dados de entrada
            DataModel input = new DataModel();
            input.setId("123");
            input.setStatus("Active");

            // Configurando o mock para retornar um ResponseDTO esperado
            ResponseDTO expectedResponse = new ResponseDTO();
            expectedResponse.setId("123");
            expectedResponse.setStatus("Active");

            mockedMapper.when(() -> CGFResponseMapper.map(input)).thenReturn(expectedResponse);

            // Chamada do método a ser testado
            ResponseDTO result = transformer.convert(input, TagEnum.CGF);

            // Verificações
            assertEquals(expectedResponse, result);
            mockedMapper.verify(() -> CGFResponseMapper.map(input), times(1));
        }
    }

    @Test
    void testConvert_AnotherTag() {
        // Mockando o método estático do AnotherTagResponseMapper
        try (MockedStatic<AnotherTagResponseMapper> mockedMapper = mockStatic(AnotherTagResponseMapper.class)) {
            // Dados de entrada
            DataModel input = new DataModel();
            input.setId("456");
            input.setStatus("Inactive");

            // Configurando o mock para retornar um ResponseDTO esperado
            ResponseDTO expectedResponse = new ResponseDTO();
            expectedResponse.setId("456");
            expectedResponse.setStatus("Status personalizado para ANOTHER_TAG");

            mockedMapper.when(() -> AnotherTagResponseMapper.map(input)).thenReturn(expectedResponse);

            // Chamada do método a ser testado
            ResponseDTO result = transformer.convert(input, TagEnum.ANOTHER_TAG);

            // Verificações
            assertEquals(expectedResponse, result);
            mockedMapper.verify(() -> AnotherTagResponseMapper.map(input), times(1));
        }
    }

    @Test
    void testConvert_DefaultTag() {
        // Mockando o método estático do DefaultResponseMapper
        try (MockedStatic<DefaultResponseMapper> mockedMapper = mockStatic(DefaultResponseMapper.class)) {
            // Dados de entrada
            DataModel input = new DataModel();
            input.setId("789");
            input.setStatus("Pending");

            // Configurando o mock para retornar um ResponseDTO esperado
            ResponseDTO expectedResponse = new ResponseDTO();
            expectedResponse.setId("789");
            expectedResponse.setStatus("Status padrão");

            mockedMapper.when(() -> DefaultResponseMapper.map(input)).thenReturn(expectedResponse);

            // Chamada do método a ser testado
            ResponseDTO result = transformer.convert(input, TagEnum.OTHER);

            // Verificações
            assertEquals(expectedResponse, result);
            mockedMapper.verify(() -> DefaultResponseMapper.map(input), times(1));
        }
    }
}
