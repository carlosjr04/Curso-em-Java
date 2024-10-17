package com.mycompany.pootrabalhodeimplementacao.data_access_object.text_file;

import com.mycompany.pootrabalhodeimplementacao.data_access_object.RecordsFileDataAccessObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author felipe
 * @param <T>
 */
public class TextFileDataAccessObject<T extends ITextFileConvertable> extends RecordsFileDataAccessObject<T> {

    private final ITextFileObjectFactory<T> FACTORY;

    public TextFileDataAccessObject(Path filePath, ITextFileObjectFactory<T> factory) {
        super(filePath);
        this.FACTORY = factory;
    }

    public TextFileDataAccessObject(File file, ITextFileObjectFactory<T> factory) {
        super(file);
        this.FACTORY = factory;
    }

    public Stream<String> getLinesStream() throws IOException {
        return Files.lines(this.getFile().toPath());
    }

    @Override
    public int getSize() {
        try (Stream<String> lines = this.getLinesStream()) {
            return (int) lines.count();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public T getRecord(int index) {
        try (Stream<String> lines = this.getLinesStream()) {
            return this.FACTORY.create(lines.skip(index).findFirst().get());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public T getRecord(Predicate<T> predicate) {
        for (Iterator<T> it = this.getIterator(); it.hasNext();) {
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
        Iterator<T> it = this.getIterator();

        while (records.size() < limit && it.hasNext()) {
            T next = it.next();
            if (predicate.test(next)) {
                records.add(next);
            }
        }

        return records;
    }

    @Override
    public List<T> getRecords(int limit, int offset) {
        return this.getRecords((T) -> true, limit, offset);
    }

    @Override
    public void addRecord(T object) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFilePath().toString(), true))) {
            bw.append(object.toTextFileString() + "\n");
        }
    }

    @Override
    public void deleteRecord(int index) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFilePath().toString()))) {
            Iterator<T> it = this.getIterator();

            for (int i = 0; it.hasNext(); i++) {
                if (i == index) {
                    break;
                }
                bw.append(it.next().toTextFileString() + "\n");
            }

            while (it.hasNext()) {
                bw.append(it.next().toTextFileString() + "\n");
            }
        }
    }

    @Override
    public void deleteRecord(Predicate<T> predicate) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFilePath().toString()))) {
            Iterator<T> it = this.getIterator();

            while (it.hasNext()) {
                T next = it.next();
                if (predicate.test(next)) {
                    break;
                }
                bw.append(next.toTextFileString() + "\n");
            }

            while (it.hasNext()) {
                bw.append(it.next().toTextFileString() + "\n");
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void deleteAllRecords(Predicate<T> predicate) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFilePath().toString()))) {
            Iterator<T> it = this.getIterator();

            while (it.hasNext()) {
                T next = it.next();
                if (predicate.test(next)) {
                    continue;
                }
                bw.append(next.toTextFileString() + "\n");
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void updateRecord(int index, T object) throws IOException {
        File tempFile = File.createTempFile("tempfile", ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile)); Stream<String> lines = this.getLinesStream()) {

            Iterator<String> iterator = lines.iterator();

            for (int i = 0; iterator.hasNext(); i++) {
                String line = iterator.next();
                if (i == index) {
                    bw.append(object.toTextFileString() + "\n");
                } else {
                    bw.append(line + "\n");
                }
            }
        }

        Files.delete(this.getFile().toPath());
        Files.move(tempFile.toPath(), this.getFile().toPath());
    }

    @Override
    public void updateRecord(Predicate<T> predicate, T object) throws IOException {
        File tempFile = File.createTempFile("tempfile", ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile)); Stream<String> lines = this.getLinesStream()) {

            Iterator<String> iterator = lines.iterator();

            while (iterator.hasNext()) {
                String line = iterator.next();
                T record = this.FACTORY.create(line);
                if (predicate.test(record)) {
                    bw.append(object.toTextFileString() + "\n");
                } else {
                    bw.append(line + "\n");
                }
            }
        }

        Files.delete(this.getFile().toPath());
        Files.move(tempFile.toPath(), this.getFile().toPath());
    }

    @Override
    public Iterator<T> getIterator(int index) {
        return new TextFileDAOIterator(this, index);
    }

    @Override
    public Iterator<T> getIterator() {
        return this.getIterator(0);
    }

    public static class TextFileDAOIterator<S extends ITextFileConvertable> implements Iterator<S> {

        private final TextFileDataAccessObject<S> TEXT_FILE_DAO;
        private final BufferedReader BR;

        public TextFileDAOIterator(TextFileDataAccessObject<S> tfdao, int start) {
            this.TEXT_FILE_DAO = tfdao;
            try {
                this.BR = new BufferedReader(new FileReader(this.TEXT_FILE_DAO.getFile()));
                this.BR.skip((long) start);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found", e);
            } catch (IOException e) {
                throw new RuntimeException("Error trying to initialize TextFileDAOIterator", e);
            }
        }

        @Override
        public boolean hasNext() {
            try {
                return BR.ready();
            } catch (IOException e) {
                throw new RuntimeException("Error trying to verify next element", e);
            }
        }

        @Override
        public S next() {
            try {
                String line = BR.readLine();
                if (line != null) {
                    return TEXT_FILE_DAO.FACTORY.create(line);
                }

                return null;
            } catch (IOException e) {
                throw new RuntimeException("Error trying to get next element", e);
            }
        }
    }
}
