/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course.course_dao;

import com.mycompany.pootrabalhodeimplementacao.course.Course;
import com.mycompany.pootrabalhodeimplementacao.data_access_object.DirectoryDataAccessObject;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author felipe
 */
public class CourseDao extends DirectoryDataAccessObject {

    private static final String STUDENTS_DATA_FILE_NAME = "studentsData.txt";
    private static final String MODULES_DIRECTORY_NAME = "modules";

    private final String courseName;

    private final StudentsCourseDataDao studentsCourseDataDao;
    private final ModulesDao modulesDao;

    public CourseDao(Path directoryPath, String courseName) {
        super(Paths.get(directoryPath.toAbsolutePath().toString(), courseName));

        this.courseName = courseName;

        Path absolutePath = this.getDirectoryPath().toAbsolutePath();
        Path studentsDataFilePath = Paths.get(absolutePath.toString(), CourseDao.STUDENTS_DATA_FILE_NAME);
        Path modulesDirPath = Paths.get(absolutePath.toString(), CourseDao.MODULES_DIRECTORY_NAME);

        this.studentsCourseDataDao = new StudentsCourseDataDao(studentsDataFilePath);
        this.modulesDao = new ModulesDao(modulesDirPath);
    }

    public String getCourseName() {
        return this.courseName;
    }

    public StudentsCourseDataDao getStudentsCourseDataDao() {
        return this.studentsCourseDataDao;
    }

    public ModulesDao getModulesDao() {
        return this.modulesDao;
    }

    public Course getCourse() {
        return new Course(
                this.courseName,
                this.modulesDao.getModules(),
                this.studentsCourseDataDao.getStudentsCourseData()
        );
    }
}
