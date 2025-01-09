package br.com.etldomaindata.transformation.service;

import br.com.etldomaindata.enumeration.TagEnum;

public interface TransformationService {

    /**
     * Realiza a transformação de um objeto de entrada para um objeto de saída com base no tagEnum.
     *
     * @param input O objeto de entrada a ser transformado
     * @param tagEnum O enum que determina o comportamento da transformação
     * @param inputClass A classe do objeto de entrada
     * @param outputClass A classe do objeto de saída
     * @param <I> Tipo do objeto de entrada
     * @param <O> Tipo do objeto de saída
     * @return O objeto transformado no tipo desejado
     */
    <I, O> O transform(I input, TagEnum tagEnum, Class<I> inputClass, Class<O> outputClass);
}
