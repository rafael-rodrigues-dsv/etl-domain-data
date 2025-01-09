package br.com.etldomaindata.converter;

import br.com.etldomaindata.converter.Converter;

import java.util.HashMap;
import java.util.Map;

public class ConverterCatalog {
    private final Map<String, Converter<?, ?>> converters = new HashMap<>();

    public <I, O> void registerConverter(Class<I> inputClass, Class<O> outputClass, Converter<I, O> converter) {
        String key = generateKey(inputClass, outputClass);
        converters.put(key, converter);
    }

    @SuppressWarnings("unchecked")
    public <I, O> Converter<I, O> getConverter(Class<I> inputClass, Class<O> outputClass) {
        String key = generateKey(inputClass, outputClass);
        return (Converter<I, O>) converters.get(key);
    }

    private String generateKey(Class<?> inputClass, Class<?> outputClass) {
        return inputClass.getName() + "->" + outputClass.getName();
    }
}