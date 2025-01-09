package br.com.etldomaindata.converter;

import br.com.etldomaindata.catalog.ModelToResponseCatalog;
import br.com.etldomaindata.catalog.RequestToModelCatalog;
import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;

public class ConverterRegistry {

    public static void registerAll(ConverterCatalog catalog) {
        catalog.registerConverter(RequestDTO.class, DataModel.class, new RequestToModelCatalog());
        catalog.registerConverter(DataModel.class, ResponseDTO.class, new ModelToResponseCatalog());
    }
}
