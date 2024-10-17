/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.logged_user_validator.LoggedUserValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author felipe
 */
public class SubToCourseRoute extends Route {

    public SubToCourseRoute() {
        super("subtocourse", "(courseName)", "subscribe to a course",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new ArrayList<ParameterValidator>() {
                            {
                                add(new ParameterValidator().required());
                            }
                        }
                        ),
                        new LoggedUserValidator(),
                        (ICommand) (Route.Parameters parameters) -> {
                            List<String> positionalParameters = parameters.getPositionalParameters();

                            App app = App.getAppInstance();
                            CourseHandler courseHandler = app.getCourseHandler();
                            UserHandler userHandler = app.getUserHandler();

                            String courseName = positionalParameters.get(0);

                            try {
                                courseHandler.addStudent(userHandler.getLoggedUser().getId(), courseName);
                            } catch (CourseHandler.CourseNotFoundException e) {
                                app.out.println("ERROR: Course not found.");
                                return 1;
                            } catch (IOException e) {
                                app.out.println("ERROR: could not subscribe to this course.");
                                return 1;
                            }

                            app.out.println("Successfully subscribed successfully.");

                            return 0;
                        }
                )
        );
    }
}
