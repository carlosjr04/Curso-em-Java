/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.app.interal_routes;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.app.Router;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author felipe
 */
public class HelpRoute extends Route {
    public HelpRoute() {
        super("help", "(routeName?)", "list all the routes",
            Arrays.asList(
                (ICommand) (Route.Parameters parameters) -> {
                    App app = App.getAppInstance();
                    
                    List<String> positionalParameters = parameters.getPositionalParameters();
                    if (!positionalParameters.isEmpty()) {
                        String routeName = positionalParameters.get(0);
                        Route route = null;
                        for (Router router : app.getRouters()) {
                            if ((route = router.getRoutes().get(routeName)) != null)
                                break;
                        }

                        if (route == null) app.out.println("Route not found.");
                        else app.out.println(route.getHelpString());

                    } else {
                        app.out.println("routeName (positionalParameters) <key-value parameters>");
                        app.out.println("?: opcional parameter");
                        app.out.println("*: many parameters");
                        
                        for (Router router : app.getRouters()) {
                            app.out.println("[ " + router.getName() + " ]");
                            for (Route route : router.getRoutes().values()) {
                                app.out.println("\t" + route.getHelpString());
                            }
                        }
                    }

                    return 0;
                }
            )
        );
    }
}
