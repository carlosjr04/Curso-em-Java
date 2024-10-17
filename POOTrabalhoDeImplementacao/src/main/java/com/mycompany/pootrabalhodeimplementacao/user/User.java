/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.user;

import com.mycompany.pootrabalhodeimplementacao.course.Question;
import com.mycompany.pootrabalhodeimplementacao.data_access_object.binary.IBinaryConvertable;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author felipe
 */
public class User implements IBinaryConvertable {

    private final int id;
    private final String name;
    private final String login;
    private final String password;
    private final int accessLevel;
    private final Map<Question, Integer> questionScores;

    public User(int id, String name, String login, String password, int accessLevel) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
        this.questionScores = new HashMap<>();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public int getAccessLevel() {
        return this.accessLevel;
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password);
    }
    
    public void AcertoUser(Question question) {
    
    Integer pontosAtuais = this.questionScores.get(question);
    
    
    if (pontosAtuais == null) {
        pontosAtuais = 0;
    }
    
    
    pontosAtuais -= 1;
    
    
    this.questionScores.put(question, pontosAtuais);
    
    
}
    public void Erro(Question question) {
    Integer pontosAtuais = this.questionScores.get(question);

    if (pontosAtuais == null) {
        pontosAtuais = 0;
    }
    pontosAtuais += 1;   
    this.questionScores.put(question, pontosAtuais);
        
    
}
    
    public Question getQuestionWithHighestScore() {
        Question questionWithHighestScore = null;
        int highestScore = 0;

        for (Map.Entry<Question, Integer> entry : questionScores.entrySet()) {
            if (entry.getValue() > highestScore) {
                highestScore = entry.getValue();
                questionWithHighestScore = entry.getKey();
            }
        }

        return questionWithHighestScore;
    }

    @Override
    public String toString() {
        return "User("
                + "id=" + this.id + " "
                + "name=" + this.name + " "
                + "login=" + this.login + " "
                + "password=" + this.password + " "
                + "accessLevel=" + this.accessLevel + ")";
    }

    protected static final int ID_SIZE = Integer.BYTES;
    protected static final int NAME_SIZE = 64;
    protected static final int LOGIN_SIZE = 32;
    protected static final int PASSWORD_SIZE = 32;
    protected static final int ACCESS_LEVEL_SIZE = Integer.BYTES;
    protected static final long RECORD_SIZE = ID_SIZE + NAME_SIZE + LOGIN_SIZE + PASSWORD_SIZE + ACCESS_LEVEL_SIZE;

    @Override
    public long getRecordSize() {
        return User.RECORD_SIZE;
    }

    @Override
    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bos);
            out.writeInt(this.getId());
            out.writeBytes(String.format("%-" + User.NAME_SIZE + "s", this.getName()).substring(0, User.NAME_SIZE));
            out.writeBytes(String.format("%-" + User.LOGIN_SIZE + "s", this.getLogin()).substring(0, User.LOGIN_SIZE));
            out.writeBytes(String.format("%-" + User.PASSWORD_SIZE + "s", this.getPassword()).substring(0, User.PASSWORD_SIZE));
            out.writeInt(this.getAccessLevel());
            out.flush();

            return bos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
