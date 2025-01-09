package br.com.etldomaindata.config;

import br.com.etldomaindata.transformation.service.TransformationService;
import br.com.etldomaindata.transformation.service.impl.TransformationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@lombok.Generated
public class ETLConfig {

    @Bean
    public TransformationService transformationService() {
        return new TransformationServiceImpl();
    }
}