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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe
 */
public class DeleteQuestionRoute extends Route {

    public DeleteQuestionRoute() {
        super("deletequestion", "(courseName) (moduleName) (questionTitle)", "deletes a question",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new ArrayList<ParameterValidator>() {
                            {
                                add(new ParameterValidator().required());
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
                            String questionTitle = positionalParameters.get(2);

                            courseHandler.deleteQuestion(courseName, moduleName, questionTitle);
                            
                            app.out.println("Question deleted successfully.");

                            return 0;
                        }
                )
        );
    }
}
