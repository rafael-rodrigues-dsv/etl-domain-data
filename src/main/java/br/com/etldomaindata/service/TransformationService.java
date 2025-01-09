package br.com.etldomaindata.service;

public interface TransformationService {
    <I, O> O transform(I input, Class<I> inputClass, Class<O> outputClass);
}
