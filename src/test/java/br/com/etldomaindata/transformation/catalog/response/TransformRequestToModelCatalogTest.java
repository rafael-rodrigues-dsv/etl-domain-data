package br.com.etldomaindata.transformation.catalog.response;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.catalog.request.TransformRequestToModelCatalog;
import br.com.etldomaindata.transformation.mapper.request.AnotherTagRequestMapper;
import br.com.etldomaindata.transformation.mapper.request.CGFRequestMapper;
import br.com.etldomaindata.transformation.mapper.request.DefaultRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransformRequestToModelCatalogTest {

    private TransformRequestToModelCatalog transformer;

    @BeforeEach
    void setUp() {
        transformer = new TransformRequestToModelCatalog();
    }

    @Test
    void testConvert_CGFTag() {
        // Mockando o método estático do CGFRequestMapper
        try (MockedStatic<CGFRequestMapper> mockedMapper = mockStatic(CGFRequestMapper.class)) {
            // Dados de entrada
            RequestDTO input = new RequestDTO();
            input.setId("123");

            // Configurando o mock para retornar um DataModel esperado
            DataModel expectedModel = new DataModel();
            expectedModel.setId("123");
            expectedModel.setStatus("Status para CGF");

            mockedMapper.when(() -> CGFRequestMapper.map(input)).thenReturn(expectedModel);

            // Chamada do método a ser testado
            DataModel result = transformer.convert(input, TagEnum.CGF);

            // Verificações
            assertEquals(expectedModel, result);
            mockedMapper.verify(() -> CGFRequestMapper.map(input), times(1));
        }
    }

    @Test
    void testConvert_AnotherTag() {
        // Mockando o método estático do AnotherTagRequestMapper
        try (MockedStatic<AnotherTagRequestMapper> mockedMapper = mockStatic(AnotherTagRequestMapper.class)) {
            // Dados de entrada
            RequestDTO input = new RequestDTO();
            input.setId("456");

            // Configurando o mock para retornar um DataModel esperado
            DataModel expectedModel = new DataModel();
            expectedModel.setId("456");
            expectedModel.setStatus("Status para ANOTHER_TAG");

            mockedMapper.when(() -> AnotherTagRequestMapper.map(input)).thenReturn(expectedModel);

            // Chamada do método a ser testado
            DataModel result = transformer.convert(input, TagEnum.ANOTHER_TAG);

            // Verificações
            assertEquals(expectedModel, result);
            mockedMapper.verify(() -> AnotherTagRequestMapper.map(input), times(1));
        }
    }

    @Test
    void testConvert_DefaultTag() {
        // Mockando o método estático do DefaultRequestMapper
        try (MockedStatic<DefaultRequestMapper> mockedMapper = mockStatic(DefaultRequestMapper.class)) {
            // Dados de entrada
            RequestDTO input = new RequestDTO();
            input.setId("789");

            // Configurando o mock para retornar um DataModel esperado
            DataModel expectedModel = new DataModel();
            expectedModel.setId("789");
            expectedModel.setStatus("Status padrão");

            mockedMapper.when(() -> DefaultRequestMapper.map(input)).thenReturn(expectedModel);

            // Chamada do método a ser testado
            DataModel result = transformer.convert(input, TagEnum.OTHER);

            // Verificações
            assertEquals(expectedModel, result);
            mockedMapper.verify(() -> DefaultRequestMapper.map(input), times(1));
        }
    }
}
