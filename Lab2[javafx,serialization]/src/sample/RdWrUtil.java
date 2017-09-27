package sample;

import sample.entity.Train;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class RdWrUtil {

    public static List<Train> loadPersonDataFromFile(File file) {
        List<Train> trains = null;
        try (FileReader fr = new FileReader(file);
             FileInputStream fis = new FileInputStream(file);
             ObjectInputStream oin = new ObjectInputStream(fis);) {

            try {
                trains = (ArrayList<Train>) oin.readObject() ;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trains;
    }

    public static void savePersonDataToFile(File file, List<Train> trains) {

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos);)
        {
            oos.writeObject(new ArrayList<Train>(trains));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
