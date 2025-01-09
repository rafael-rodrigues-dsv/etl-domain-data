package br.com.etldomaindata.transformation.converter;

import br.com.etldomaindata.transformation.catalog.TransformModelToResponseCatalog;
import br.com.etldomaindata.transformation.catalog.TransformRequestToModelCatalog;
import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;

public class ConverterRegistry {

    public static void registerAll(ConverterCatalog catalog) {
        catalog.registerConverter(RequestDTO.class, DataModel.class, new TransformRequestToModelCatalog());
        catalog.registerConverter(DataModel.class, ResponseDTO.class, new TransformModelToResponseCatalog());
    }
}
