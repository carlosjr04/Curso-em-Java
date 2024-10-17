/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator;

/**
 *
 * @author felipe
 */
public class RequiredParameterValidatorStrategy implements IParameterValidatorStrategy {

    @Override
    public boolean validate(String parameter) {
        return (parameter != null);
    }

    @Override
    public String getErrorMessage() {
        return "Expected not null value.";
    }
}
