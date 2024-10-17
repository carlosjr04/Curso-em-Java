/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.course.Course;
import java.util.Arrays;

/**
 *
 * @author carlo
 */
public class ListCoursesRoute extends Route {

    public ListCoursesRoute() {
        super("listcourses", "prints the courses list",
                Arrays.asList(
                        (ICommand) (Parameters parameters) -> {
                            App app = App.getAppInstance();
                            CourseHandler courseHandler = app.getCourseHandler();

                            app.out.println("[ COURSE LIST START ]");
                            for (Course course : courseHandler.getCourses()) {
                                app.out.println(course);
                            }
                            app.out.println("[ COURSE LIST END ]");

                            return 0;
                        }
                )
        );
    }
}
