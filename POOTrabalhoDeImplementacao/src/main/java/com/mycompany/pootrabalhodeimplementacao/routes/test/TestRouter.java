/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.routes.test;

import com.mycompany.pootrabalhodeimplementacao.app.Router;

/**
 *
 * @author felipe
 */
public class TestRouter extends Router {

    public TestRouter() {
        super("Test");
        this.addRoute(new PrintRoute());
    }
}
