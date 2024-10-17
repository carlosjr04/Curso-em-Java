/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.user;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.logged_user_validator.AccessLevelValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * @author felipe
 */
public class DeleteUserRoute extends Route {

    public DeleteUserRoute() {
        super("userdelete", "<id?> <login?>", "deletes an user by its id or login",
                Arrays.asList(
                        new AccessLevelValidatorCommand(1),
                        (ICommand) (Route.Parameters parameters) -> {
                            App app = App.getAppInstance();
                            UserHandler userHandler = app.getUserHandler();

                            Map<String, String> keyParameters = parameters.getKeyValueParameters();

                            String id = keyParameters.get("id"), login = keyParameters.get("login");
                            if (id != null) {
                                userHandler.delete(Integer.parseInt(id));
                                
                                app.out.println("Usuario deletado com sucesso.");
                                return 0;
                            }

                            if (login != null) {
                                userHandler.delete(login);
                                app.out.println("Usuario deletado com sucesso.");
                                return 0;
                            }

                            app.out.println("Parametro 'id' e 'login' encontrados com valor null.");
                            return 0;
                        }
                )
        );
    }
}
