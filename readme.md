# Project Structure Documentation

This document explains the purpose of each folder and file in the `src/main/java/br.com.etldomaindata.transformation` directory.

---

## **English**

### **Folder and File Descriptions**

#### **Project Tree**
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

1. **`catalog`**
    - Contains classes responsible for managing and transforming catalog-related data.
    - **File:**
        - `TransformModelToResponseCatalog.java`: A class that transforms model data into a response format specific to catalogs.

2. **`dto`**
    - Holds Data Transfer Object (DTO) classes used for transferring data between different layers of the application.
    - **Files:**
        - `RequestDTO.java`: Represents the structure of incoming requests.
        - `ResponseDTO.java`: Represents the structure of outgoing responses.

3. **`enumeration`**
    - Stores enums for predefined sets of constants used across the application.
    - **File:**
        - `TagEnum.java`: Enum defining various tags for categorization.

4. **`model`**
    - Contains classes representing the core data models of the application.
    - **File:**
        - `DataModel.java`: The main data model class.

5. **`conversion`**
    - Focuses on the transformation of data models into different formats.
    - **Files:**
        - `BaseTransformModelToResponseCatalog.java`: Base class for catalog transformation operations.
        - `TransformModelToResponseCatalogImpl.java`: Implementation of the catalog transformation logic.

6. **`service`**
    - Houses service classes that encapsulate business logic and operations.
    - **Files:**
        - `TransformationConverter.java`: Utility class for converting data during transformation processes.
        - `TransformationServiceImpl.java`: Service implementation class that handles the transformation workflows.

---

## **Português**

### **Descrição das Pastas e Arquivos**

#### **Árvore do Projeto**
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

1. **`catalog`**
    - Contém classes responsáveis por gerenciar e transformar dados relacionados a catálogos.
    - **Arquivo:**
        - `TransformModelToResponseCatalog.java`: Classe que transforma dados de modelo em um formato de resposta específico para catálogos.

2. **`dto`**
    - Armazena classes de Objetos de Transferência de Dados (DTO) usadas para transferir dados entre diferentes camadas da aplicação.
    - **Arquivos:**
        - `RequestDTO.java`: Representa a estrutura de requisições recebidas.
        - `ResponseDTO.java`: Representa a estrutura de respostas enviadas.

3. **`enumeration`**
    - Guarda enums para conjuntos predefinidos de constantes utilizadas na aplicação.
    - **Arquivo:**
        - `TagEnum.java`: Enum que define diversas tags para categorização.

4. **`model`**
    - Contém classes que representam os modelos de dados principais da aplicação.
    - **Arquivo:**
        - `DataModel.java`: Classe principal do modelo de dados.

5. **`conversion`**
    - Focado na transformação de modelos de dados em diferentes formatos.
    - **Arquivos:**
        - `BaseTransformModelToResponseCatalog.java`: Classe base para operações de transformação de catálogos.
        - `TransformModelToResponseCatalogImpl.java`: Implementação da lógica de transformação de catálogos.

6. **`service`**
    - Abriga classes de serviço que encapsulam lógica de negócio e operações.
    - **Arquivos:**
        - `TransformationConverter.java`: Classe utilitária para conversão de dados durante os processos de transformação.
        - `TransformationServiceImpl.java`: Classe de implementação de serviço que lida com os fluxos de trabalho de transformação.
