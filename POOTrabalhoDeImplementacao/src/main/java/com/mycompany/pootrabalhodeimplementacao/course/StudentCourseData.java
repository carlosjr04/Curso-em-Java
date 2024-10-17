/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course;

import com.mycompany.pootrabalhodeimplementacao.data_access_object.text_file.ITextFileConvertable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author carlo
 */
public class StudentCourseData implements ITextFileConvertable {

    private final int studentUserId;
    private int score;
    private final Map<Question, Integer> questionScores;
    

    public StudentCourseData(int studentUserId, int score) {
        this.studentUserId = studentUserId;
        this.score = score;
        this.questionScores = new HashMap<>();
        
    }

    public StudentCourseData(int studentUserId) {
        this(studentUserId, 0);
    }

    public int getStudentUserId() {
        return studentUserId;
    }

    

    public int getScore() {
        return this.score;
    }

    public Map<Question, Integer> getQuestionScores() {
        return this.questionScores;
    }
    public void setScore() {
        int pontos = 5;
        this.score +=pontos;
    }
    
    
    @Override
    public String toTextFileString() {
        String s = this.studentUserId + ";" + getScore() + ";";

        for (Entry<Question, Integer> entry : questionScores.entrySet()) {
            s += entry.getKey().getTitle() + "-" + entry.getValue() + ";";
        }

        return s;
    }
}
