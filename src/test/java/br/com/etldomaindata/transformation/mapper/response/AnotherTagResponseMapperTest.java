package br.com.etldomaindata.transformation.mapper.response;

import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnotherTagResponseMapperTest {

    @Test
    void testMap() {
        // Dados de entrada
        DataModel input = new DataModel();
        input.setId("123");

        // Chamada do método estático
        ResponseDTO result = AnotherTagResponseMapper.map(input);

        // Verificações
        assertEquals("123", result.getId(), "O ID retornado deve ser igual ao ID de entrada.");
        assertEquals("Status personalizado para ANOTHER_TAG", result.getStatus(), "O status retornado deve corresponder ao esperado para ANOTHER_TAG.");
    }
}
