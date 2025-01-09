package br.com.etldomaindata.catalog;

import br.com.etldomaindata.converter.Converter;
import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.model.DataModel;

public class RequestToModelCatalog implements Converter<RequestDTO, DataModel> {

    @Override
    public DataModel convert(RequestDTO input) {
        DataModel model = new DataModel();
        model.setId(input.getId());
        model.setName(input.getName());
        model.setStatus("NEW");
        return model;
    }
}

