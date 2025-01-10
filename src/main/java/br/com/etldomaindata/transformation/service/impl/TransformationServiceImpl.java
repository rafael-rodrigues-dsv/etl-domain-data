package br.com.etldomaindata.transformation.service.impl;

import br.com.etldomaindata.dto.RequestDTO;
import br.com.etldomaindata.dto.ResponseDTO;
import br.com.etldomaindata.enumeration.TagEnum;
import br.com.etldomaindata.model.DataModel;
import br.com.etldomaindata.transformation.catalog.response.TransformModelToResponseCatalog;
import br.com.etldomaindata.transformation.catalog.request.TransformRequestToModelCatalog;
import br.com.etldomaindata.transformation.converter.TransformationConverter;
import br.com.etldomaindata.transformation.service.TransformationService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransformationServiceImpl implements TransformationService {

    protected final Map<Class<?>, Object> converters = new HashMap<>();

    public TransformationServiceImpl() {
        initializeConverters();
    }

    protected void initializeConverters() {
        List<Class<? extends TransformModelToResponseCatalog>> modelToResponseClasses = findCatalogClasses(TransformModelToResponseCatalog.class);
        List<Class<? extends TransformRequestToModelCatalog>> requestToModelClasses = findCatalogClasses(TransformRequestToModelCatalog.class);

        for (Class<? extends TransformModelToResponseCatalog> catalogClass : modelToResponseClasses) {
            try {
                Object instance = catalogClass.newInstance();
                if (instance instanceof TransformModelToResponseCatalog) {
                    converters.put(ResponseDTO.class, instance);
                }
            } catch (Exception e) {
                System.err.println("Erro ao instanciar cataloga de modelo para resposta: " + catalogClass.getName());
            }
        }

        for (Class<? extends TransformRequestToModelCatalog> catalogClass : requestToModelClasses) {
            try {
                Object instance = catalogClass.newInstance();
                if (instance instanceof TransformRequestToModelCatalog) {
                    converters.put(RequestDTO.class, instance);
                }
            } catch (Exception e) {
                System.err.println("Erro ao instanciar cataloga de requisição para modelo: " + catalogClass.getName());
            }
        }
    }

    private <T> List<Class<? extends T>> findCatalogClasses(Class<T> interfaceClass) {
        List<Class<? extends T>> catalogClasses = new ArrayList<>();

        String[] packagesToScan = {"br.com.etldomaindata.transformation.catalog"};

        for (String packageToScan : packagesToScan) {
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                String path = packageToScan.replace('.', '/');
                Enumeration<URL> resources = classLoader.getResources(path);

                while (resources.hasMoreElements()) {
                    URL resourceUrl = resources.nextElement();
                    String directory = resourceUrl.getFile();

                    File directoryFile = new File(directory);
                    String[] files = directoryFile.list();

                    for (String fileName : files) {
                        if (fileName.endsWith(".class")) {
                            String className = fileName.substring(0, fileName.length() - 6);
                            Class<?> clazz;
                            try {
                                clazz = classLoader.loadClass(packageToScan + "." + className);
                                if (interfaceClass.isAssignableFrom(clazz)) {
                                    catalogClasses.add((Class<? extends T>) clazz);
                                }
                            } catch (ClassNotFoundException e) {
                                System.err.println("Classe não encontrada: " + packageToScan + "." + className);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao acessar pacotes: " + e.getMessage());
            }
        }

        return catalogClasses;
    }

    protected <I, O> TransformationConverter<I, O> getConverterForClasses(Class<I> inputClass, Class<O> outputClass) {
        if (inputClass.equals(RequestDTO.class) && outputClass.equals(DataModel.class)) {
            return (TransformationConverter<I, O>) converters.get(RequestDTO.class);
        } else if (inputClass.equals(DataModel.class) && outputClass.equals(ResponseDTO.class)) {
            return (TransformationConverter<I, O>) converters.get(ResponseDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public <I, O> O transform(I input, TagEnum tagEnum, Class<O> outputClass) {
        TransformationConverter<I, O> transformationConverter = (TransformationConverter<I, O>) getConverterForClasses(input.getClass(), outputClass);
        if (transformationConverter == null) {
            throw new IllegalArgumentException("Nenhum conversor registrado para: "
                    + input.getClass().getName() + " -> " + outputClass.getName());
        }
        return transformationConverter.convert(input, tagEnum);
    }
}