/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.logged_user_validator.AccessLevelValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author felipe
 */
public class DeleteModuleRoute extends Route {

    public DeleteModuleRoute() {
        super("deletemodule", "(courseName) (moduleName)", "deletes a module from a course",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new java.util.ArrayList<ParameterValidator>() {
                            {
                                add(new ParameterValidator().required());
                                add(new ParameterValidator().required());
                            }
                        }
                        ),
                        new AccessLevelValidatorCommand(1),
                        (ICommand) (Route.Parameters parameters) -> {
                            List<String> positionalParameters = parameters.getPositionalParameters();

                            App app = App.getAppInstance();
                            CourseHandler courseHandler = app.getCourseHandler();

                            String courseName = positionalParameters.get(0);
                            String moduleName = positionalParameters.get(1);

                            try {
                                courseHandler.deleteModule(courseName, moduleName);
                            } catch (CourseHandler.CourseNotFoundException e) {
                                app.out.println("ERROR: Course not found.");
                            }

                            return 0;
                        }
                )
        );
    }
}
