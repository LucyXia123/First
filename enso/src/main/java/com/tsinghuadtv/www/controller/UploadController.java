package com.tsinghuadtv.www.controller;

import com.tsinghuadtv.www.util.ConfigurationDir;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController 
{
    //我要报名
    @RequestMapping(value = "/uploadApplyInfo", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String applyInfo(@RequestParam("files") MultipartFile file,
                            HttpServletRequest     request,
                            @RequestParam("title") String title)
    {
        //1、上传到图片
        try(InputStream  in = file.getInputStream();
            FileOutputStream out = new FileOutputStream(Paths.get(ConfigurationDir.dir, file.getOriginalFilename()).toFile()))
        {
            byte[] buf = new byte[2048];
            while (true)
            {
                int len = in.read(buf);
                if (len == -1)
                    break;
                out.write(buf, 0, len);
            }
            System.out.println("file write finish.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    //上传单一图片
    @RequestMapping(value = "/tomography/pool", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String uploadFiles(@RequestParam("files") MultipartFile file,
                              HttpServletRequest request)
    {
        System.out.println("file"+file.getOriginalFilename());
        try(InputStream  in = file.getInputStream();
            FileOutputStream out = new FileOutputStream(Paths.get("D:\\image", file.getOriginalFilename()).toFile()))
        {
            byte[] buf = new byte[2048];
            while (true)
            {
                int len = in.read(buf);
                if (len == -1)
                    break;
                out.write(buf, 0, len);
            }
            System.out.println("file write finish.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "ok";
    }
    
    @RequestMapping(value="/moreUpload/files",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String uploadMoreFile(@RequestParam("files") MultipartFile[] file)
    {
        System.out.println("list file size:"+file.length);
        for (MultipartFile f : file)
        {
            try(InputStream  in = f.getInputStream();
                FileOutputStream out = new FileOutputStream(Paths.get("D:\\image",f.getOriginalFilename()).toFile()))
                {
                    byte[] buf = new byte[2048];
                    while (true)
                    {
                        int len = in.read(buf);
                        if (len == -1)
                            break;
                        out.write(buf, 0, len);
                    }
                    System.out.println("file write finish.");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return "ok";
    }
	@RequestMapping("/util/oneUpload")
	public String oneUpload(@RequestParam("imageFile") MultipartFile imageFile,
			HttpServletRequest request) {
		
		String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
		
		String filename = imageFile.getOriginalFilename();	
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println("文件上传到: " + uploadUrl + filename);
		File targetFile = new File(uploadUrl + filename);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			imageFile.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:http://localhost/ENSO/upload/" + filename;
	}
	
	@RequestMapping("/moreUpload")
	public String moreUpload(HttpServletRequest request){
		System.out.println("moreUpload"+"============================================================");
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
		String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		List<String> fileList = new ArrayList<String>();
		for (MultipartFile file :  files.values()) {
			File targetFile = new File(uploadUrl + file.getOriginalFilename());
			if (!targetFile.exists()) {
				try {
					targetFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					file.transferTo(targetFile);
					fileList.add("http://localhost/ENSO/upload/" + file.getOriginalFilename());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("files", fileList);
		return "moreUploadResult";
	}
	
}
