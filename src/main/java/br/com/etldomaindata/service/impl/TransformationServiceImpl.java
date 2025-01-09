package br.com.etldomaindata.service.impl;

import br.com.etldomaindata.converter.Converter;
import br.com.etldomaindata.converter.ConverterCatalog;
import br.com.etldomaindata.converter.ConverterRegistry;
import br.com.etldomaindata.service.TransformationService;

public class TransformationServiceImpl implements TransformationService {
    private final ConverterCatalog catalog;

    public TransformationServiceImpl(ConverterCatalog catalog) {
        this.catalog = catalog;
        ConverterRegistry.registerAll(catalog); // Registra todos os conversores ao inicializar
    }

    @Override
    public <I, O> O transform(I input, Class<I> inputClass, Class<O> outputClass) {
        Converter<I, O> converter = catalog.getConverter(inputClass, outputClass);
        if (converter == null) {
            throw new IllegalArgumentException("Nenhum conversor registrado para: "
                    + inputClass.getName() + " -> " + outputClass.getName());
        }
        return converter.convert(input);
    }
}
