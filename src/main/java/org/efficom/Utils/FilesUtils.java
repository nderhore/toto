package org.efficom.Utils;

import java.io.*;

public class FilesUtils {

    public static String openFile(String text){
        BufferedReader bufferedReader;
        String contenuFichier="";
        try {
            bufferedReader = new BufferedReader(new FileReader(text));
            String line = bufferedReader.readLine();
            while (line != null) {
                contenuFichier = contenuFichier + "\n" + line;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return contenuFichier;
    }


    public static Boolean writeFile(String fileName,String text){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(text);
            writer.close();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
