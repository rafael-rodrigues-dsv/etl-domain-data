package br.com.etldomaindata.config;

import br.com.etldomaindata.converter.ConverterCatalog;
import br.com.etldomaindata.service.TransformationService;
import br.com.etldomaindata.service.impl.TransformationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ETLCatalogConfig {

    @Bean
    public TransformationService transformationService() {
        ConverterCatalog catalog = new ConverterCatalog();
        return new TransformationServiceImpl(catalog); // Cria o serviço de transformação
    }
}
