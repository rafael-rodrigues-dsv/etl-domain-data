package br.com.etldomaindata.transformation.service.impl;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.catalog.TransformModelToResponseCatalog;
import br.com.etldomaindata.transformation.catalog.TransformRequestToModelCatalog;
import br.com.etldomaindata.transformation.converter.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TransformationServiceImplTest {

    @InjectMocks
    private TransformationServiceImpl transformationService;

    @Mock
    private TransformModelToResponseCatalog transformModelToResponseCatalog;

    @Mock
    private TransformRequestToModelCatalog transformRequestToModelCatalog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testTransform_RequestDTO_to_DataModel() {
        // Dados de entrada simulados
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId("123");
        requestDTO.setName("Test");

        // Dados de saída esperados
        DataModel expectedModel = new DataModel();
        expectedModel.setId("123");
        expectedModel.setName("Test");

        // Configurando o mock corretamente para converter RequestDTO para DataModel
        when(transformRequestToModelCatalog.convert(requestDTO, TagEnum.CGF))
                .thenReturn(expectedModel);

        // Chamada do método de transformação
        DataModel result = transformationService.transform(requestDTO, TagEnum.CGF, RequestDTO.class, DataModel.class);

        // Verificando o resultado
        assertNotNull(result);
    }

    @Test
    void testTransform_DataModel_to_ResponseDTO() {
        // Dados de entrada simulados
        DataModel dataModel = new DataModel();
        dataModel.setId("123");
        dataModel.setName("Test");

        ResponseDTO expectedResponse = new ResponseDTO();
        expectedResponse.setId("123");
        expectedResponse.setStatus("Active");

        // Configurando o mock
        when(transformModelToResponseCatalog.convert(dataModel, TagEnum.CGF))
                .thenReturn(expectedResponse);

        // Chamada do método
        ResponseDTO result = transformationService.transform(dataModel, TagEnum.CGF, DataModel.class, ResponseDTO.class);

        // Verificando o resultado
        assertNotNull(result);
    }

    @Test
    void testTransform_ThrowsException_WhenNoConverterFound() {
        // Dados de entrada simulados
        DataModel dataModel = new DataModel();
        dataModel.setId("123");

        // Chamada do método com tipos incompatíveis
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transformationService.transform(dataModel, TagEnum.CGF, DataModel.class, RequestDTO.class);
        });
    }

    @Test
    void testGetConverterForClasses() {
        // Testando o método privado (método não diretamente testável em JUnit, mas pode ser verificado indiretamente nos testes anteriores)
        TransformationServiceImpl service = new TransformationServiceImpl();

        // Espera que o conversor seja encontrado para RequestDTO -> DataModel
        Converter<RequestDTO, DataModel> converter = service.getConverterForClasses(RequestDTO.class, DataModel.class);
        assertNotNull(converter);

        // Espera que o conversor seja encontrado para DataModel -> ResponseDTO
        Converter<DataModel, ResponseDTO> converter2 = service.getConverterForClasses(DataModel.class, ResponseDTO.class);
        assertNotNull(converter2);
    }
}
