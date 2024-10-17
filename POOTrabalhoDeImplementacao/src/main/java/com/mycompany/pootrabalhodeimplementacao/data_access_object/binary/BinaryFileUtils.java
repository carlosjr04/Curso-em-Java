/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object.binary;

/**
 *
 * @author felipe
 */
public class BinaryFileUtils {

    public static <T extends IBinaryConvertable> byte[] toByteArray(T object) {
        return object.toByteArray();
    }

    public static <T> T fromByteArray(IBinaryObjectFactory<? extends T> factory, byte[] byteArray) {
        return factory.create(byteArray);
    }
}
