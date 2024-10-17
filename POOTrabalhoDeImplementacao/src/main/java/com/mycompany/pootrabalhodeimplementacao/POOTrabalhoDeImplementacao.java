/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.pootrabalhodeimplementacao;

import com.mycompany.pootrabalhodeimplementacao.app.App;
import com.mycompany.pootrabalhodeimplementacao.routes.test.TestRouter;
import com.mycompany.pootrabalhodeimplementacao.routes.user.UserRouter;
import com.mycompany.pootrabalhodeimplementacao.routes.course.CourseRouter;

/**
 *
 * @author felipe
 */
public class POOTrabalhoDeImplementacao {

    public static void main(String[] args) {
        App app = App.getAppInstance();

        app.addRouter(new TestRouter());
        app.addRouter(new UserRouter());
        app.addRouter(new CourseRouter());

        app.run();
    }
}
