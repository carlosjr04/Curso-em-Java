package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.course.Course;
import com.mycompany.pootrabalhodeimplementacao.course.Module;
import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author felipe
 */
public class ListModulesRoute extends Route {

    public ListModulesRoute() {
        super("listmodules", "(courseName)", "prints the module list of a course",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new ArrayList<ParameterValidator>() {
                            {
                                add(new ParameterValidator().required());
                            }
                        }
                        ),
                        (ICommand) (Route.Parameters parameters) -> {
                            App app = App.getAppInstance();
                            CourseHandler courseHandler = app.getCourseHandler();

                            List<String> positionalParameters = parameters.getPositionalParameters();

                            String courseName = positionalParameters.get(0);

                            Course course = courseHandler.getCourse(courseName);
                            if (course == null) {
                                app.out.println("ERROR: Course not found.");
                                return 1;
                            }
                            
                            app.out.println("[ MODULES LIST START ]");
                            for (Module module : course.getModules()) {
                                app.out.println(module);
                            }
                            app.out.println("[ MODULES LIST END ]");

                            return 0;
                        }
                )
        );
    }
}
