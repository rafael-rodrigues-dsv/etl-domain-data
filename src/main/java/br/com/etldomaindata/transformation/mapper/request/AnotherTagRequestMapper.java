package br.com.etldomaindata.transformation.mapper.request;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.model.DataModel;

public class AnotherTagRequestMapper {

    public static DataModel map(RequestDTO input) {
        DataModel model = new DataModel();
        model.setId(input.getId());
        model.setStatus("Status para ANOTHER_TAG");
        return model;
    }
}

