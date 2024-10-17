/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.user;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.util.Arrays;

/**
 *
 * @author felipe
 */
public class LogoutRoute extends Route {

    public LogoutRoute() {
        super("logout", "logout the current logged user",
                Arrays.asList(
                        (ICommand) (Route.Parameters parameters) -> {
                            App app = App.getAppInstance();
                            UserHandler userHandler = app.getUserHandler();

                            if (userHandler.getLoggedUser() == null) {
                                app.out.println("Nenhum usuario logado atualmente.");
                                return 0;
                            }

                            userHandler.logout();
                            app.out.println("Logout realizado com sucesso.");

                            return 0;
                        }
                )
        );
    }
}
