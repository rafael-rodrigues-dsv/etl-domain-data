package br.com.etldomaindata.catalog;

import br.com.etldomaindata.converter.Converter;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;

public class ModelToResponseCatalog implements Converter<DataModel, ResponseDTO> {

    @Override
    public ResponseDTO convert(DataModel input) {
        ResponseDTO response = new ResponseDTO();
        response.setId(input.getId());
        response.setStatus(input.getStatus());
        return response;
    }
}
