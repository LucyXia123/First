/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsinghuadtv.www.util;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author liwenbo
 */
public class ConfigurationDir
{
    public static String dir;
    public static String httpUrl;
    public static Properties p = new Properties();
    static
    {
        try(InputStream is = ConfigurationDir.class.getResourceAsStream("/cfg.properties"))
        {
            p.load(is);
            dir = p.getProperty("dir");
            httpUrl=p.getProperty("httpUrl");
        }
        catch(Exception ex)
        {
            dir = "E:\\upload";
        }
    }
}
