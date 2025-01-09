package br.com.etldomaindata.converter;

/**
 * Interface genérica para conversores.
 */
public interface Converter<I, O> {
    O convert(I input);
}
