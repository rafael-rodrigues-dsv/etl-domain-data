package br.com.etldomaindata.transformation.service;

import br.com.etldomaindata.enumeration.TagEnum;

public interface TransformationService {

    /**
     * Realiza a conversão de um objeto de entrada para um objeto de saída.
     *
     * @param input      O objeto de entrada a ser convertido
     * @param tagEnum    O enum de tag para determinar qual conversor utilizar
     * @param outputClass A classe do tipo de saída esperado
     * @return O objeto de saída resultante da conversão
     * @throws IllegalArgumentException Se nenhum conversor for encontrado para a combinação de entradas/saidas
     */
    <I, O> O transform(I input, TagEnum tagEnum, Class<O> outputClass);

}
