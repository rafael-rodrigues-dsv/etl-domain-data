package br.com.etldomaindata.transformation.mapper.response;

import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CGFResponseMapperTest {

    @Test
    void testMap() {
        // Dados de entrada
        DataModel input = new DataModel();
        input.setId("456");
        input.setStatus("Status para CGF");

        // Chamada do método estático
        ResponseDTO result = CGFResponseMapper.map(input);

        // Verificações
        assertEquals("456", result.getId(), "O ID retornado deve ser igual ao ID de entrada.");
        assertEquals("Status para CGF", result.getStatus(), "O status retornado deve corresponder ao esperado para CGF.");
    }
}
