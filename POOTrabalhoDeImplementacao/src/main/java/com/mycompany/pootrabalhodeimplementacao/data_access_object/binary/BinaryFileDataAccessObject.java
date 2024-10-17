/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object.binary;

import com.mycompany.pootrabalhodeimplementacao.data_access_object.RecordsFileDataAccessObject;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author felipe
 * @param <T>
 */
public class BinaryFileDataAccessObject<T extends IBinaryConvertable> extends RecordsFileDataAccessObject<T> {

    private final long HEADER_SIZE;
    private final long RECORD_SIZE;
    private final IBinaryObjectFactory<T> FACTORY;
    private int size;

    public BinaryFileDataAccessObject(Path filePath, long headerSize, long recordSize, IBinaryObjectFactory<T> factory) {
        super(filePath);
        this.HEADER_SIZE = headerSize;
        this.RECORD_SIZE = recordSize;
        this.FACTORY = factory;

        this.size = 0;
        for (Iterator<T> it = new BinaryFileDAOIterator<>(this); it.hasNext(); it.next()) {
            this.size++;
        }
    }

    public BinaryFileDataAccessObject(File file, long headerSize, long recordSize, IBinaryObjectFactory<T> factory) {
        this(file.toPath(), headerSize, recordSize, factory);
    }

    public final long getHeaderSize() {
        return this.HEADER_SIZE;
    }

    public final long getRecordSize() {
        return this.RECORD_SIZE;
    }

    public IBinaryObjectFactory<T> getFactory() {
        return this.FACTORY;
    }
    
    public long getRecordPosition(int index) {
        return this.HEADER_SIZE + index * this.RECORD_SIZE;
    }

    public byte[] getDefaultHeader() {
        return new byte[(int) this.HEADER_SIZE];
    }

    public byte[] getHeader() {
        try (RandomAccessFile raf = new RandomAccessFile(this.getFile(), "r")) {
            byte[] headerByteArray = new byte[(int) this.HEADER_SIZE];
            raf.seek(0);
            raf.readFully(headerByteArray);
            return headerByteArray;
        } catch (IOException e) {
            return null;
        }
    }

    public void setHeader(byte[] byteArray) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(this.getFile(), "rw")) {
            raf.seek(0);
            raf.write(byteArray, 0, (int) this.HEADER_SIZE);
        }
    }

    @Override
    public void createFileIfNotExists() throws IOException {
        super.createFileIfNotExists();
        this.setHeader(this.getDefaultHeader());
    }
    
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public T getRecord(int index) {
        try (RandomAccessFile raf = new RandomAccessFile(this.getFile(), "r");) {
            byte[] b = new byte[(int) this.RECORD_SIZE];
            raf.seek(this.getRecordPosition(index));
            raf.readFully(b);
            return this.FACTORY.create(b);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public T getRecord(Predicate<T> predicate) {
        Iterator<T> it = this.getIterator();

        while (it.hasNext()) {
            T next = it.next();
            if (predicate.test(next)) {
                return next;
            }
        }

        return null;
    }

    @Override
    public List<T> getRecords(Predicate<T> predicate, int limit, int offset) {
        List<T> records = new ArrayList<>();

        Iterator<T> it = this.getIterator(offset);
        while (it.hasNext() && records.size() < limit) {
            T next = it.next();
            if (predicate.test(next)) {
                records.add(next);
            }
        }

        return records;
    }

    @Override
    public List<T> getRecords(int limit, int offset) {
        return this.getRecords((T t) -> true, limit, offset);
    }

    @Override
    public void addRecord(T object) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(this.getFile(), "rw")) {
            byte[] byteArray = object.toByteArray();
            raf.seek(raf.length());
            raf.write(byteArray);

            this.size++;
        }
    }

    @Override
    public void deleteRecord(int index) throws IOException {
        long recordSize = this.getRecordSize();
        try (RandomAccessFile raf = new RandomAccessFile(this.getFile(), "rw")) {

            if (index != this.getSize() - 1) {
                byte[] last = new byte[(int) recordSize];
                raf.seek(raf.length() - recordSize);
                raf.read(last);
                raf.seek(this.getRecordPosition(index));
                raf.write(last);
            }

            this.size--;
            raf.setLength(Math.max(0, raf.length() - recordSize));
        }
    }

    @Override
    public void deleteRecord(Predicate<T> predicate) {
        Iterator<T> it = this.getIterator();

        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.remove();
                return;
            }
        }
    }

    @Override
    public void deleteAllRecords(Predicate<T> predicate) {
        Iterator<T> it = this.getIterator();

        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.remove();
            }
        }
    }

    @Override
    public void updateRecord(int index, T object) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(this.getFile(), "rw")) {
            byte[] byteArray = object.toByteArray();

            raf.seek(this.getRecordPosition(index));
            raf.write(byteArray);
        }
    }

    @Override
    public void updateRecord(Predicate<T> predicate, T object) throws IOException {
        Iterator<T> it = this.getIterator();
        int index = 0;

        while (it.hasNext()) {
            T next = it.next();
            if (predicate.test(next)) {
                this.updateRecord(index, object);
                return;
            }
            index++;
        }
    }

    @Override
    public Iterator<T> getIterator(int index) {
        return new BinaryFileDAOIterator<>(this, index);
    }

    @Override
    public Iterator<T> getIterator() {
        return this.getIterator(0);
    }

    public static class BinaryFileDAOIterator<S extends IBinaryConvertable> implements Iterator<S> {

        private final BinaryFileDataAccessObject<S> BINARY_FILE_DAO;
        private int index;

        public BinaryFileDAOIterator(BinaryFileDataAccessObject<S> bfdao, int index) {
            this.BINARY_FILE_DAO = bfdao;
            this.index = index;
        }

        public BinaryFileDAOIterator(BinaryFileDataAccessObject<S> bfdao) {
            this(bfdao, 0);
        }

        @Override
        public boolean hasNext() {
            return this.BINARY_FILE_DAO.getRecordPosition(this.index) < this.BINARY_FILE_DAO.getFileLength();
        }

        @Override
        public S next() {
            try (RandomAccessFile raf = new RandomAccessFile(this.BINARY_FILE_DAO.getFile(), "r")) {
                byte[] byteArray = new byte[(int) this.BINARY_FILE_DAO.getRecordSize()];
                raf.seek(this.BINARY_FILE_DAO.getRecordPosition(this.index));
                raf.read(byteArray);
                this.index++;
                return this.BINARY_FILE_DAO.getFactory().create(byteArray);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public void remove() {
            try {
                this.BINARY_FILE_DAO.deleteRecord(--this.index);
            } catch (IOException e) {
            }
        }
    }
}
