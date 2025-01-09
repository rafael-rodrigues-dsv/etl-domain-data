package br.com.etldomaindata.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TagEnumTest {

    @Test
    void testEnumValues() {
        // Verifica se o enum contém os valores esperados
        assertEquals("CGF", TagEnum.CGF.name());
        assertEquals("ANOTHER_TAG", TagEnum.ANOTHER_TAG.name());
    }

    @Test
    void testEnumValuesOrder() {
        // Verifica se a ordem dos valores no enum está correta
        TagEnum[] values = TagEnum.values();
        assertEquals(2, values.length);
        assertEquals(TagEnum.CGF, values[0]);
        assertEquals(TagEnum.ANOTHER_TAG, values[1]);
    }

    @Test
    void testEnumValueOf() {
        // Verifica se a conversão de String para enum funciona corretamente
        assertEquals(TagEnum.CGF, TagEnum.valueOf("CGF"));
        assertEquals(TagEnum.ANOTHER_TAG, TagEnum.valueOf("ANOTHER_TAG"));
    }

    @Test
    void testEnumValueOf_ThrowsException_WhenInvalidName() {
        // Verifica se uma exceção é lançada ao tentar obter um valor do enum com um nome inválido
        assertThrows(IllegalArgumentException.class, () -> {
            TagEnum.valueOf("INVALID_TAG");
        });
    }
}
