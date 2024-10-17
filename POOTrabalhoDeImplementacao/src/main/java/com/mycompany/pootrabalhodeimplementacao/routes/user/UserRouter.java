/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.user;

import com.mycompany.pootrabalhodeimplementacao.app.Router;

/**
 *
 * @author felipe
 */
public class UserRouter extends Router {

    public UserRouter() {
        super("User");
        this.addRoute(new LoginStatusRoute());
        this.addRoute(new LoginRoute());
        this.addRoute(new LogoutRoute());
        this.addRoute(new SigninRoute());
        this.addRoute(new SignAdminRoute());
        this.addRoute(new ListUserRoute());
        this.addRoute(new DeleteUserRoute());
    }
}
