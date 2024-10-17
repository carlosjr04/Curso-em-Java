/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.data_access_object;

import java.io.File;

/**
 *
 * @author felipe
 * @param <T>
 */
public interface IRecordsFileDataAccessObjectFactory<T> {

    public abstract RecordsFileDataAccessObject<T> create(File file);
}
