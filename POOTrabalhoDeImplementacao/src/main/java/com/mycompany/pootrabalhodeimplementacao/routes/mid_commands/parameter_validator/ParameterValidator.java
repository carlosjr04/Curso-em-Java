/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author felipe
 */
public class ParameterValidator {

    Collection<IParameterValidatorStrategy> validators;

    public ParameterValidator() {
        this.validators = new ArrayList<>();
    }

    public ParameterValidator custom(IParameterValidatorStrategy strategy) {
        this.validators.add(strategy);
        return this;
    }

    public ParameterValidator required() {
        return this.custom(new RequiredParameterValidatorStrategy());
    }

    public ParameterValidator regex(String pattern) {
        return this.custom(new RegexPatternParameterValidatorStrategy(pattern));
    }

    public IParameterValidatorStrategy validate(String parameter) {
        for (IParameterValidatorStrategy validatorStrategy : this.validators) {
            if (!validatorStrategy.validate(parameter)) {
                return validatorStrategy;
            }
        }

        return null;
    }
}
