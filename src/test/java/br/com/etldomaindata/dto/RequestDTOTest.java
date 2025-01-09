package br.com.etldomaindata.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequestDTOTest {

    @Test
    void testBuilder() {
        // Usando o builder para criar uma instância de RequestDTO
        RequestDTO requestDTO = RequestDTO.builder()
                .id("123")
                .name("Test Name")
                .build();

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", requestDTO.getId());
        assertEquals("Test Name", requestDTO.getName());
    }

    @Test
    void testNoArgsConstructor() {
        // Criando uma instância sem passar parâmetros
        RequestDTO requestDTO = new RequestDTO();

        // Verificando se os valores padrão são null
        assertNull(requestDTO.getId());
        assertNull(requestDTO.getName());
    }

    @Test
    void testAllArgsConstructor() {
        // Usando o construtor com todos os parâmetros
        RequestDTO requestDTO = new RequestDTO("123", "Test Name");

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", requestDTO.getId());
        assertEquals("Test Name", requestDTO.getName());
    }

    @Test
    void testSettersAndGetters() {
        // Criando uma instância usando o construtor vazio
        RequestDTO requestDTO = new RequestDTO();

        // Usando os setters para definir os valores
        requestDTO.setId("123");
        requestDTO.setName("Test Name");

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", requestDTO.getId());
        assertEquals("Test Name", requestDTO.getName());
    }
}
