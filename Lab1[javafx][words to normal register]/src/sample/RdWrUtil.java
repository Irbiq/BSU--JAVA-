package sample;

import java.io.*;
import java.util.Scanner;
import java.util.prefs.Preferences;

/**
 * Created by Maxim on 11.09.2016.
 */
public class RdWrUtil {

    static String loadPersonDataFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(file);
             Scanner sc = new Scanner(fr);) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            sc.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void savePersonDataToFile(File file, String str) {

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(str);
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
