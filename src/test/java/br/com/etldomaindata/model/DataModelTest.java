package br.com.etldomaindata.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DataModelTest {

    @Test
    void testBuilder() {
        // Usando o builder para criar uma instância de DataModel
        DataModel dataModel = DataModel.builder()
                .id("123")
                .name("Test Name")
                .status("Active")
                .build();

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", dataModel.getId());
        assertEquals("Test Name", dataModel.getName());
        assertEquals("Active", dataModel.getStatus());
    }

    @Test
    void testNoArgsConstructor() {
        // Criando uma instância sem passar parâmetros
        DataModel dataModel = new DataModel();

        // Verificando se os valores padrão são null
        assertNull(dataModel.getId());
        assertNull(dataModel.getName());
        assertNull(dataModel.getStatus());
    }

    @Test
    void testAllArgsConstructor() {
        // Usando o construtor com todos os parâmetros
        DataModel dataModel = new DataModel("123", "Test Name", "Active");

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", dataModel.getId());
        assertEquals("Test Name", dataModel.getName());
        assertEquals("Active", dataModel.getStatus());
    }

    @Test
    void testSettersAndGetters() {
        // Criando uma instância usando o construtor vazio
        DataModel dataModel = new DataModel();

        // Usando os setters para definir os valores
        dataModel.setId("123");
        dataModel.setName("Test Name");
        dataModel.setStatus("Active");

        // Verificando se os valores foram configurados corretamente
        assertEquals("123", dataModel.getId());
        assertEquals("Test Name", dataModel.getName());
        assertEquals("Active", dataModel.getStatus());
    }
}
