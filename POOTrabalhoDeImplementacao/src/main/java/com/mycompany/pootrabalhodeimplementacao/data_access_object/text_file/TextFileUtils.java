/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object.text_file;

/**
 *
 * @author felipe
 */
public class TextFileUtils {

    public static <T extends ITextFileConvertable> String toTextFileString(T object) {
        return object.toTextFileString();
    }

    public static <T> T fromTextFileString(ITextFileObjectFactory<T> factory, String textFileString) {
        return factory.create(textFileString);
    }
}
