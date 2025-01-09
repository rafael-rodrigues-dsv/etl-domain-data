package br.com.etldomaindata.service;

import br.com.etldomaindata.converter.Converter;
import br.com.etldomaindata.converter.ConverterCatalog;

public class TransformationServiceImpl {
    private final ConverterCatalog catalog;

    public TransformationServiceImpl(ConverterCatalog catalog) {
        this.catalog = catalog;
    }

    public <I, O> O transform(I input, Class<I> inputClass, Class<O> outputClass) {
        Converter<I, O> converter = catalog.getConverter(inputClass, outputClass);
        if (converter == null) {
            throw new IllegalArgumentException("Nenhum conversor registrado para: "
                    + inputClass.getName() + " -> " + outputClass.getName());
        }
        return converter.convert(input);
    }
}
