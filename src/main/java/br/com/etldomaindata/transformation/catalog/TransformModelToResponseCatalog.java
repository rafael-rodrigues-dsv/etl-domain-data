package br.com.etldomaindata.transformation.catalog;

import br.com.etldomaindata.transformation.converter.Converter;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;

public class TransformModelToResponseCatalog implements Converter<DataModel, ResponseDTO> {

    @Override
    public ResponseDTO convert(DataModel input, TagEnum tagEnum) {
        ResponseDTO response = new ResponseDTO();

        // Lógica baseada no tagEnum
        if (tagEnum == TagEnum.CGF) {
            response.setId(input.getId());
            response.setStatus(input.getStatus());
        } else if (tagEnum == TagEnum.ANOTHER_TAG) {
            // Outra lógica baseada em tagEnum
            response.setId(input.getId());
            response.setStatus("Status personalizado para ANOTHER_TAG");
        } else {
            // Lógica padrão para outros tagEnum
            response.setId(input.getId());
            response.setStatus("Status padrão");
        }

        return response;
    }
}