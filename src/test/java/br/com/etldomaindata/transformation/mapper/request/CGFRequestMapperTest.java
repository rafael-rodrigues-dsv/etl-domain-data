package br.com.etldomaindata.transformation.mapper.request;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.model.DataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CGFRequestMapperTest {

    @Test
    void testMap() {
        // Dados de entrada
        RequestDTO input = new RequestDTO();
        input.setId("456");

        // Chamada do método estático
        DataModel result = CGFRequestMapper.map(input);

        // Verificações
        assertEquals("456", result.getId(), "O ID retornado deve ser igual ao ID de entrada.");
        assertEquals("Status para CGF", result.getStatus(), "O status retornado deve corresponder ao esperado para CGF.");
    }
}
