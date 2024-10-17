/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author felipe
 */
public class Route {
    public class Parameters {
        private final List<String> positionalParameters;
        private final Map<String, String> keyValueParameters;

        public Parameters(String line) {
            this.keyValueParameters = new HashMap<>();
            this.positionalParameters = new ArrayList<>();

            String[] sline = line.split(" ", 2);
            String parametersString = sline.length >= 2 ? sline[1] : "";

            Pattern keyValuePattern = Pattern.compile("(\\w+)=('([^']*)'|\"([^\"]*)\"|(\\S+))");
            Matcher keyValueMatcher = keyValuePattern.matcher(parametersString);
            while (keyValueMatcher.find()) {
                String value = keyValueMatcher.group(3) != null ? keyValueMatcher.group(3) : keyValueMatcher.group(4) != null ? keyValueMatcher.group(4) : keyValueMatcher.group(5);
                keyValueParameters.put(keyValueMatcher.group(1), value);
                parametersString = parametersString.replace(keyValueMatcher.group(), "");
            }

            Pattern positionalPattern = Pattern.compile("'([^']*)'|\"([^\"]*)\"|(\\S+)");
            Matcher positionalMatcher = positionalPattern.matcher(parametersString);
            while (positionalMatcher.find()) {
                String value = positionalMatcher.group(1) != null ? positionalMatcher.group(1) : positionalMatcher.group(2) != null ? positionalMatcher.group(2) : positionalMatcher.group(3);
                positionalParameters.add(value != null ? value : positionalMatcher.group());
            }
        }
        
        public List<String> getPositionalParameters() { return this.positionalParameters; }
        
        public Map<String, String> getKeyValueParameters() { return this.keyValueParameters; }
    }
    
    private final String name;
    private final String parametersDescription;
    private final String description;
    private final List<ICommand> commands;

    public Route(String name, String parametersDescription, String description, List<ICommand> commands) {
        this.name = name;
        this.parametersDescription = parametersDescription;
        this.description = description;
        this.commands = commands;
    }
    
    public Route(String name, String description, List<ICommand> commands) {
        this(name, "", description, commands);
    }

    public void run(String line) {
        Route.Parameters parameters = new Route.Parameters(line);
        
        for (ICommand command : this.commands)
            if (command.run(parameters) != 0) break;
    }
    
    public String getName() { return this.name; }
    
    public String getParametersDescription() { return this.parametersDescription; }
    
    public String getDescription() { return this.description; }
    
    public String getHelpString() {
        String first = this.name + (!this.getParametersDescription().isBlank() ? " " + this.getParametersDescription() : "");
        String second = this.description + ".";

        return String.format("%-64s %s", first, second);
    }
    
    public List<ICommand> gerCommands() { return this.commands; }
}
