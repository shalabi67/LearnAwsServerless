package com.serverless.framework.dynamodb.factories;

import com.serverless.framework.dynamodb.exceptions.ModelInstantiationException;

public class ClassFactory {
    public static <Model> Model createModel(Model model) {
        Model newModel;
        try {
            newModel = (Model)model.getClass().newInstance();
        } catch (Exception e) {
            throw new ModelInstantiationException("Could not instantiate model of type " + model.getClass().getTypeName(), e);
        }

        return newModel;
    }
}
