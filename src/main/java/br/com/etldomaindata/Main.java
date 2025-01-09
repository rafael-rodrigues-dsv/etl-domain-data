package br.com.etldomaindata;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    private TransformationService transformationService; // Injeção automática do Spring

    @Override
    public void run(String... args) throws Exception {
        // Entrada inicial
        RequestDTO request = RequestDTO.builder()
                .id("123")
                .name("Teste Automático")
                .build();

        // ETL: RequestDTO -> DataModel
        DataModel model = transformationService.transform(request, RequestDTO.class, DataModel.class);
        System.out.println("Model: " + model.getName() + ", Status: " + model.getStatus());

        // ETL: DataModel -> ResponseDTO
        ResponseDTO response = transformationService.transform(model, DataModel.class, ResponseDTO.class);
        System.out.println("Response: " + response.getId() + ", Status: " + response.getStatus());
    }
}