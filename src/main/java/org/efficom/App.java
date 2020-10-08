package org.efficom;

import org.efficom.Utils.CesarUtils;
import org.efficom.Utils.VigereUtils;

import java.io.*;
import java.lang.*;
import java.nio.file.Path;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int[] key = {1,3,2};
        char[] alphalist = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        CesarUtils.attaque_brute_force_sa("cpokpvs",alphalist);
    }


}
