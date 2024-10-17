/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author felipe
 * @param <T>
 */
public class RecordsFileDirectoryDataAccessObject<T> extends DirectoryDataAccessObject {

    private final IRecordsFileDataAccessObjectFactory<T> FACTORY;

    public RecordsFileDirectoryDataAccessObject(Path directoryPath, IRecordsFileDataAccessObjectFactory<T> factory) {
        super(directoryPath);
        this.FACTORY = factory;
    }

    public RecordsFileDataAccessObject<T> getRecordFile(String fileName) {
        File file = this.getFile(fileName);

        return this.FACTORY.create(file);
    }

    public Collection<RecordsFileDataAccessObject<T>> getRecordsFileDao() {
        Collection<RecordsFileDataAccessObject<T>> rfdaos = new ArrayList<>();

        for (File file : this.getFiles()) {
            rfdaos.add(this.FACTORY.create(file));
        }

        return rfdaos;
    }
}
