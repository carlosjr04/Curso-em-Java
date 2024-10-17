/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator;

/**
 *
 * @author felipe
 */
public interface IParameterValidatorStrategy {
    public abstract boolean validate(String parameter);
    public abstract String getErrorMessage();
}
