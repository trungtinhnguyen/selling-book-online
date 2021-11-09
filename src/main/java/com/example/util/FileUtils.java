package com.example.util;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileUtils {

    private static FileUtils fileUtils;

    public static FileUtils getInstance () {
        if (fileUtils == null) {
            fileUtils = new FileUtils();
        }
        return fileUtils;
    }

    public static String getUploadFolder  () {
        String classpath =  getInstance().getRootPath();
        File uploadFolder = new File(classpath + "/uploads");
        if (!uploadFolder.exists() || !uploadFolder.isDirectory()) {
            if (!uploadFolder.mkdir()) {
                throw new IllegalArgumentException("Cannot create folder: " + uploadFolder.getName());
            }
        }
        return uploadFolder.getPath();
    }

    public String getRootPath () {
        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
        return url.getPath();
    }


   public static void saveFile(String fileName, byte[] src) {
       OutputStream writer = null;
       try {
           File file = new File(fileName);
           if (!file.exists()) {
               file.createNewFile();
           }
           writer = new FileOutputStream(file);
           writer.write(src);
       } catch (IOException e) {
           System.out.println(e.getMessage());
       } finally {
           if (writer != null) {
               try {
                   writer.close();
               } catch (IOException ignored){

               }
           }
       }
   }
}
