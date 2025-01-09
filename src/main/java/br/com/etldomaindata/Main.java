package br.com.etldomaindata;

import br.com.etldomaindata.converter.ConverterCatalog;
import br.com.etldomaindata.converter.ConverterRegistry;
import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.service.TransformationServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Catálogo e ETL configurados automaticamente
        ConverterCatalog catalog = new ConverterCatalog();

        // Registrar todos os conversores
        ConverterRegistry.registerAll(catalog);

        TransformationServiceImpl transformationServiceImpl = new TransformationServiceImpl(catalog);

        // Entrada inicial
        RequestDTO request = RequestDTO.builder()
                .id("123")
                .name("Teste Automático")
                .build();

        // ETL: RequestDTO -> DataModel
        DataModel model = transformationServiceImpl.transform(request, RequestDTO.class, DataModel.class);
        System.out.println("Model: " + model.getName() + ", Status: " + model.getStatus());

        // ETL: DataModel -> ResponseDTO
        ResponseDTO response = transformationServiceImpl.transform(model, DataModel.class, ResponseDTO.class);
        System.out.println("Response: " + response.getId() + ", Status: " + response.getStatus());
    }
}