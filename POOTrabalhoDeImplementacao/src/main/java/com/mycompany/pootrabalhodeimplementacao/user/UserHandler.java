/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.user;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


/**
 *
 * @author felipe
 */
public class UserHandler {

    public static class UserHandlerException extends Exception {

        public static class UniquePropertyValidation extends UserHandlerException {

            public UniquePropertyValidation(String propertyName) {
                super("Property (" + propertyName + ") already found in the database.");
            }
        }

        public static class LoginInvalidCredentials extends UserHandlerException {

            public LoginInvalidCredentials() {
                super("Login invalid credentials");
            }
        }

        public UserHandlerException(String message) {
            super(message);
        }
    }
    
    private final UserBinaryDAO userDao;
   
    private static final Path USERS_FILE_PATH = Paths.get("users.bin");

    private User loggedUser;

    public UserHandler() {
        this.userDao = new UserBinaryDAO(UserHandler.USERS_FILE_PATH);
        this.initializeUserDao();

        this.loggedUser = null;
    }

    private void initializeUserDao() {
        try {
            this.userDao.createFileIfNotExists();
        } catch (IOException e) {
        }
        
        if (this.userDao.getRecord((User user) -> user.getAccessLevel() == 1) == null) {
            try {
                this.signin("admin", "admin@email.com", "1234", 1);
            } catch (UserHandlerException.UniquePropertyValidation e) {
            }
        }
    }

    public User getUserById(int id) {
        return this.userDao.getRecord((User t) -> t.getId() == id);
    }

    public User getUserByLogin(String login) {
        return this.userDao.getRecord((User t) -> t.getLogin().compareTo(login) == 0);
    }
    
    public int getValidId(){
        
        int saiu=0;
        int n=1;
        while(saiu==0){
            User usuario= getUserById(n);
            if (usuario==null){
                return n;
                
            }
            n++;
        }
        return n;
       
    }
    
    

    public Collection<User> getUsers(int limit, int offset) {
        return this.userDao.getRecords(limit, offset);
    }

    public Iterator<User> usersIterator() {
        return this.userDao.getIterator();
    }

    public int getNextId() {
        return this.userDao.getNextId();
    }

    public void delete(User user) {
        if (this.getLoggedUser() == user) {
            this.logout();
        }

        this.userDao.deleteRecord((User t) -> t.getId() == user.getId());
    }

    public void delete(int id) {
        this.delete(this.getUserById(id));
    }

    public void delete(String login) {
        this.delete(this.getUserByLogin(login));
    }

    public void signin(String name, String login, String password, int accessLevel) throws UserHandlerException.UniquePropertyValidation {
        if (!(this.getUserByLogin(login) == null)) {
            throw new UserHandlerException.UniquePropertyValidation("login");
        }

        try {
            this.userDao.addRecord(new User(getValidId(), name, login, password, accessLevel));
            
        } catch (IOException e) {
        }
    }

    public User login(String login, String password) throws UserHandlerException.LoginInvalidCredentials {
        User u = this.getUserByLogin(login);

        if (u != null && u.comparePassword(password)) {
            this.loggedUser = u;
            return u;
        }

        throw new UserHandlerException.LoginInvalidCredentials();
    }

    public void logout() {
        this.loggedUser = null;
    }

    public User getLoggedUser() {
        return this.loggedUser;
    }
}
