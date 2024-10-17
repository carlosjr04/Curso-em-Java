/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.logged_user_validator.AccessLevelValidatorCommand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author carlo
 */
public class AddCourseRoute extends Route {

    public AddCourseRoute() {
        super("addcourse", "(name)", "cria um curso",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new ArrayList<ParameterValidator>() {
                            {
                                add(new ParameterValidator().required());
                            }
                        }
                        ),
                        new AccessLevelValidatorCommand(1),
                        (ICommand) (Parameters parameters) -> {
                            App app = App.getAppInstance();

                            CourseHandler courseHandler = app.getCourseHandler();

                            List<String> positionalParameters = parameters.getPositionalParameters();

                            String courseName = positionalParameters.get(0);

                            try {
                                courseHandler.addCourse(courseName);
                            } catch (IOException e) {
                                app.out.println("ERRROR: Failed to create a new course.");
                                return 1;
                            }

                            app.out.println("Course created successfully.");

                            return 0;
                        }
                )
        );
    }
}
