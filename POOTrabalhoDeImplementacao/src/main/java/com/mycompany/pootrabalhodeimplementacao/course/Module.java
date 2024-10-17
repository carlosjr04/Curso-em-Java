/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author carlo
 */
public class Module {

    private final String name;
    List<Question> questions;
    private final Random random = new Random();

    public Module(String name, List<? extends Question> questions) {
        this.name = name;
        this.questions = new ArrayList<>(questions);
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<? extends Question> questions) {
        this.questions = new ArrayList<>(questions);
    }

    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }

        int randomIndex = random.nextInt(questions.size());
        return questions.get(randomIndex);
    }

    @Override
    public String toString() {
        return "Module{" + "name=" + name + ", questions=" + questions + '}';
    }
}
