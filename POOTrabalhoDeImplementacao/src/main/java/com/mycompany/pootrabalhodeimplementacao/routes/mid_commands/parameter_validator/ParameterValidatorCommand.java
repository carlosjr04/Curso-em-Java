/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author felipe
 */
public class ParameterValidatorCommand implements ICommand {

    public List<ParameterValidator> positionalParameterValidators;
    public Map<String, ParameterValidator> keyValueParameterValidators;

    public ParameterValidatorCommand(List<? extends ParameterValidator> positionalParameterValidators, Map<String, ? extends ParameterValidator> keyValueParameterValidators) {
        this.positionalParameterValidators = new ArrayList<>(positionalParameterValidators);
        this.keyValueParameterValidators = new HashMap<>(keyValueParameterValidators);
    }

    public ParameterValidatorCommand(List<? extends ParameterValidator> positionalParameterValidators) {
        this(positionalParameterValidators, new HashMap<>());
    }

    public ParameterValidatorCommand(Map<String, ? extends ParameterValidator> keyValueParameterValidators) {
        this(new ArrayList<>(), keyValueParameterValidators);
    }

    private boolean validatePositionalParameters(PrintStream ps, List<String> positionalParameters) {
        Iterator<ParameterValidator> ppvit = this.positionalParameterValidators.iterator();
        Iterator<String> ppit = positionalParameters.iterator();

        for (int position = 0; ppvit.hasNext(); position++) {
            ParameterValidator ppv = ppvit.next();
            String pp = ppit.hasNext() ? ppit.next() : null;

            IParameterValidatorStrategy caughtValidator = ppv.validate(pp);
            if (caughtValidator != null) {
                ps.println("Positional parameter #" + position + " didn't pass parameter validation. " + caughtValidator.getErrorMessage());
                return false;
            }

            position++;
        }

        return true;
    }

    private boolean validateKeyValueParameters(PrintStream ps, Map<String, String> keyValueParameters) {
        for (Map.Entry<String, ParameterValidator> entry : keyValueParameterValidators.entrySet()) {
            String value = keyValueParameters.get(entry.getKey());

            IParameterValidatorStrategy caughtValidator = entry.getValue().validate(value);
            if (caughtValidator != null) {
                ps.println("KeyValue parameter '" + entry.getKey() + "' didn't pass parameter validation. " + caughtValidator.getErrorMessage());
                return false;
            }
        }

        return true;
    }

    @Override
    public int run(Route.Parameters parameters) {
        App app = App.getAppInstance();

        if (!validatePositionalParameters(app.out, parameters.getPositionalParameters())) {
            return 1;
        }

        if (!validateKeyValueParameters(app.out, parameters.getKeyValueParameters())) {
            return 1;
        }

        return 0;
    }
}
