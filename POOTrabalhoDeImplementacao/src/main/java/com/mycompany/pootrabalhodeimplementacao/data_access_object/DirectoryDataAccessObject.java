/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author felipe
 */
public class DirectoryDataAccessObject {

    private final Path DIRECTORY_PATH;

    public DirectoryDataAccessObject(Path directoryPath) {
        this.DIRECTORY_PATH = directoryPath;

        if (!Files.exists(this.DIRECTORY_PATH)) {
            try {
                Files.createDirectories(this.DIRECTORY_PATH);
            } catch (IOException e) {
            }
        }
    }

    public Path getDirectoryPath() {
        return this.DIRECTORY_PATH;
    }

    public File getFile(String fileName) {
        return new File(this.DIRECTORY_PATH.toString() + File.separator + fileName);
    }

    public Collection<Path> getFilePaths() {
        File directory = this.DIRECTORY_PATH.toFile();

        Collection<Path> fileNames = new ArrayList<>();
        for (File file : directory.listFiles(File::isFile)) {
            fileNames.add(file.toPath());
        }

        return fileNames;
    }

    public Collection<File> getFiles() {
        File directory = this.DIRECTORY_PATH.toFile();

        return new ArrayList<>(Arrays.asList(directory.listFiles(File::isFile)));
    }

    public DirectoryDataAccessObject getSubDirectory(String directoryName) {
        return new DirectoryDataAccessObject(new File(this.DIRECTORY_PATH.toString() + File.separator + directoryName).toPath());
    }

    public Collection<String> getSubDirectoryNames() {
        File directory = this.DIRECTORY_PATH.toFile();

        Collection<String> directoryNames = new ArrayList<>();
        for (File dir : directory.listFiles(File::isDirectory)) {
            directoryNames.add(dir.getName());
        }

        return directoryNames;
    }

    public Collection<DirectoryDataAccessObject> getSubDirectories() {
        File directory = this.DIRECTORY_PATH.toFile();

        Collection<DirectoryDataAccessObject> directories = new ArrayList<>();
        for (File dir : directory.listFiles(File::isDirectory)) {
            directories.add(new DirectoryDataAccessObject(dir.toPath()));
        }

        return directories;
    }

    public void deleteDirectory() {
        Collection<File> files = this.getFiles();
        Collection<DirectoryDataAccessObject> directories = this.getSubDirectories();

        for (File file : files) {
            file.delete();
        }

        for (DirectoryDataAccessObject directory : directories) {
            directory.deleteDirectory();
        }

        File directory = this.getDirectoryPath().toFile();
        directory.delete();
    }
}
