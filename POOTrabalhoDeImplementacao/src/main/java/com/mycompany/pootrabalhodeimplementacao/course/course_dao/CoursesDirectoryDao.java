/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course.course_dao;

import com.mycompany.pootrabalhodeimplementacao.course.Course;
import com.mycompany.pootrabalhodeimplementacao.course.Question;
import com.mycompany.pootrabalhodeimplementacao.data_access_object.DirectoryDataAccessObject;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author felipe
 */
public class CoursesDirectoryDao extends DirectoryDataAccessObject {

    private final Collection<CourseDao> coursesDao;

    public CoursesDirectoryDao(Path coursesDirectoryPath) {
        super(coursesDirectoryPath);

        this.coursesDao = new ArrayList<>();
        for (String courseDirectoryName : this.getSubDirectoryNames()) {
            this.coursesDao.add(new CourseDao(this.getDirectoryPath(), courseDirectoryName));
        }
    }

    public CourseDao getCourseDao(String courseName) {
        for (CourseDao courseDao : this.coursesDao) {
            if (courseDao.getCourseName().equals(courseName)) {
                return courseDao;
            }
        }

        return null;
    }

    public Collection<Course> getCourses() {
        Collection<Course> courses = new ArrayList<>();

        for (CourseDao courseDao : this.coursesDao) {
            courses.add(courseDao.getCourse());
        }

        return courses;
    }

    public void addCourse(String courseName) throws IOException {
        this.coursesDao.add(new CourseDao(this.getDirectoryPath(), courseName));
    }

    public void deleteCourse(String courseName) {
        CourseDao courseDao = this.getCourseDao(courseName);

        courseDao.deleteDirectory();
    }

    public void addQuestion(String courseName, String moduleName, Question question) throws IOException {
        CourseDao courseDao = this.getCourseDao(courseName);
        ModulesDao modulesDao = courseDao.getModulesDao();

        modulesDao.addQuestion(moduleName, question);
    }

    public void deleteQuestion(String courseName, String moduleName, String questionTitle) {
        CourseDao courseDao = this.getCourseDao(courseName);
        ModulesDao modulesDao = courseDao.getModulesDao();

        modulesDao.deleteQuestion(moduleName, questionTitle);
    }
}
