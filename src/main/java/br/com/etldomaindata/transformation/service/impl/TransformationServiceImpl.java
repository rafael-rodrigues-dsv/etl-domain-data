package br.com.etldomaindata.transformation.service.impl;

import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.transformation.converter.TransformationConverter;
import br.com.etldomaindata.transformation.factory.ConverterMapFactory;
import br.com.etldomaindata.transformation.service.TransformationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransformationServiceImpl implements TransformationService {

    private static final Map<Class<?>, Map<Class<?>, TransformationConverter<?, ?>>> converterMap = ConverterMapFactory.createConverterMap();

    @Override
    public <T, U> U transform(T input, TagEnum tagEnum, Class<U> outputClass) {
        TransformationConverter<T, U> converter = getConverter(input, outputClass);

        return converter.convert(input, tagEnum);
    }

    private <T, U> TransformationConverter<T, U> getConverter(T input, Class<U> outputClass) {
        Map<Class<?>, TransformationConverter<?, ?>> outputConverters = converterMap.get(input.getClass());

        if (outputConverters == null) {
            throw new IllegalArgumentException("Nenhum conversor encontrado para a classe de entrada: " + input.getClass());
        }

        TransformationConverter<T, U> converter = (TransformationConverter<T, U>) outputConverters.get(outputClass);

        if (converter == null) {
            throw new IllegalArgumentException("Nenhum conversor encontrado para a conversão de "
                    + input.getClass() + " para " + outputClass);
        }

        return converter;
    }
}
