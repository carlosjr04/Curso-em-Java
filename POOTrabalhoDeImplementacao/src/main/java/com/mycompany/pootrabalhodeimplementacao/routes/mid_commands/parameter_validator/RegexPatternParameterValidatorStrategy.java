package com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author felipe
 */
public class RegexPatternParameterValidatorStrategy implements IParameterValidatorStrategy {

    private final String pattern;

    public RegexPatternParameterValidatorStrategy(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean validate(String parameter) {
        if (parameter != null) {
            return parameter.matches(this.pattern);
        }

        return true;
    }

    @Override
    public String getErrorMessage() {
        return "Expected pattern (" + this.pattern + ").";
    }
}
