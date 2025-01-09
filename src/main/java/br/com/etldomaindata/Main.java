package br.com.etldomaindata;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@lombok.Generated
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

        // ETL: Usando o enum para determinar o catálogo e fazendo a transformação com tagEnum
        TagEnum tagEnum = TagEnum.CGF;  // Usando o TagEnum diretamente
        DataModel model = transformationService.transform(request, tagEnum, RequestDTO.class, DataModel.class);
        System.out.println("Model: " + model.getId() + ", Status: " + model.getStatus());

        // ETL: DataModel -> ResponseDTO
        tagEnum = TagEnum.ANOTHER_TAG;  // Usando outro valor do TagEnum
        ResponseDTO response = transformationService.transform(model, tagEnum, DataModel.class, ResponseDTO.class);
        System.out.println("Response: " + response.getId() + ", Status: " + response.getStatus());
    }
}