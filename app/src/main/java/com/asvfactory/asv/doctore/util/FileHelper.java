package com.asvfactory.asv.doctore.util;

/**
 * Created by Alberto on 27/11/2015.
 */
public class FileHelper{

    public static void setInfoFile(String fileId)
    {
        // Write to disk with FileOutputStream
        FileOutputStream f_out = new
                FileOutputStream("myobject.data");

// Write object with ObjectOutputStream
        ObjectOutputStream obj_out = new
                ObjectOutputStream (f_out);

// Write object out to disk
        obj_out.writeObject ( myObject );
    }

    public static Object getTrainningFromFile(String fileId)
    {
        // Read from disk using FileInputStream
        FileInputStream f_in = new
                FileInputStream("myobject.data");

// Read object using ObjectInputStream
        ObjectInputStream obj_in =
                new ObjectInputStream (f_in);

// Read an object
        Object obj = obj_in.readObject();

        if (obj instanceof Vector)
        {
            // Cast object to a Vector
            Vector vec = (Vector) obj;

            // Do something with vector....
        }
    }
}
