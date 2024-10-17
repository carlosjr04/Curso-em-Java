/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course;

import com.mycompany.pootrabalhodeimplementacao.data_access_object.text_file.ITextFileConvertable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author carlo
 */
public class Question implements ITextFileConvertable {

    private final String title;
    private final String description;
    private final List<String> answers;
    private final int correctAnswer;

    public Question(String title, String description, List<String> answers, int correctAnswer) {
        this.title = title;
        this.description = description;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" + "title=" + title + "\n description=" + description + "\n answers=" + answers + '\n';
    }

    public String toStringQuestao() {
        String formattedDescription = description.replace('_', ' ');
        StringBuilder result = new StringBuilder("Description: " + formattedDescription + "\n");
        for (int i = 0; i < answers.size(); i++) {
            String formattedAnswer = answers.get(i).replace('_', ' ');
            result.append("Option ").append(i + 1).append(": ").append(formattedAnswer).append("\n");
        }
        return result.toString();
    }

    public boolean VerifyAnswer(int resposta) {
        return resposta == this.correctAnswer;

    }

    public static Question fromText(String text) {
        String[] parts = text.split(";");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid text format for Question");
        }

        String title = parts[0];
        String description = parts[1];
        int correctAnswer = Integer.parseInt(parts[2]);
        List<String> answers = new ArrayList<>(Arrays.asList(parts[3].split(",")));

        return new Question(title, description, answers, correctAnswer);
    }

    @Override
    public String toTextFileString() {
        String s = this.title + ";" + this.description + ";" + this.correctAnswer;

        for (String answer : this.answers) {
            s += ";" + answer;
        }
        return s;
    }
}
