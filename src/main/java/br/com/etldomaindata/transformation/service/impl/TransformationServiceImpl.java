package br.com.etldomaindata.transformation.service.impl;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.catalog.TransformModelToResponseCatalog;
import br.com.etldomaindata.transformation.catalog.TransformRequestToModelCatalog;
import br.com.etldomaindata.transformation.converter.Converter;
import br.com.etldomaindata.transformation.service.TransformationService;

public class TransformationServiceImpl implements TransformationService {

    private final TransformModelToResponseCatalog transformModelToResponseCatalog;
    private final TransformRequestToModelCatalog transformRequestToModelCatalog;

    public TransformationServiceImpl() {
        this.transformModelToResponseCatalog = new TransformModelToResponseCatalog();
        this.transformRequestToModelCatalog = new TransformRequestToModelCatalog();
    }

    @Override
    public <I, O> O transform(I input, TagEnum tagEnum, Class<I> inputClass, Class<O> outputClass) {
        Converter<I, O> converter = getConverterForClasses(inputClass, outputClass);
        if (converter == null) {
            throw new IllegalArgumentException("Nenhum conversor registrado para: "
                    + inputClass.getName() + " -> " + outputClass.getName());
        }
        return converter.convert(input, tagEnum);  // Passa o tagEnum para o método convert
    }

    private <I, O> Converter<I, O> getConverterForClasses(Class<I> inputClass, Class<O> outputClass) {
        if (inputClass.equals(RequestDTO.class) && outputClass.equals(DataModel.class)) {
            return (Converter<I, O>) transformRequestToModelCatalog;
        } else if (inputClass.equals(DataModel.class) && outputClass.equals(ResponseDTO.class)) {
            return (Converter<I, O>) transformModelToResponseCatalog;
        } else {
            return null;
        }
    }
}
