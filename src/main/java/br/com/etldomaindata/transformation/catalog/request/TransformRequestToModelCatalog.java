package br.com.etldomaindata.transformation.catalog.request;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.converter.TransformationConverter;
import br.com.etldomaindata.transformation.mapper.request.AnotherTagRequestMapper;
import br.com.etldomaindata.transformation.mapper.request.CGFRequestMapper;
import br.com.etldomaindata.transformation.mapper.request.DefaultRequestMapper;

public class TransformRequestToModelCatalog implements TransformationConverter<RequestDTO, DataModel> {

    @Override
    public DataModel convert(RequestDTO input, TagEnum tagEnum) {
        switch (tagEnum) {
            case CGF:
                return CGFRequestMapper.map(input);
            case ANOTHER_TAG:
                return AnotherTagRequestMapper.map(input);
            default:
                return DefaultRequestMapper.map(input);
        }
    }
}

