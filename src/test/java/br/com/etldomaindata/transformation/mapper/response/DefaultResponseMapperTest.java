package br.com.etldomaindata.transformation.mapper.response;

import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultResponseMapperTest {

    @Test
    void testMap() {
        // Dados de entrada
        DataModel input = new DataModel();
        input.setId("789");

        // Chamada do método estático
        ResponseDTO result = DefaultResponseMapper.map(input);

        // Verificações
        assertEquals("789", result.getId(), "O ID retornado deve ser igual ao ID de entrada.");
        assertEquals("Status padrão", result.getStatus(), "O status retornado deve corresponder ao esperado para o Default.");
    }
}
