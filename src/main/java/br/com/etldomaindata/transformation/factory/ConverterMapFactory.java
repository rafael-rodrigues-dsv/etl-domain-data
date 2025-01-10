package br.com.etldomaindata.transformation.factory;

import br.com.etldomaindata.transformation.converter.TransformationConverter;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConverterMapFactory {

    // Método para criar o mapa de conversores dinamicamente
    public static Map<Class<?>, Map<Class<?>, TransformationConverter<?, ?>>> createConverterMap() {
        Map<Class<?>, Map<Class<?>, TransformationConverter<?, ?>>> converterMap = new HashMap<>();

        // Usar a biblioteca Reflections para buscar todas as classes de conversores
        Reflections reflections = new Reflections("br.com.etldomaindata.transformation.catalog");
        Set<Class<? extends TransformationConverter>> converterClasses = reflections.getSubTypesOf(TransformationConverter.class);

        for (Class<? extends TransformationConverter> converterClass : converterClasses) {
            // Obter os tipos de entrada e saída do conversor usando reflexão
            Type[] genericInterfaces = converterClass.getGenericInterfaces();
            for (Type type : genericInterfaces) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Class<?> inputType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    Class<?> outputType = (Class<?>) parameterizedType.getActualTypeArguments()[1];

                    // Adicionar o conversor ao mapa
                    converterMap
                            .computeIfAbsent(inputType, k -> new HashMap<>())
                            .put(outputType, instantiateConverter(converterClass, inputType, outputType));
                }
            }
        }

        return converterMap;
    }

    // Método para instanciar dinamicamente o conversor usando reflexão
    private static <T, U> TransformationConverter<T, U> instantiateConverter(
            Class<? extends TransformationConverter> converterClass,
            Class<?> inputType,
            Class<?> outputType) {
        try {
            // Instanciamos o conversor com os tipos genéricos corretos
            return converterClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao instanciar o conversor: " + converterClass.getName(), e);
        }
    }
}
