/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.app.interal_routes;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import java.util.Arrays;

/**
 *
 * @author felipe
 */
public class ExitRoute extends Route {
    public ExitRoute() {
        super("close", "closes the application",
            Arrays.asList(
                (ICommand) (Route.Parameters parameters) -> {
                    App app = App.getAppInstance();
                    
                    app.exit();

                    return 0;
                }
            )
        );
    }
}
