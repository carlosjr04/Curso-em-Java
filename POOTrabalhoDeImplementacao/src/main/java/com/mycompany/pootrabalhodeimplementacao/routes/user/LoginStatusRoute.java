/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.user;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.user.User;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.util.Arrays;

/**
 *
 * @author felipe
 */
public class LoginStatusRoute extends Route {

    public LoginStatusRoute() {
        super("loginstatus", "prints the current logged user",
                Arrays.asList(
                        (ICommand) (Route.Parameters parameters) -> {
                            App app = App.getAppInstance();
                            UserHandler userHandler = app.getUserHandler();

                            User loggedUser = userHandler.getLoggedUser();
                            if (loggedUser == null) {
                                app.out.println("Nenhum usuario logado atualmente.");
                                return 0;
                            }

                            app.out.print("Usuario logado: ");
                            app.out.println(loggedUser);

                            return 0;
                        }
                )
        );
    }
}
