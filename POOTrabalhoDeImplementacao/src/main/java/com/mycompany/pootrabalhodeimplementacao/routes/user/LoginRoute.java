/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.user;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.user.User;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author felipe
 */
public class LoginRoute extends Route {

    public LoginRoute() {
        super("login", "<login> <password>", "login with a new user given its login and password",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new HashMap<String, ParameterValidator>() {
                            {
                                put("login", new ParameterValidator().required());
                                put("password", new ParameterValidator().required());
                            }
                        }
                        ),
                        (ICommand) (Route.Parameters parameters) -> {
                            App app = App.getAppInstance();
                            UserHandler userHandler = app.getUserHandler();

                            Map<String, String> keyParameters = parameters.getKeyValueParameters();

                            if (userHandler.getLoggedUser() != null) {
                                app.out.println("Um usuario ja esta logado atualmente.");
                                return 0;
                            }

                            try {
                                User loggedUser = userHandler.login(
                                        keyParameters.get("login"),
                                        keyParameters.get("password")
                                );

                                app.out.println("Usuario logado com sucesso. Bem vindo, " + loggedUser.getName() + ".");
                            } catch (UserHandler.UserHandlerException.LoginInvalidCredentials e) {
                                app.out.println("ERRO: Credenciais incorretas.");
                            }

                            return 0;
                        }
                )
        );
    }
}
