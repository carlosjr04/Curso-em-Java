/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author felipe
 * @param <T>
 */
public abstract class RecordsFileDataAccessObject<T> extends FileDataAccessObject {

    public RecordsFileDataAccessObject(Path filePath) {
        super(filePath);
    }

    public RecordsFileDataAccessObject(File file) {
        super(file);
    }

    public abstract int getSize();

    public abstract T getRecord(int index);

    public abstract T getRecord(Predicate<T> predicate);

    public abstract List<T> getRecords(Predicate<T> predicate, int limit, int offset);

    public abstract List<T> getRecords(int limit, int offset);

    public abstract void addRecord(T object) throws IOException;

    public abstract void deleteRecord(int index) throws IOException;

    public abstract void deleteRecord(Predicate<T> predicate);

    public abstract void deleteAllRecords(Predicate<T> predicate);

    public abstract void updateRecord(int index, T object) throws IOException;

    public abstract void updateRecord(Predicate<T> predicate, T object) throws IOException;

    public abstract Iterator<T> getIterator(int index);

    public abstract Iterator<T> getIterator();
}
