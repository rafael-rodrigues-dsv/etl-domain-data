package br.com.etldomaindata.transformation.service.impl;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.catalog.TransformModelToResponseCatalog;
import br.com.etldomaindata.transformation.catalog.TransformRequestToModelCatalog;
import br.com.etldomaindata.transformation.converter.TransformationConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransformationServiceImplTest {

    private TransformationServiceImpl service;
    private TransformModelToResponseCatalog modelToResponseCatalogMock;
    private TransformRequestToModelCatalog requestToModelCatalogMock;

    @BeforeEach
    void setUp() {
        service = new TransformationServiceImpl();

        // Mockar os catalogues
        modelToResponseCatalogMock = mock(TransformModelToResponseCatalog.class);
        when(modelToResponseCatalogMock.convert(any(DataModel.class), any(TagEnum.class))).thenReturn(new ResponseDTO());

        requestToModelCatalogMock = mock(TransformRequestToModelCatalog.class);
        when(requestToModelCatalogMock.convert(any(RequestDTO.class), any(TagEnum.class))).thenReturn(new DataModel());

        // Inicializar o serviço com os mocks
        service.initializeConverters();
    }

    @Test
    void testInitializeConverters() {
        assertEquals(2, service.converters.size());
        assertTrue(service.converters.containsKey(ResponseDTO.class));
        assertTrue(service.converters.containsKey(RequestDTO.class));
    }

    @Test
    void testGetConverterForClasses() {
        TransformationConverter<DataModel, ResponseDTO> converter =
                (TransformationConverter<DataModel, ResponseDTO>) service.getConverterForClasses(DataModel.class, ResponseDTO.class);
        assertNotNull(converter);

        TransformationConverter<RequestDTO, DataModel> requestToDataConverter =
                (TransformationConverter<RequestDTO, DataModel>) service.getConverterForClasses(RequestDTO.class, DataModel.class);
        assertNotNull(requestToDataConverter);

        TransformationConverter<DataModel, RequestDTO> dataToRequestConverter =
                (TransformationConverter<DataModel, RequestDTO>) service.getConverterForClasses(DataModel.class, RequestDTO.class);
        assertNull(dataToRequestConverter);
    }

    @Test
    void testTransform() {
        DataModel input = new DataModel();
        TagEnum tagEnum = TagEnum.ANOTHER_TAG;
        Class<ResponseDTO> outputClass = ResponseDTO.class;

        ResponseDTO result = service.transform(input, tagEnum, outputClass);
        assertNotNull(result);
    }
}
