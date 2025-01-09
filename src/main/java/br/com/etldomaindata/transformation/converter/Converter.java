package br.com.etldomaindata.transformation.converter;

import br.com.etldomaindata.enumeration.TagEnum;

/**
 * Interface genérica para conversores.
 */
public interface Converter<I, O> {
    O convert(I input, TagEnum tagEnum);  // Agora o método recebe um TagEnum
}
