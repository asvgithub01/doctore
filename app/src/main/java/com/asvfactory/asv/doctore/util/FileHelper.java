package com.asvfactory.asv.doctore.util;

import com.asvfactory.asv.doctore.model.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Created by Alberto on 27/11/2015.
 */
public class FileHelper {

    public static void writeDoctoreFile(Serializable myObject, String fileId) {
        // Write to disk with FileOutputStream
        FileOutputStream f_out = null;
        try {
            f_out = new
                    FileOutputStream(fileId + Constants.DOCTORE_FILE_EXTENSION);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

// Write object with ObjectOutputStream
        ObjectOutputStream obj_out = null;
        try {
            obj_out = new
                    ObjectOutputStream(f_out);
        } catch (IOException e) {
            e.printStackTrace();
        }

// Write object out to disk
        try {
            obj_out.writeObject(myObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readDoctoreFile(String fileId) {
        // Read from disk using FileInputStream
        FileInputStream f_in = null;
        try {
            f_in = new FileInputStream(fileId + Constants.DOCTORE_FILE_EXTENSION);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

// Read object using ObjectInputStream
        ObjectInputStream obj_in =
                null;
        try {
            obj_in = new ObjectInputStream(f_in);
        } catch (IOException e) {
            e.printStackTrace();
        }

// Read an object
        Object obj = null;
        try {
            obj = obj_in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
