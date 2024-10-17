/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.course;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.app.ICommand;
import com.mycompany.pootrabalhodeimplementacao.app.Route;
import com.mycompany.pootrabalhodeimplementacao.course.Course;
import com.mycompany.pootrabalhodeimplementacao.course.Module;
import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.course.Question;
import com.mycompany.pootrabalhodeimplementacao.course.StudentCourseData;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.logged_user_validator.LoggedUserValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidator;
import com.mycompany.pootrabalhodeimplementacao.routes.mid_commands.parameter_validator.ParameterValidatorCommand;
import com.mycompany.pootrabalhodeimplementacao.user.User;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author carlo
 */
public class Revisao extends Route {

    public Revisao() {
        super("revisao", "(courseName) (moduleName)", "take a list of revison from a module",
                Arrays.asList(
                        new ParameterValidatorCommand(
                            new ArrayList<ParameterValidator>() {
                            {
                                add(new ParameterValidator().required());
                                add(new ParameterValidator().required());
                            }
                        }
                        ),
                        new LoggedUserValidator(),
                        (ICommand) (Route.Parameters parameters) -> {
                            App app = App.getAppInstance();
                            CourseHandler courseHandler = app.getCourseHandler();
                            UserHandler userhandler = app.getUserHandler();
                            

                            List<String> positionalParameters = parameters.getPositionalParameters();
                            String courseName = positionalParameters.get(0);
                            String moduleName = positionalParameters.get(1);

                            Course course = courseHandler.getCourse(courseName);
                            
                            if (course == null) {
                                app.out.println("ERROR: Course not found.");
                                return 1;
                            }
                            
                            
                            
                            
                            
                            Question question = userhandler.getLoggedUser().getQuestionWithHighestScore();
                            if (question == null) {
                                app.out.println("ERROR: no question to revision.");
                                return 1;
                            }
                            app.out.println("Module " + moduleName + " starting...");

                            Scanner scanner = new Scanner(app.in);
                            
                                app.out.println("[ " + question.getTitle() + " ]");
                                app.out.println(question.getDescription());

                                List<String> answers = question.getAnswers();
                                for (int i = 0; i < answers.size(); i++) {
                                    app.out.println("[" + i + "]" + " - " + answers.get(i));
                                }

                                app.out.print("Answer: ");
                                int answer = scanner.nextInt();

                                if (answer == question.getCorrectAnswer()) {
                                     userhandler.getLoggedUser().AcertoUser(question);

                                    
                                    app.out.println("Correct answer!");
                                } else {
                                    app.out.println("Incorrect answer.");
                                }
                            

                            app.out.println("End of module " + moduleName + ".");

                            return 0;
                        }
                )
        );
    }
}