/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.app.Router;

/**
 *
 * @author carlo
 */
public class CourseRouter extends Router {

    public CourseRouter() {
        super("Course");

        this.addRoute(new ListCoursesRoute());
        this.addRoute(new ListModulesRoute());
        this.addRoute(new ListStudentsRoute());

        this.addRoute(new AddCourseRoute());
        this.addRoute(new AddModuleRoute());
        this.addRoute(new AddQuestionRoute());

        this.addRoute(new DeleteCourseRoute());
        this.addRoute(new DeleteModuleRoute());
        this.addRoute(new DeleteQuestionRoute());

        this.addRoute(new SubToCourseRoute());
        this.addRoute(new TakeModuleRoute());
        this.addRoute(new Revisao());
    }
}
