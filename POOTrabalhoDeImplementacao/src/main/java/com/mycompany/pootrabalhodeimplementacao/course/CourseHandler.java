/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course;

import com.mycompany.pootrabalhodeimplementacao.course.course_dao.CourseDao;
import com.mycompany.pootrabalhodeimplementacao.course.course_dao.CoursesDirectoryDao;
import com.mycompany.pootrabalhodeimplementacao.course.course_dao.ModulesDao;
import com.mycompany.pootrabalhodeimplementacao.course.course_dao.StudentsCourseDataDao;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class CourseHandler {

    public class CourseNotFoundException extends RuntimeException {
        public CourseNotFoundException() {
            super("Course directory not found");
        }
    }
    
    private final CoursesDirectoryDao coursesDirectoryDao;
    private final Path COURSES_DIRECTORY_PATH = Paths.get("courses");

    public CourseHandler() {
        this.coursesDirectoryDao = new CoursesDirectoryDao(this.COURSES_DIRECTORY_PATH);
    }
    
    public CourseDao getCourseDao(String courseName) throws CourseNotFoundException {
        CourseDao courseDao = this.coursesDirectoryDao.getCourseDao(courseName);
        if (courseDao == null) throw new CourseNotFoundException();
        
        return courseDao;
    }

    public Collection<Course> getCourses() {
        return this.coursesDirectoryDao.getCourses();
    }
        
    public Course getCourse(String courseName) {
        try {
            CourseDao courseDao = this.getCourseDao(courseName);
            return courseDao.getCourse();
        } catch (CourseNotFoundException e) {
            return null;
        }
    }
    
    public void addCourse(String courseName) throws IOException {
        this.coursesDirectoryDao.addCourse(courseName);
    }

    public void deleteCourse(String courseName) {
        this.coursesDirectoryDao.deleteCourse(courseName);
    }

    public void addModule(String courseName, String moduleName) throws IOException, CourseNotFoundException {
        CourseDao courseDao = this.getCourseDao(courseName);

        ModulesDao modulesDao = courseDao.getModulesDao();
        modulesDao.addModule(moduleName);
    }

    public void deleteModule(String courseName, String moduleName) throws CourseNotFoundException {
        CourseDao courseDao = this.getCourseDao(courseName);

        ModulesDao modulesDao = courseDao.getModulesDao();
        modulesDao.deleteModule(moduleName);
    }

    public void addQuestion(String courseName, String moduleName, Question question) throws IOException {
        this.coursesDirectoryDao.addQuestion(courseName, moduleName, question);
    }

    public void deleteQuestion(String courseName, String moduleName, String questionTitle) {
        this.coursesDirectoryDao.deleteQuestion(courseName, moduleName, questionTitle);
    }

    public void addStudent(int studentUserId, String courseName) throws IOException, CourseNotFoundException {
        CourseDao courseDao = this.getCourseDao(courseName);
        
        StudentsCourseDataDao studentsCourseDataDao = courseDao.getStudentsCourseDataDao();
        studentsCourseDataDao.addRecord(new StudentCourseData(studentUserId));
    }
}
