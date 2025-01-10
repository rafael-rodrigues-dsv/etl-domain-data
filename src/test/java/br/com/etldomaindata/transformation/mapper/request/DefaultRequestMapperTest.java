package br.com.etldomaindata.transformation.mapper.request;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.model.DataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultRequestMapperTest {

    @Test
    void testMap() {
        // Dados de entrada
        RequestDTO input = new RequestDTO();
        input.setId("789");

        // Chamada do método estático
        DataModel result = DefaultRequestMapper.map(input);

        // Verificações
        assertEquals("789", result.getId(), "O ID retornado deve ser igual ao ID de entrada.");
        assertEquals("Status padrão", result.getStatus(), "O status retornado deve corresponder ao esperado para o Default.");
    }
}
