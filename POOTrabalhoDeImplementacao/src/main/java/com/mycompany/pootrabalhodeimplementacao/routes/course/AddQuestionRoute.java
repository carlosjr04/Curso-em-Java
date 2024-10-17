/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.course.Question;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.logged_user_validator.AccessLevelValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author felipe
 */
public class AddQuestionRoute extends Route {

    public AddQuestionRoute() {
        super("addquestion", "(courseName) (moduleName) <title> <description> <correctAnswerIndex> (answers)*", "adds a question to the module of a course",
                Arrays.asList(
                        new ParameterValidatorCommand(
                                new ArrayList<ParameterValidator>() {
                            {
                                add(new ParameterValidator().required());
                                add(new ParameterValidator().required());
                            }
                        },
                                new HashMap<String, ParameterValidator>() {
                            {
                                put("title", new ParameterValidator().required());
                                put("description", new ParameterValidator().required());
                                put("correctAnswerIndex", new ParameterValidator().required());
                            }
                        }
                        ),
                        new AccessLevelValidatorCommand(1),
                        (ICommand) (Route.Parameters parameters) -> {
                            List<String> positionalParameters = parameters.getPositionalParameters();
                            Map<String, String> keyValueParameters = parameters.getKeyValueParameters();

                            App app = App.getAppInstance();
                            CourseHandler courseHandler = app.getCourseHandler();

                            String courseName = positionalParameters.get(0);
                            String moduleName = positionalParameters.get(1);
                            String title = keyValueParameters.get("title");
                            String description = keyValueParameters.get("description");
                            String correctAnswerIndex = keyValueParameters.get("correctAnswerIndex");
                            List<String> answers = positionalParameters.subList(2, positionalParameters.size());

                            Question question = new Question(title, description, answers, Integer.parseInt(correctAnswerIndex));

                            try {
                                courseHandler.addQuestion(courseName, moduleName, question);
                            } catch (IOException e) {
                                app.out.println("ERROR: could not create question to module " + moduleName + ".");
                                return 1;
                            }
                            
                            app.out.println("Created question successfully.");

                            return 0;
                        }
                )
        );
    }
}
