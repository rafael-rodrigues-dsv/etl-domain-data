
# Project Documentation

This document provides information about the project's structure, installation guide, and an example of dependency injection and usage.

---

## Project Structure

### **Project Tree**

```plaintext
src/main/java/
└── br.com.etldomaindata.transformation
    ├── catalog
    │   └── TransformModelToResponseCatalog.java
    ├── dto
    │   ├── RequestDTO.java
    │   └── ResponseDTO.java
    ├── enumeration
    │   └── TagEnum.java
    ├── model
    │   └── DataModel.java
    ├── conversion
    │   ├── BaseTransformModelToResponseCatalog.java
    │   └── TransformModelToResponseCatalogImpl.java
    └── service
        ├── TransformationConverter.java
        └── TransformationServiceImpl.java
```

---

## Installation Guide

To install and use the library in your project, follow these steps:

### 1. Add the required dependencies to your `pom.xml` (Maven) or `build.gradle` (Gradle), depending on your build tool.

#### For Maven:

```xml
<dependency>
    <groupId>br.com.etldomaindata</groupId>
    <artifactId>transformation</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### For Gradle:

```gradle
implementation 'br.com.etldomaindata:transformation:1.0.0'
```

### 2. Import the necessary classes wherever needed in your project.

---

## Example of Dependency Injection and Usage

### 1. **Configuration Class for Dependency Injection**

```java
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
```

### 2. **Main Application Class**

```java
package br.com.etldomaindata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@lombok.Generated
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args); // Starts the Spring Boot application
    }
}
```

### 3. **Class Using the Injected `TransformationService`**

```java
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
    private TransformationService transformationService; // Automatic injection by Spring

    @Override
    public void run(String... args) throws Exception {
        // Initial input
        RequestDTO request = RequestDTO.builder()
                .id("123")
                .name("Test Example")
                .build();

        // ETL: Using enum to determine catalog and transform with tagEnum
        TagEnum tagEnum = TagEnum.CGF;  // Using TagEnum directly
        DataModel model = transformationService.transform(request, tagEnum, DataModel.class);
        System.out.println("Model: " + model.getId() + ", Status: " + model.getStatus());

        // ETL: DataModel -> ResponseDTO
        tagEnum = TagEnum.ANOTHER_TAG;  // Using another value from TagEnum
        ResponseDTO response = transformationService.transform(model, tagEnum, ResponseDTO.class);
        System.out.println("Response: " + response.getId() + ", Status: " + response.getStatus());
    }
}
```

---

With this guide, you can easily set up the library in your project, inject the necessary service, and begin using it for transformations.
