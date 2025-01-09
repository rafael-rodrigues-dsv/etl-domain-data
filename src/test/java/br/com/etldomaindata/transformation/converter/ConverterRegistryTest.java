package br.com.etldomaindata.transformation.converter;

import br.com.etldomaindata.transformation.catalog.TransformModelToResponseCatalog;
import br.com.etldomaindata.transformation.catalog.TransformRequestToModelCatalog;
import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConverterRegistryTest {

    private ConverterCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new ConverterCatalog();  // Cria uma nova instância do catálogo antes de cada teste
    }

    @Test
    void testRegisterAll() {
        // Chama o método registerAll para registrar os conversores
        ConverterRegistry.registerAll(catalog);

        // Verifica se os conversores foram registrados corretamente
        assertNotNull(catalog.getConverter(RequestDTO.class, DataModel.class));
        assertNotNull(catalog.getConverter(DataModel.class, ResponseDTO.class));
    }

    @Test
    void testRegisterRequestDTO_to_DataModel() {
        // Registra o conversor individualmente
        catalog.registerConverter(RequestDTO.class, DataModel.class, new TransformRequestToModelCatalog());

        // Verifica se o conversor foi registrado corretamente
        assertNotNull(catalog.getConverter(RequestDTO.class, DataModel.class));
    }

    @Test
    void testRegisterDataModel_to_ResponseDTO() {
        // Registra o conversor individualmente
        catalog.registerConverter(DataModel.class, ResponseDTO.class, new TransformModelToResponseCatalog());

        // Verifica se o conversor foi registrado corretamente
        assertNotNull(catalog.getConverter(DataModel.class, ResponseDTO.class));
    }

    @Test
    void testGetConverterForInvalidType() {
        // Teste de um tipo inválido
        assertNull(catalog.getConverter(RequestDTO.class, ResponseDTO.class));
    }
}
