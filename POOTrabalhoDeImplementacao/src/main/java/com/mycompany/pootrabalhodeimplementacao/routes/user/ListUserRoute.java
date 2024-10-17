package com.mycompany.pootrabalhodeimplementacao.routes.user;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.user.User;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.util.Arrays;
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author felipe
 */
public class ListUserRoute extends Route {

    public ListUserRoute() {
        super("listuser", "prints the user list",
                Arrays.asList(
                        (ICommand) (Parameters parameters) -> {
                            App app = App.getAppInstance();
                            UserHandler userHandler = app.getUserHandler();

                            app.out.println("[ USER LIST START ]");
                            Iterator<User> it = userHandler.usersIterator();

                            while (it.hasNext()) {
                                app.out.println(it.next());
                            }
                            app.out.println("[ USER LIST END ]");

                            return 0;
                        }
                )
        );
    }
}
