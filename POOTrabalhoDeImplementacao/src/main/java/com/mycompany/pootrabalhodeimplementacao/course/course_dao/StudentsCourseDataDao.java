/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course.course_dao;

import com.mycompany.pootrabalhodeimplementacao.course.StudentCourseData;
import com.mycompany.pootrabalhodeimplementacao.data_access_object.text_file.TextFileDataAccessObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipe
 */
public class StudentsCourseDataDao extends TextFileDataAccessObject<StudentCourseData> {

    public StudentsCourseDataDao(Path filePath) {
        super(filePath, (String textFileString) -> {
            String[] data = textFileString.split(";", 3);

            int userId = Integer.parseInt(data[0]);
            int score = Integer.parseInt(data[1]);

            return new StudentCourseData(userId, score);
        });
        
        try {
            this.createFileIfNotExists();
        } catch (IOException ex) {
            Logger.getLogger(StudentsCourseDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void writeToFile(StudentCourseData studentCourseData) {
        try (BufferedWriter writer = Files.newBufferedWriter(getFilePath(), StandardOpenOption.APPEND)) {
            writer.write(studentCourseData.getStudentUserId() + ";" + studentCourseData.getScore() + System.lineSeparator());
        } catch (IOException ex) {
            Logger.getLogger(StudentsCourseDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Collection<StudentCourseData> getStudentsCourseData() {
        Collection<StudentCourseData> studentsCourseData = new ArrayList<>();

        for (Iterator<StudentCourseData> it = this.getIterator(); it.hasNext();) {
            studentsCourseData.add(it.next());
        }

        return studentsCourseData;
    }
}
