package com.tsinghuadtv.www.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;

public class FileUtil {

	/**
	 * 将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath。
	 * 
	 * @param sourceFilePath
	 *            待压缩的文件路径
	 * @param zipFilePath
	 *            压缩后存放路径
	 * @param fileName
	 *            压缩后文件的名称
	 * @return flag
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath,
			String fileName) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		if (sourceFile.exists() == false) {
			System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath
					+ " 不存在. <<<<<<");
			flag = false;
			return flag;
		} else {
			try {
				File zipFile = new File(zipFilePath + "/" + fileName);
				if (zipFile.exists()) {
//					System.out.println(">>>>>> " + zipFilePath + " 目录下存在名字为："
//							+ fileName + " 打包文件. <<<<<<");
					zipFile.delete();
				}
				File[] sourceFiles = sourceFile.listFiles();
				/*
				 * if(null == sourceFiles || sourceFiles.length < 1) {
				 * System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath +
				 * " 里面不存在文件,无需压缩. <<<<<<"); flag = false; return flag; }
				 */
				ZipOutputStream zos = new ZipOutputStream(
						new BufferedOutputStream(new FileOutputStream(zipFile))); // 用到时才申明，否则容易出现问题，记得先开后关，后开先关
				byte[] bufs = new byte[1024 * 10]; // 缓冲块
				for (int i = 0; i < sourceFiles.length; i++) {
					// 创建ZIP实体,并添加进压缩包
					ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
					zos.putNextEntry(zipEntry);
					// 读取待压缩的文件并写进压缩包里
					BufferedInputStream bis = new BufferedInputStream(
							new FileInputStream(sourceFiles[i]), 1024 * 10);// 用到时才申明，否则容易出现问题，记得先开后关，后开先关
					int read = 0;
					while ((read = (bis.read(bufs, 0, 1024 * 10))) != -1) {
						zos.write(bufs, 0, read);
					}
					if (null != bis)
						bis.close(); // 关闭
				}
				flag = true;
				if (null != zos)
					zos.close(); // 关闭

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return flag;
	}
	
	public static void fileMove(String from, String to) throws Exception {// 移动指定文件夹内的全部文件  
        try {  
            File dir = new File(from);  
            File[] files = dir.listFiles();// 将文件或文件夹放入文件集  
            if (files == null)// 判断文件集是否为空  
                return;  
            File moveDir = new File(to);// 创建目标目录  
            if (!moveDir.exists()) {// 判断目标目录是否存在  
                moveDir.mkdirs();// 不存在则创建  
            }  
            for (int i = 0; i < files.length; i++) {// 遍历文件集  
                if (files[i].isDirectory()) {// 如果是文件夹或目录,则递归调用fileMove方法，直到获得目录下的文件  
                    fileMove(files[i].getPath(), to + "\\" + files[i].getName());// 递归移动文件  
                    files[i].delete();// 删除文件所在原目录  
                }  
                File moveFile = new File(moveDir.getPath() + "\\"// 将文件目录放入移动后的目录  
                        + files[i].getName());  
                if (moveFile.exists()) {// 目标文件夹下存在的话，删除  
                    moveFile.delete();  
                }  
                files[i].renameTo(moveFile);// 移动文件  
                System.out.println(files[i] + " 移动成功");  
            }  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
  
    // 复制目录下的文件（不包括该目录）到指定目录，会连同子目录一起复制过去。  
    public static void copyFileFromDir(String toPath, String fromPath) {  
        File file = new File(fromPath);  
        createFile(toPath, false);// true:创建文件 false创建目录  
        if (file.isDirectory()) {// 如果是目录  
            copyFileToDir(toPath, listFile(file));  
        }  
    }  
  
    // 复制目录到指定目录,将目录以及目录下的文件和子目录全部复制到目标目录  
    public static void copyDir(String toPath, String fromPath) {  
        File targetFile = new File(toPath);// 创建文件  
        createFile(targetFile, false);// 创建目录  
        File file = new File(fromPath);// 创建文件  
        if (targetFile.isDirectory() && file.isDirectory()) {// 如果传入是目录  
            copyFileToDir(targetFile.getAbsolutePath() + "/" + file.getName(),  
                    listFile(file));// 复制文件到指定目录  
        }  
    }  
  
    // 复制一组文件到指定目录。targetDir是目标目录，filePath是需要复制的文件路径  
    public static void copyFileToDir(String toDir, String[] filePath) {  
        if (toDir == null || "".equals(toDir)) {// 目录路径为空  
            System.out.println("参数错误，目标路径不能为空");  
            return;  
        }  
        File targetFile = new File(toDir);  
        if (!targetFile.exists()) {// 如果指定目录不存在  
            targetFile.mkdir();// 新建目录  
        } else {  
            if (!targetFile.isDirectory()) {// 如果不是目录  
                System.out.println("参数错误，目标路径指向的不是一个目录！");  
                return;  
            }  
        }  
        for (int i = 0; i < filePath.length; i++) {// 遍历需要复制的文件路径  
            File file = new File(filePath[i]);// 创建文件  
            if (file.isDirectory()) {// 判断是否是目录  
                copyFileToDir(toDir + file.getName(), listFile(file));// 递归调用方法获得目录下的文件  
                System.out.println("复制文件 " + file);  
            } else {  
                copyFileToDir(toDir, file, "");// 复制文件到指定目录  
            }  
        }  
    }  
  
    public static void copyFileToDir(String toDir, File file, String newName) {// 复制文件到指定目录  
        String newFile = "";  
        if (newName != null && !"".equals(newName)) {  
            newFile = toDir +  newName;  
        } else {  
            newFile = toDir +  file.getName();  
        }  
        File tFile = new File(newFile);  
        copyFile(tFile, file);// 调用方法复制文件  
    }  
    
    public static void moveFileToDir(String toDir, File file, String newName) throws IOException {// 复制文件到指定目录  
        String newFile = "";  
     // 如果文件夹不存在则创建
		File tofile = new File(toDir);
		if (!tofile.exists() && !tofile.isDirectory()) {
			tofile.mkdirs();
		}
        if (newName != null && !"".equals(newName)) {  
            newFile = toDir +  newName;  
        } else {  
            newFile = toDir +  file.getName();  
        }  
        File tFile = new File(newFile);  
        moveFile(tFile, file);// 调用方法复制文件  
    }  
  
    public static void copyFile(File toFile, File fromFile) {// 复制文件  
    	if (toFile.exists()){
    		toFile.delete();
    		}
        createFile(toFile, true);// 创建文件  
     
        System.out.println("复制文件" + fromFile.getAbsolutePath() + "到"  
                + toFile.getAbsolutePath());
        try {
            int byteread = 0;
            InputStream is = new FileInputStream(fromFile);// 创建文件输入流  
            FileOutputStream fos = new FileOutputStream(toFile);// 文件输出流  
            byte[] buffer = new byte[1024];// 字节数组  
            while ((byteread = is.read(buffer)) != -1) {// 将文件内容写到文件中
                fos.write(buffer, 0, byteread);
            }  
            if(fos!=null)  fos.close();// 输出流关闭  
            if(is!=null) is.close();// 输入流关闭  
          
        } catch (FileNotFoundException e) {// 捕获文件不存在异常  
            e.printStackTrace();  
        } catch (IOException e) {// 捕获异常  
            e.printStackTrace();  
        }  
                
        
    }  
    /** *//**文件重命名
     * @param oldname  原来的文件名 
     * @param newname 新文件名 
     */ 
     public static void renameFile(String oldname,String newname){ 
       if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
             File oldfile=new File(oldname); 
             File newfile=new File(newname); 
             if(!oldfile.exists()){
                 return;//重命名文件不存在
             }
             if(newfile.exists())
             {
            	//System.out.println(newname+"已经存在！");//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
            	 newfile.delete(); 
            	 oldfile.renameTo(newfile); 
             }                  
             else{ 
                 oldfile.renameTo(newfile); 
             } 
         }else{
             System.out.println("新文件名和旧文件名相同...");
         }
     }

    public static boolean moveFile(String toFile, String fromFile) {
        boolean rt = false;
        File _fromFile = new File(fromFile);
        File _toFile = new File(toFile);
        if(_fromFile.exists()) {
            try {
                moveFile(_toFile, _fromFile);
                rt = true;
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        return rt;
    }

    public static void moveFile(File toFile, File fromFile) throws IOException {// 复制文件
    	if (toFile.exists()){
    		toFile.delete();
    		}
       //createFile(toFile, true);// 创建文件  
     
        //System.out.println("移动文件" + fromFile.getAbsolutePath() + "到"  
        //        + toFile.getAbsolutePath());
        int byteread = 0;
        InputStream is = null;// 创建文件输入流  
        FileOutputStream fos =null;// 文件输出流  
        
        try {  
        	 is = new FileInputStream(fromFile);// 创建文件输入流  
            fos = new FileOutputStream(toFile);// 文件输出流  
           byte[] buffer = new byte[1024];// 字节数组  
            while ((byteread = is.read(buffer)) != -1) {// 将文件内容写到文件中
                fos.write(buffer, 0, byteread);
            }  
            if(fos!=null)  fos.close();// 输出流关闭 
            if(is!=null) is.close();// 输入流关闭  
           
        } catch (FileNotFoundException e) {// 捕获文件不存在异常  
            e.printStackTrace();  
        } catch (IOException e) {// 捕获异常  
            e.printStackTrace();  
        }  finally{
        	 if(fos!=null)  fos.close();// 输出流关闭  
        	 if(is!=null)	is.close();            
        }

      //删除源文件
        fromFile.delete();
    
    }  
    public static String[] listFile(File dir) {// 获取文件绝对路径  
        String absolutPath = dir.getAbsolutePath();// 声获字符串赋值为路传入文件的路径  
        String[] paths = dir.list();// 文件名数组  
        String[] files = new String[paths.length];// 声明字符串数组，长度为传入文件的个数  
        for (int i = 0; i < paths.length; i++) {// 遍历显示文件绝对路径  
            files[i] = absolutPath + "/" + paths[i];  
        }  
        return files;  
    }  
    /** 
     * 通配符匹配 
     * @param pattern    通配符模式 
     * @param str    待匹配的字符串 
     * @return    匹配成功则返回true，否则返回false 
     */  
    private static boolean wildcardMatch(String pattern, String str) {  
        int patternLength = pattern.length();  
        int strLength = str.length();  
        int strIndex = 0;  
        char ch;  
        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {  
            ch = pattern.charAt(patternIndex);  
            if (ch == '*') {  
                //通配符星号*表示可以匹配任意多个字符  
                while (strIndex < strLength) {  
                    if (wildcardMatch(pattern.substring(patternIndex + 1),  
                            str.substring(strIndex))) {  
                        return true;  
                    }  
                    strIndex++;  
                }  
            } else if (ch == '?') {  
                //通配符问号?表示匹配任意一个字符  
                strIndex++;  
                if (strIndex > strLength) {  
                    //表示str中已经没有字符匹配?了。  
                    return false;  
                }  
            } else {  
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {  
                    return false;  
                }  
                strIndex++;  
            }  
        }  
        return (strIndex == strLength);  
    }
    public static void findDirbyFilename(String baseDirName,String targetFileName, List<File> fileList) {
    	String tempName = null;  
        //判断目录是否存在  
        File baseDir = new File(baseDirName);  
        if (!baseDir.exists() || !baseDir.isDirectory()){  
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");  
        } else {  
            String[] filelist = baseDir.list();  
            for (int i = 0; i < filelist.length; i++) {  
                File readfile = new File(baseDirName + "\\" + filelist[i]);  
                if(!readfile.isDirectory()) {  
                    tempName =  readfile.getName();   
                    if (wildcardMatch(targetFileName, tempName)) {  
                        //匹配成功，将文件名添加到结果集  
                        fileList.add(readfile.getAbsoluteFile());   
                    }  
                } else if(readfile.isDirectory()){  
                    findFiles(baseDirName + "\\" + filelist[i],targetFileName,fileList);  
                }  
            }  
        } 
    }
    public static void findDirs(String baseDirName, String targetDirName, List<File> fileList) {  
        /** 
         * 算法简述： 
         * 从某个给定的需查找的文件夹出发，搜索该文件夹的所有子文件夹及文件， 
         * 若为文件，则进行匹配，匹配成功则加入结果集，若为子文件夹，则进队列。 
         * 队列不空，重复上述操作，队列为空，程序结束，返回结果。 
         */  
        String tempName = null;  
        
        //判断目录是否存在  
        File baseDir = new File(baseDirName);
        if (!baseDir.exists() || !baseDir.isDirectory()){  
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");  
        } else {  
            String[] filelist = baseDir.list();  
            for (int i = 0; i < filelist.length; i++) {  
                File readfile = new File(baseDirName + "/" + filelist[i]);
                if(readfile.isDirectory()) {  
                    tempName =  readfile.getName();   
                    if (wildcardMatch(targetDirName, tempName)) {
                        fileList.add(readfile.getAbsoluteFile());   
                    } else {
                    	findDirs(baseDirName + "/" + filelist[i],targetDirName,fileList);
                    }   
                } 
            }  
        }  
    }  
    public static void findFiles(String baseDirName, String targetFileName, List<File> fileList) {  
        /** 
         * 算法简述： 
         * 从某个给定的需查找的文件夹出发，搜索该文件夹的所有子文件夹及文件， 
         * 若为文件，则进行匹配，匹配成功则加入结果集，若为子文件夹，则进队列。 
         * 队列不空，重复上述操作，队列为空，程序结束，返回结果。 
         */  
        String tempName = null;  
        //判断目录是否存在  
        File baseDir = new File(baseDirName);  
        if (!baseDir.exists() || !baseDir.isDirectory()){  
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");  
        } else {  
            String[] filelist = baseDir.list();  
            for (int i = 0; i < filelist.length; i++) {  
                File readfile = new File(baseDirName + "\\" + filelist[i]);  
                //System.out.println(readfile.getName());  
                if(!readfile.isDirectory()) {  
                    tempName =  readfile.getName();   
                    if (wildcardMatch(targetFileName, tempName)) {  
                        //匹配成功，将文件名添加到结果集  
                        fileList.add(readfile.getAbsoluteFile());   
                    }  
                } else if(readfile.isDirectory()){  
                    findFiles(baseDirName + "\\" + filelist[i],targetFileName,fileList);  
                }  
            }  
        }  
    }  
    public static void createFile(String path, boolean isFile) {// 创建文件或目录  
        createFile(new File(path), isFile);// 调用方法创建新文件或目录  
    }

    // 移动文件到指定目录
 	public static void moveFileToDes(String path, String condition, String name,
 			String dest) {
 		File tmpfile = new File(path + name);
 		if (tmpfile.exists()) {
 			String str_tofilepath = path + condition + dest;
 			try {
 				FileUtil.moveFileToDir(str_tofilepath, tmpfile, name);
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		}
 	}

    public static void deleteFile(String path) {
        File tmpfile = new File(path);
        if( tmpfile.exists()) {
            tmpfile.delete();
        }
    }
     
    public static void createFile(File file, boolean isFile) {// 创建文件  
        if (!file.exists()) {// 如果文件不存在  
            if (!file.getParentFile().exists()) {// 如果文件父目录不存在  
                createFile(file.getParentFile(), false);  
            } else {// 存在文件父目录  
                if (isFile) {// 创建文件  
                    try {  
                        file.createNewFile();// 创建新文件  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                } else {  
                    file.mkdir();// 创建目录  
                }  
            }  
        }  
    }

    public static void createDir(String path) {
        File tmpFile = new File(path);
        if(!tmpFile.exists()) {
            tmpFile.mkdir();
        }
    }

    public static boolean exist(String file_path) {
        File file = new File(file_path);
        return file.exists();
    }

    /**
     * 解压缩zip包
     * @param zipFilePath zip文件的全路径
     * @param unzipFilePath 解压后的文件保存的路径
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含
     */
    public static void unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws Exception
    {
        File zipFile = new File(zipFilePath);
        //如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径
        if (includeZipFileName)
        {
            String fileName = zipFile.getName();
            if (StringUtils.isNotEmpty(fileName))
            {
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
            }
            unzipFilePath = unzipFilePath + File.separator + fileName;
        }
        //创建解压缩文件保存的路径
        File unzipFileDir = new File(unzipFilePath);
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory())
        {
            unzipFileDir.mkdirs();
        }

        //开始解压
        ZipEntry entry = null;
        String entryFilePath = null, entryDirPath = null;
        File entryFile = null, entryDir = null;
        int index = 0, count = 0, bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<? extends ZipEntry> entries = zip.entries();
        //循环对压缩包里的每一个文件进行解压
        while(entries.hasMoreElements())
        {
            entry = entries.nextElement();
            //构建压缩包中一个文件解压后保存的文件全路径
            entryFilePath = unzipFilePath + File.separator + entry.getName();
            entryFilePath = entryFilePath.replace("/",File.separator);
            //构建解压后保存的文件夹路径
            index = entryFilePath.lastIndexOf(File.separator);
            if (index != -1)
            {
                entryDirPath = entryFilePath.substring(0, index);
            }
            else
            {
                entryDirPath = "";
            }
            entryDir = new File(entryDirPath);
            //如果文件夹路径不存在，则创建文件夹
            if (!entryDir.exists() || !entryDir.isDirectory())
            {
                entryDir.mkdirs();
            }

            //创建解压文件
            entryFile = new File(entryFilePath);
            if (entryFile.isDirectory()) {
                continue;
            }

            if (entryFile.exists())
            {
                //删除已存在的目标文件
                entryFile.delete();
            }

            //写入文件
            bos = new BufferedOutputStream(new FileOutputStream(entryFile));
            bis = new BufferedInputStream(zip.getInputStream(entry));
            while ((count = bis.read(buffer, 0, bufferSize)) != -1)
            {
                bos.write(buffer, 0, count);
            }
            bos.flush();
            bos.close();
        }
        zip.close();
    }
}
