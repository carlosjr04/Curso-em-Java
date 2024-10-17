/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object.binary;

/**
 *
 * @author felipe
 * @param <T>
 */
public interface IBinaryObjectFactory<T> {

    public abstract T create(byte[] byteArray);
}
