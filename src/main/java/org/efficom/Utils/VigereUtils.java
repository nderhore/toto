package org.efficom.Utils;

public class VigereUtils {



    public static String encodeOrDecodeText(String text, int[] key, char[] alphalist, String operation){
        //mise en forme de la chaine de charactere
        text = FormatUtils.clean(text);

        //declaration des variables necessaires
        String encode = "";
        char letter;
        int index = 0;
        int indexKey = 0;
        int nouvelIndex = 0;

        //parcours de la chaine de charactère afin de prendre chaque lettre et faire un traitement spécifique.
        for(int i =0;i<text.length();i++){
            letter = text.charAt(i);

            for(int j=0;j<alphalist.length;j++){
                if(letter == alphalist[j]) index = j;
            }

                if (indexKey >= key.length) indexKey = indexKey - key.length;

                if(operation.equals("encode"))
            nouvelIndex = FormatUtils.updateIndexInsideBounds(index + key[indexKey],alphalist);
                else
                    nouvelIndex = FormatUtils.updateIndexInsideBounds(index-key[indexKey],alphalist);
            encode = encode + alphalist[nouvelIndex];
            indexKey++;
        }
        return encode;
    }
    public static String textToVig(String text, int[] key, char[] alphalist){

        return encodeOrDecodeText(text,key,alphalist,"encode");

    }
    public static String vigToText(String text,int[] key, char[] alphalist){

        return encodeOrDecodeText(text,key,alphalist,"decode");
    }
    static void fileToVig(String filename,int[] key,char[] alphalist){
        String contenu = FilesUtils.openFile(filename);
        contenu = textToVig(contenu,key,alphalist);
        FilesUtils.writeFile(filename.substring(0,filename.length()-4) + "_code.txt",contenu);
    }
    static void vigToFile(String filename,int[] key,char[] alphalist){
        String contenu = FilesUtils.openFile(filename);
        contenu = vigToText(contenu,key,alphalist);
        FilesUtils.writeFile(filename.substring(0,filename.length()-4) + "_decode.txt",contenu);
    }

}
