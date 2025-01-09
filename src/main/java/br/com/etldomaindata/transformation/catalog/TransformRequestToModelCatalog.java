package br.com.etldomaindata.transformation.catalog;

import br.com.etldomaindata.transformation.converter.Converter;
import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;

public class TransformRequestToModelCatalog implements Converter<RequestDTO, DataModel> {

    @Override
    public DataModel convert(RequestDTO input, TagEnum tagEnum) {
        DataModel model = new DataModel();

        // Lógica baseada no tagEnum
        if (tagEnum == TagEnum.CGF) {
            model.setId(input.getId());
            model.setStatus("Status para CGF");
        } else if (tagEnum == TagEnum.ANOTHER_TAG) {
            // Outra lógica baseada em tagEnum
            model.setId(input.getId());
            model.setStatus("Status para ANOTHER_TAG");
        } else {
            // Lógica padrão para outros tagEnum
            model.setId(input.getId());
            model.setStatus("Status padrão");
        }

        return model;
    }
}
