/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsinghuadtv.www.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 *
 * @author liwenbo
 */
public class DefaultAvatarImage
{
    public static String file = "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";
    public static String produceDefaultAvatarImage(String path,
                                                   String usernumber,
                                                   String url,
                                                   DateTimeFormatter dateTimeFormatter)
    {
         Base64.Decoder base64 = Base64.getDecoder();
         byte[] bytes = base64.decode(file);
         LocalDateTime creatTime = LocalDateTime.now();
         File imgeFile = Paths.get(path, "writerActivity").toFile();
         if (!imgeFile.exists() && !imgeFile.isDirectory())
         {
             imgeFile.mkdir();
         }
         File file = Paths.get(imgeFile.getAbsolutePath(), usernumber).toFile();
         if (!file.exists() && !file.isDirectory())
         {
             file.mkdir();
         }
         
         try (OutputStream os = new FileOutputStream(Paths.get(path, 
                                                              "writerActivity",
                                                              usernumber,
                                                              String.format("%s%s.%s",
                                                                            "avatar",
                                                                            creatTime.format(dateTimeFormatter),
                                                                            "jpg")).toFile()))
         {
             os.write(bytes);
             os.flush();
             return String.format("%s%s%s", 
                                   url,
                                   "/writerActivity/"+usernumber+"/",
                                   String.format("%s%s.%s",
                                                 "avatar",
                                                 creatTime.format(dateTimeFormatter),
                                                 "jpg"));
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
             return null;
         }
    }
    
    
    public static void main(String[] args)
    {
        String string = DefaultAvatarImage.produceDefaultAvatarImage(ConfigurationDir.dir,
                                                   "98888888",
                                                   ConfigurationDir.httpUrl,
                                                   DateTimeFormatter.ofPattern(
                                                           "yyyyMMddHHmmss"));
        System.out.println("string:"+string);
    }
}
