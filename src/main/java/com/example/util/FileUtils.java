package com.example.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {

    private final String HOST = "localhost:8080";
    private static FileUtils fileUtils;

    public static FileUtils getInstance () {
        if (fileUtils == null) {
            fileUtils = new FileUtils();
        }
        return fileUtils;
    }

    public String getUploadFolder  () {
        File uploadFolder = new File(HOST + "/uploads");
        if (!uploadFolder.exists() || !uploadFolder.isDirectory()) {
            if (!uploadFolder.mkdir()) {
                throw new IllegalArgumentException("Cannot create folder: " + uploadFolder.getName());
            }
        }
        return uploadFolder.getPath();
    }

   public void saveFile(String fileName, byte[] src) {
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
