/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 *
 * @author felipe
 */
public abstract class FileDataAccessObject {

    private final Path FILE_PATH;

    public FileDataAccessObject(Path filePath) {
        this.FILE_PATH = filePath;
    }

    public FileDataAccessObject(File file) {
        this(file.toPath());
    }

    public Path getFilePath() {
        return this.FILE_PATH;
    }

    public File getFile() {
        return this.FILE_PATH.toFile();
    }

    public long getFileLength() {
        File file = this.FILE_PATH.toFile();
        return (long) file.length();
    }

    public void createFileIfNotExists() throws IOException {
        File file = this.FILE_PATH.toFile();
        if (!file.exists()) file.createNewFile();
    }
}
