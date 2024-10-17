/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.course.Course;
import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.course.StudentCourseData;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.user.User;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author felipe
 */
public class ListStudentsRoute extends Route {

    public ListStudentsRoute() {
        super("liststudents", "(courseName)", "prints the students list for a course",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new ArrayList<ParameterValidator>() {
                            {
                                add(new ParameterValidator().required());
                            }
                        }
                        ),
                        (ICommand) (Route.Parameters parameters) -> {
                            List<String> positionalParameters = parameters.getPositionalParameters();

                            App app = App.getAppInstance();
                            CourseHandler courseHandler = app.getCourseHandler();
                            UserHandler userHandler = app.getUserHandler();

                            String courseName = positionalParameters.get(0);
                            
                            Course course = courseHandler.getCourse(courseName);
                            if (course == null) {
                                app.out.println("Course not found.");
                                return 1;
                            }
                            
                            Collection<StudentCourseData> studentsCourseData = course.getStudentsCourseData();

                            app.out.println("[ STUDENT LIST START ]");
                            for (StudentCourseData studentCourseData : studentsCourseData) {
                                int studentUserId = studentCourseData.getStudentUserId();
                                User studentUser = userHandler.getUserById(studentUserId);

                                app.out.println(studentUser + " - " + "Score: " + studentCourseData.getScore());
                            }
                            app.out.println("[ STUDENT LIST END ]");

                            return 0;
                        }
                )
        );
    }
}
