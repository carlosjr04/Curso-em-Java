/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.test;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * @author felipe
 */
public class PrintRoute extends Route {

    public PrintRoute() {
        super("print", "(args)* <kwargs>*", "prints the given parameters",
                Arrays.asList(
                        (ICommand) (Parameters parameters) -> {
                            App app = App.getAppInstance();

                            app.out.print("ARUMENTOS POSITIONAIS: ");
                            for (String s : parameters.getPositionalParameters()) {
                                app.out.print(s + "; ");
                            }
                            app.out.println();

                            app.out.print("ARUMENTOS CHAVE-VALOR: ");
                            for (Map.Entry<String, String> entry : parameters.getKeyValueParameters().entrySet()) {
                                app.out.print(entry.getKey() + "=" + entry.getValue() + "; ");
                            }
                            app.out.println();

                            return 0;
                        }
                )
        );
    }
}
