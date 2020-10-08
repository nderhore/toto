package org.efficom.Utils;

import java.io.*;
import java.util.Scanner;

public class CesarUtils {




    static int[] occurenceOfLetter(String text,char[] alphabet){
        text = FormatUtils.clean(text);
        int[] occurence = new int[26];
        char letter;
        //on initialize le tableau
        for (int i=0;i<26;i++){
            occurence[i] = 0;
        }

        //on prend une lettre de la String
        for(int j=0;j<text.length();j++){
            letter = text.charAt(j);

            //je parcours le tableau des alphabets afin d'avoir la position
            for(int k=0;k<alphabet.length;k++){

                //une fois que j'ai trouvé l'index, j'update le nombre d'occurence dans le tableau des occurences.
                if (letter == alphabet[k]) {
                    occurence[k]++;
                }
            }
        }
        return occurence;
    }
    public static double[] rateOfLetter(String text, char[] asphalt){
        text = FormatUtils.clean(text);
        String textWithoutSpace = "";

        //On retire les espaces.
        for(int j=0;j<text.length();j++){
            if(!(text.charAt(j) == ' ')) textWithoutSpace = textWithoutSpace + text.charAt(j);
        }

        int[] occurrence = occurenceOfLetter(textWithoutSpace,asphalt);
        double[] frequency = new double[asphalt.length];
        for(int i=0;i<occurrence.length;i++){
            if(occurrence[i] == 0) frequency[i] = 0.0;
            else frequency[i] = ((occurrence[i]*1.0) / textWithoutSpace.length());
        }


        return frequency;
    }
    private static Boolean verificationLetterInArray(char letter,char[] alphalist){
        boolean verif = true;

        for(int i=0;i<alphalist.length;i++){
            if(alphalist[i] == letter) verif = false;
        }
        return verif;
    }
    public static char charToCesar(char letter,int key, char[] alphalist){

        //Si le charactere n'est pas dans le tableau.
        if(verificationLetterInArray(letter,alphalist)) return letter;

        int nouveauIndice = 0;

        for(int j=0;j<alphalist.length;j++){
            if (alphalist[j] == letter){
                nouveauIndice = j+key;
            }
        }

        return alphalist[FormatUtils.updateIndexInsideBounds(nouveauIndice,alphalist)];
    }
    public static char cesarToChar(char letter,int key,char[] alphalist){
        if((verificationLetterInArray(letter,alphalist))) return letter;
        int nouveauIndice = 0;

        for(int j=0;j<alphalist.length;j++){
            if (alphalist[j] == letter){
                nouveauIndice = j-key;
            }
        }

        return alphalist[FormatUtils.updateIndexInsideBounds(nouveauIndice,alphalist)];

    }
    static String decodeOrEncode(String text,int key,char[] alphalist,String operation){
        text = FormatUtils.clean(text);
        String phraseEncodeOrDecode = "";
        for(int i=0;i<text.length();i++){
            if(operation.equals("encode"))
            phraseEncodeOrDecode = phraseEncodeOrDecode + charToCesar(text.charAt(i),key,alphalist);
            else phraseEncodeOrDecode = phraseEncodeOrDecode + cesarToChar(text.charAt(i),key,alphalist);
        }
        return phraseEncodeOrDecode;
    }
    public static String textToCesar(String text, int key, char[] alphalist){
        return decodeOrEncode(text,key,alphalist,"encode");
    }
    public static String cesarToText(String encode,int key,char[] alphalist){
        return decodeOrEncode(encode,key,alphalist,"decode");
    }
    public static void fileToCesar(String filename,int key,char[] alphalist){
        String contenu = FilesUtils.openFile(filename);
        String encode= textToCesar(contenu,key,alphalist);
        FilesUtils.writeFile(filename.substring(0,filename.length()-4)+"_code.txt",encode);
    }
    public static void cesarToFile(String filename,int key,char[] alphalist){
        String contenu = cesarToText(FilesUtils.openFile(filename),key,alphalist);
        FilesUtils.writeFile(filename.substring(0,filename.length()-4)+"_encode.txt",contenu);
    }
    public static void attaque_brute_force_sa(String text,char[] alphalist){
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;
        String chiffrage = "";
        String saisie = "";
        int cle = 0;
        while(continuer) {
            chiffrage = cesarToText(text, cle, alphalist);
            if (chiffrage.length() < 40)
                System.out.println("Clé " + cle + " - Texte : " + chiffrage);
            else System.out.println("Clé " + cle + " - Texte : " + chiffrage.substring(0, 40));
            System.out.println("Pour tester la clé suivante, appuyer sur la touche N, sinon S pour stopper");
            saisie = sc.nextLine();
            cle +=1;
            if (!(saisie.equals("N"))) {
                continuer = false;

            }
        }
    }
}
