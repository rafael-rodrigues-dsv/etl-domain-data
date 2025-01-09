package br.com.etldomaindata.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResponseDTOTest {

    @Test
    void testBuilder() {
        // Usando o builder para criar uma instância de ResponseDTO
        ResponseDTO responseDTO = ResponseDTO.builder()
                .id("123")
                .status("Active")
                .build();

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", responseDTO.getId());
        assertEquals("Active", responseDTO.getStatus());
    }

    @Test
    void testNoArgsConstructor() {
        // Criando uma instância sem passar parâmetros
        ResponseDTO responseDTO = new ResponseDTO();

        // Verificando se os valores padrão são null
        assertNull(responseDTO.getId());
        assertNull(responseDTO.getStatus());
    }

    @Test
    void testAllArgsConstructor() {
        // Usando o construtor com todos os parâmetros
        ResponseDTO responseDTO = new ResponseDTO("123", "Active");

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", responseDTO.getId());
        assertEquals("Active", responseDTO.getStatus());
    }

    @Test
    void testSettersAndGetters() {
        // Criando uma instância usando o construtor vazio
        ResponseDTO responseDTO = new ResponseDTO();

        // Usando os setters para definir os valores
        responseDTO.setId("123");
        responseDTO.setStatus("Active");

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", responseDTO.getId());
        assertEquals("Active", responseDTO.getStatus());
    }
}
