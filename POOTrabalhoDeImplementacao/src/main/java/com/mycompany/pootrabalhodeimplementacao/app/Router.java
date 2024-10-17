/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.app;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author felipe
 */
public class Router {
    private final String name;
    private final Map<String, Route> routes;
    
    public Router(String name) {
        this.name = name;
        this.routes = new HashMap<>();
    }
    
    public String getName() { return this.name; }
    
    public Map<String, Route> getRoutes() { return this.routes; }
    
    public void addRoute(Route route) {
        this.routes.put(route.getName(), route);
    }
    
    public boolean run(String line) {
        App app = App.getAppInstance();

        String routeName = line.split(" ", 2)[0];
        Route route = this.routes.get(routeName);
        
        if (route == null)
            return false;
        
        route.run(line);
        return true;
    }
}
