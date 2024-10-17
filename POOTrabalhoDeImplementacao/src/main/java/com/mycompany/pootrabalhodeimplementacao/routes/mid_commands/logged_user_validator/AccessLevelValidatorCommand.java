/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.logged_user_validator;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.user.User;

/**
 *
 * @author felipe
 */
public class AccessLevelValidatorCommand extends LoggedUserValidator {

    private final int minimumAccessLevel;

    public AccessLevelValidatorCommand(int minimumAccessLevel) {
        this.minimumAccessLevel = minimumAccessLevel;
    }

    @Override
    public int run(Route.Parameters parameters) {
        int loggedUserValidatorReturn = super.run(parameters);
        if (loggedUserValidatorReturn != 0) {
            return loggedUserValidatorReturn;
        }

        App app = App.getAppInstance();
        User loggedUser = app.getUserHandler().getLoggedUser();

        if (loggedUser.getAccessLevel() < this.minimumAccessLevel) {
            app.out.println("ERROR: You do not have permission to do this.");
            return 1;
        }

        return 0;
    }
}
