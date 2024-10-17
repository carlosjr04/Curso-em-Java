/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.app;

import com.mycompany.pootrabalhodeimplementacao.app.interal_routes.AppInternalRouter;
import com.mycompany.pootrabalhodeimplementacao.course.CourseHandler;
import com.mycompany.pootrabalhodeimplementacao.user.UserHandler;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author felipe
 */
public class App {
    private static final App INSTANCE = new App();
    
    public final InputStream in = System.in;
    public final PrintStream out = System.out;

    private final Scanner scanner;
    private final Collection<Router> routers;
    
    private final UserHandler userHandler;
    private final CourseHandler courseHandler;

    private boolean running;
    
    private App() {
        this.running = true;
        this.scanner = new Scanner(this.in);
        
        this.routers = new ArrayList<>();
        this.routers.add(new AppInternalRouter());
        
        this.userHandler = new UserHandler();
        this.courseHandler = new CourseHandler();
    }
    
    public static App getAppInstance() { return App.INSTANCE; }
    
    public Collection<Router> getRouters() { return this.routers; }
    
    public void addRouter(Router router) {
        this.routers.add(router);
    }
        
    public void run() {
        String line;
        boolean routeRunReturn;
        while (this.running) {
            this.out.print(">>> ");

            line = this.scanner.nextLine();
            
            routeRunReturn = false;
            for (Router router : this.routers)
                if (router.run(line)) {
                    routeRunReturn = true;
                    break;
                }
            
            if (!routeRunReturn)
                this.out.println("Route not found. Try 'help'.");
        }
    }
    
    public void exit() {
        this.running = false;
    }
    
    public UserHandler getUserHandler() { return this.userHandler; }

  
    public CourseHandler getCourseHandler() {return this.courseHandler; };
    
}
