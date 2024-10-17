package com.mycompany.pootrabalhodeimplementacao.routes.user;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author felipe
 */
public class SigninRoute extends Route {

    public SigninRoute() {
        super("signin", "<name> <login> <password>", "creates a new user given its name, login and password",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new HashMap<String, ParameterValidator>() {
                            {
                                put("name", new ParameterValidator().required());
                                put("login", new ParameterValidator().required());
                                
                                put("password", new ParameterValidator().required());
                            }
                        }
                        ),
                        (ICommand) (Parameters parameters) -> {
                            App app = App.getAppInstance();
                            UserHandler userHandler = app.getUserHandler();
                            Map<String, String> keyParameters = parameters.getKeyValueParameters();
                            try {
                                userHandler.signin(
                                        keyParameters.get("name"),
                                        keyParameters.get("login"),
                                        keyParameters.get("password"),
                                        0
                                );
                                

                                app.out.println("User signed successfully.");
                            } catch (UserHandler.UserHandlerException.UniquePropertyValidation e) {
                                app.out.println("ERROR: Login already in the database.");
                            }

                            return 0;
                        }
                )
        );
    }
}
