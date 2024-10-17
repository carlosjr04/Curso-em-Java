/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.course.course_dao;

import com.mycompany.pootrabalhodeimplementacao.course.Question;
import com.mycompany.pootrabalhodeimplementacao.course.Module;
import com.mycompany.pootrabalhodeimplementacao.data_access_object.RecordsFileDataAccessObject;
import com.mycompany.pootrabalhodeimplementacao.data_access_object.RecordsFileDirectoryDataAccessObject;
import com.mycompany.pootrabalhodeimplementacao.data_access_object.text_file.ITextFileObjectFactory;
import com.mycompany.pootrabalhodeimplementacao.data_access_object.text_file.TextFileDataAccessObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author felipe
 */
public class ModulesDao extends RecordsFileDirectoryDataAccessObject<Question> {

    private static final ITextFileObjectFactory<Question> QUESTION_FACTORY = (String textFileString) -> {
        String[] data = textFileString.split(";", 4);

        List<String> answers = new ArrayList<>(Arrays.asList(data[3].split(";")));

        return new Question(data[0], data[1], answers, Integer.parseInt(data[2]));
    };

    public ModulesDao(Path directoryPath) {
        super(directoryPath, (File file) -> {
            return new TextFileDataAccessObject<>(file, ModulesDao.QUESTION_FACTORY);
        });
    }

    public RecordsFileDataAccessObject<Question> getModuleRecordFile(String moduleName) {
        return this.getRecordFile(moduleName + ".txt");
    }

    public Collection<Module> getModules() {
        Collection<Module> modules = new ArrayList<>();

        for (RecordsFileDataAccessObject<Question> questionDao : this.getRecordsFileDao()) {
            List<Question> questions = questionDao.getRecords(questionDao.getSize(), 0);

            modules.add(new Module(questionDao.getFilePath().getFileName().toString(), questions));
        }

        return modules;
    }

    public void addModule(String moduleName) throws IOException {
        Path path = this.getModuleRecordFile(moduleName).getFilePath();

        TextFileDataAccessObject<Question> questionsRecordFile = new TextFileDataAccessObject<>(path, ModulesDao.QUESTION_FACTORY);
        questionsRecordFile.createFileIfNotExists();
    }

    public void deleteModule(String moduleName) {
        File file = this.getModuleRecordFile(moduleName).getFile();

        file.delete();
    }

    public Collection<Question> getQuestions(String moduleName) {
        RecordsFileDataAccessObject<Question> recordFile = this.getModuleRecordFile(moduleName);

        return recordFile.getRecords(recordFile.getSize(), 0);
    }

    public void addQuestion(String moduleName, Question question) throws IOException {
        RecordsFileDataAccessObject<Question> recordFile = this.getModuleRecordFile(moduleName);

        recordFile.addRecord(question);
    }

    public void deleteQuestion(String moduleName, String questionTitle) {
        RecordsFileDataAccessObject<Question> recordFile = this.getModuleRecordFile(moduleName);

        recordFile.deleteRecord((Question question) -> question.getTitle().compareTo(questionTitle) == 0);
    }
}
