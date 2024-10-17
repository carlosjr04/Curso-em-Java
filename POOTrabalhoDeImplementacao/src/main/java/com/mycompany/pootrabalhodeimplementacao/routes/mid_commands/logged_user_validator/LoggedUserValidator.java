/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.logged_user_validator;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.user.User;

/**
 *
 * @author felipe
 */
public class LoggedUserValidator implements ICommand {

    @Override
    public int run(Route.Parameters parameters) {
        App app = App.getAppInstance();

        User loggedUser = app.getUserHandler().getLoggedUser();
        if (loggedUser == null) {
            app.out.println("ERROR: You are not logged.");
            return 1;
        }

        return 0;
    }
}
