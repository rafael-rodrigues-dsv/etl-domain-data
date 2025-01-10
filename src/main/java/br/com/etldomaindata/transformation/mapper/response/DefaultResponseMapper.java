package br.com.etldomaindata.transformation.mapper.response;

import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;

public class DefaultResponseMapper {
    public static ResponseDTO map(DataModel input) {
        ResponseDTO response = new ResponseDTO();
        response.setId(input.getId());
        response.setStatus("Status padrão");
        return response;
    }
}

