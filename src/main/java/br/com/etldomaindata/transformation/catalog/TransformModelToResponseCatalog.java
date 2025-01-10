package br.com.etldomaindata.transformation.catalog;

import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.converter.TransformationConverter;
import br.com.etldomaindata.transformation.mapper.response.CGFResponseMapper;
import br.com.etldomaindata.transformation.mapper.response.AnotherTagResponseMapper;
import br.com.etldomaindata.transformation.mapper.response.DefaultResponseMapper;

public class TransformModelToResponseCatalog implements TransformationConverter<DataModel, ResponseDTO> {

    @Override
    public ResponseDTO convert(DataModel input, TagEnum tagEnum) {
        switch (tagEnum) {
            case CGF:
                return CGFResponseMapper.map(input);
            case ANOTHER_TAG:
                return AnotherTagResponseMapper.map(input);
            default:
                return DefaultResponseMapper.map(input);
        }
    }
}
