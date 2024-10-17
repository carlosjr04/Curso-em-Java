/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course;

import com.mycompany.pootrabalhodeimplementacao.user.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author carlo
 */
public class Course {
    private final String name;
    private final Map<String, Module> modulesMap;
    private final Map<Integer, StudentCourseData> studentsCourseDataMap;

    public Course(String name, Collection<? extends Module> modules, Collection<? extends StudentCourseData> studentsCourseData) {
        this.name = name;
        this.modulesMap = new HashMap<>() {{
            for (Module module : modules)
                put(module.getName(), module);
        }};
        
        this.studentsCourseDataMap = new HashMap<>() {{
            for (StudentCourseData studentCourseData : studentsCourseData)
                put(studentCourseData.getStudentUserId(), studentCourseData);
        }};
    }
    
    public String getName() {
        return this.name;
    }
    
    public Map<String, Module> getModulesMap() {
        return modulesMap;
    }
    
    public Collection<Module> getModules() {
        return modulesMap.values();
    }
    
    public Module getModule(String moduleName) {
        return this.modulesMap.get(moduleName);
    }
    
    public Map<Integer, StudentCourseData> getStudentsCourseDataMap() {
        return this.studentsCourseDataMap;
    }
    
    
    public Collection<StudentCourseData> getStudentsCourseData() {
        return this.studentsCourseDataMap.values();
    }
    
    public StudentCourseData getStudentByUser(User user){
        for (StudentCourseData studentCourseData : this.studentsCourseDataMap.values()) {
            if (user.getId()==studentCourseData.getStudentUserId()){
                return studentCourseData;
            }
        }
        return null;
    }
    
    
    @Override
    public String toString() {
        return "Course (" + this.name + ")";
    }
}
