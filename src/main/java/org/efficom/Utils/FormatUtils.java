package org.efficom.Utils;

public class FormatUtils {

    public static String clean(String text){
        return text.replace('à','a').replace('é','e').replace('è','e')
                .replace('ê','e').toLowerCase();
    }

    public static int updateIndexInsideBounds(int nouveauIndice,char[] alphabet){
        if(0<nouveauIndice && nouveauIndice< alphabet.length) return nouveauIndice;
        else if(nouveauIndice > alphabet.length){
            nouveauIndice = nouveauIndice - alphabet.length;
            return updateIndexInsideBounds(nouveauIndice,alphabet);
        }
        else if(nouveauIndice < 0){
            nouveauIndice = nouveauIndice + alphabet.length;
            return updateIndexInsideBounds(nouveauIndice,alphabet);
        }
        else {
            return nouveauIndice;
        }
    }
}
