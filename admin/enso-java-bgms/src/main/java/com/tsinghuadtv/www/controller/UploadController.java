package com.tsinghuadtv.www.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.tsinghuadtv.www.entity.T_video;
import com.tsinghuadtv.www.service.UploadService;
import com.tsinghuadtv.www.util.ResponseUtil;

@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	private ResponseUtil responseUtil = new ResponseUtil();

	@RequestMapping(value = "/newVideo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseUtil newVideo(T_video video) throws Exception {
		ResponseUtil responseUtil = null;
		try {
			responseUtil = new ResponseUtil();
			int ra = uploadService.add(video);
			if (ra > 0) {
				responseUtil.setResult(0);
				responseUtil.setCause(String.valueOf(ra));
			} else {
				responseUtil.setResult(1);
				responseUtil.setCause("insert db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(-1);
			responseUtil.setCause("insert db exception...");
		}
		return responseUtil;
	}

	// http://localhost:8111/enso-java-bgms/getvideos?type=%E5%AE%8B%E5%9F%8E%E6%95%99%E8%82%B2
	@RequestMapping(value = "/getvideos", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<T_video> getvideos(String type) throws Exception {
		List<T_video> list = uploadService.gett_video(type);
		return list;
	}

	@RequestMapping(value = "/getVideoById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody T_video getVideoById(Integer id) throws Exception {
		T_video video = uploadService.getVideoById(id);
		return video;
	}

	@RequestMapping(value = "/updateVideo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseUtil updateVideo(T_video video) throws Exception {
		ResponseUtil responseUtil = null;
		try {
			responseUtil = new ResponseUtil();
			int ra = uploadService.update(video);
			if (ra > 0) {
				responseUtil.setResult(0);
				responseUtil.setCause("success");
			} else {
				responseUtil.setResult(1);
				responseUtil.setCause("update db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(-1);
			responseUtil.setCause("update db error...");
		}
		return responseUtil;
	}

	@RequestMapping(value = "/getcourses", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<T_video> getcourses(String school) throws Exception {
		List<T_video> list = uploadService.gett_course(school);
		return list;
	}

	@RequestMapping(value = "/getCourseById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody T_video getCourseById(Integer id) throws Exception {
		T_video course = uploadService.getCourseById(id);
		return course;
	}

	@RequestMapping(value = "/updateCourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseUtil updateCourse(HttpServletRequest request, @RequestParam("img") MultipartFile img)
			throws Exception {

		ResponseUtil responseUtil = null;

		T_video course = new T_video();
		course.setId(Integer.parseInt(request.getParameter("id")));
		course.setTitle((String) request.getParameter("title"));
		course.setSchool((String) request.getParameter("school"));
		course.setVideoid((String) request.getParameter("videoid"));
		String url = (String) request.getParameter("url");
		if (null == url) {
			url = "javascript:;";
		}
		course.setUrl(url);
		course.setImg(this.upload(img, request));

		try {
			responseUtil = new ResponseUtil();

			int ra = uploadService.updateCourse(course);
			if (ra > 0) {
				responseUtil.setResult(0);
				responseUtil.setCause("success");
			} else {
				responseUtil.setResult(1);
				responseUtil.setCause("update db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(-1);
			responseUtil.setCause("update db error...");
		}

		return responseUtil;
	}	

	@RequestMapping(value = "/newCourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseUtil newCourse(HttpServletRequest request, @RequestParam("img") MultipartFile img) throws Exception {
		ResponseUtil responseUtil = null;
		T_video course = new T_video();
		course.setTitle((String) request.getParameter("title"));
		course.setSchool((String) request.getParameter("school"));
		course.setVideoid((String) request.getParameter("videoid"));
		
		// 上传课程图片
		course.setImg(this.upload(img, request));	
		try {
			responseUtil = new ResponseUtil();
			int ra = uploadService.addCourse(course);
			if (ra > 0) {
				responseUtil.setResult(0);
				responseUtil.setCause(String.valueOf(ra));
			} else {
				responseUtil.setResult(1);
				responseUtil.setCause("insert db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(-1);
			responseUtil.setCause("insert db exception...");
		}
		return responseUtil;
	}

	@RequestMapping(value = "/listSchoolByArea", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<T_video> listSchoolByArea(String area) throws Exception {
		List<T_video> list = uploadService.listSchoolByArea(area);
		return list;
	}

	@RequestMapping(value = "/getSchoolById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody T_video getSchoolById(Integer id) throws Exception {
		T_video course = uploadService.getSchoolById(id);
		return course;
	}

	// 更新学校
	@RequestMapping(value = "/updateSchool", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseUtil updateSchool(HttpServletRequest request, 
            @RequestParam("logo") MultipartFile logo,
            @RequestParam("avatar") MultipartFile avatar) throws Exception {
		ResponseUtil responseUtil = null;
		
		T_video school = new T_video();
		school.setId( Integer.valueOf(request.getParameter("id")) );
		school.setTitle((String) request.getParameter("title"));      // 学校名称
		school.setPinyin((String) request.getParameter("pinyin"));    // 校名拼音
		school.setArea((String) request.getParameter("area"));        // 学校所在地区 开封/濮阳/商丘
		school.setLogo(this.upload(logo, request));                   // 学校logo
		school.setContent((String) request.getParameter("content"));  // 学校简介
		school.setAvatar(this.upload(avatar, request));               // 学校校长头像
		school.setWords((String) request.getParameter("words"));      // 校长寄语
		
		try {
			responseUtil = new ResponseUtil();
			int ra = uploadService.updateSchool(school);
			if (ra > 0) {
				responseUtil.setResult(0);
				responseUtil.setCause("success");
			} else {
				responseUtil.setResult(1);
				responseUtil.setCause("update db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(-1);
			responseUtil.setCause("update db threw exception...");
		}
		return responseUtil;
	}

	// 新增学校
	@RequestMapping(value = "/newSchool", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseUtil newSchool(HttpServletRequest request, 
			                                   @RequestParam("logo") MultipartFile logo,
			                                   @RequestParam("avatar") MultipartFile avatar) throws Exception {
	
		ResponseUtil responseUtil = null;
		
		T_video school = new T_video();
		school.setTitle((String) request.getParameter("title"));   // 学校名称
		school.setPinyin((String) request.getParameter("pinyin")); // 校名拼音
		school.setArea((String) request.getParameter("area"));    // 学校所在地区 开封/濮阳/商丘
		school.setLogo(this.upload(logo, request));                // 学校logo
		school.setContent((String) request.getParameter("content"));  // 学校简介
		school.setAvatar(this.upload(avatar, request));               // 学校校长头像
		school.setWords((String) request.getParameter("words"));   // 校长寄语
		
		try {
			responseUtil = new ResponseUtil();
			int ra = uploadService.addSchool(school);
			if (ra > 0) {
				responseUtil.setResult(0);
				responseUtil.setCause(String.valueOf(ra));
			} else {
				responseUtil.setResult(1);
				responseUtil.setCause("insert db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(-1);
			responseUtil.setCause("insert db exception...");
		}
		return responseUtil;
	}

	// 教师/学生风采
	@RequestMapping(value = "/getTeachers", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<T_video> getTeachers(String school, String type) throws Exception {
		List<T_video> list = uploadService.gett_teacher(school, type);
		return list;
	}

	@RequestMapping(value = "/getTeacherById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody T_video getTeacherById(Integer id) throws Exception {
		T_video course = uploadService.getTeacherById(id);
		return course;
	}

	@RequestMapping(value = "/updateTeacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseUtil updateTeacher(HttpServletRequest request, 
                               @RequestParam("img") MultipartFile img) throws Exception {
		ResponseUtil responseUtil = null;
		
		T_video teacher = new T_video();
		teacher.setId(Integer.valueOf(request.getParameter("id")));
		teacher.setName(request.getParameter("name"));
		teacher.setSchool(request.getParameter("school"));
		teacher.setType(request.getParameter("type"));
		teacher.setImg(this.upload(img, request));
		teacher.setIntro(request.getParameter("intro"));
		teacher.setContent(request.getParameter("content"));
		
		try {
			responseUtil = new ResponseUtil();
			int ra = uploadService.updateTeacher(teacher);
			if (ra > 0) {
				responseUtil.setResult(0);
				responseUtil.setCause("success");
			} else {
				responseUtil.setResult(1);
				responseUtil.setCause("update db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(-1);
			responseUtil.setCause("Update Database Exception thrown...");
		}
		return responseUtil;
	}

	// 新增教师&学生
	@RequestMapping(value = "/newTeacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseUtil newTeacher(HttpServletRequest request, 
            @RequestParam("img") MultipartFile img) throws Exception {
		ResponseUtil responseUtil = null;
		
		T_video teacher = new T_video();
		teacher.setName(request.getParameter("name"));
		teacher.setSchool(request.getParameter("school"));
		teacher.setType(request.getParameter("type"));
		teacher.setImg(this.upload(img, request));
		teacher.setIntro(request.getParameter("intro"));
		teacher.setContent(request.getParameter("content"));
		
		try {
			responseUtil = new ResponseUtil();
			int ra = uploadService.addTeacher(teacher);
			if (ra > 0) {
				responseUtil.setResult(0);
				responseUtil.setCause(String.valueOf(ra));
			} else {
				responseUtil.setResult(1);
				responseUtil.setCause("insert db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(-1);
			responseUtil.setCause("insert db exception...");
		}
		return responseUtil;
	}
	
	/**
	 * 上传图片
	 * @param img   file[type="file]
	 * @param request
	 * @return   上传之后图片所在url
	 */
	private String upload(MultipartFile img, HttpServletRequest request) {
		String imageURL = null;
		
		// 重新上传了图片 更新图片
		if (0 != img.getSize()) {
			Properties prop = null;
			try {
				InputStream inStream = UploadController.class.getClassLoader().getResourceAsStream("const.properties");
				prop = new Properties();
				prop.load(inStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 课程图片上传 img
			String uploadDir = request.getSession().getServletContext().getRealPath("/") + prop.getProperty("const.upload") + "/";
			File dir = new File(uploadDir);
			if (!dir.exists()) {dir.mkdirs();}
			
			Date date = new Date();			
			String subdir = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
			uploadDir += subdir+"/";
			dir = new File(uploadDir);
			if (!dir.exists()) {dir.mkdirs();}			
			System.out.println(uploadDir);			
			
			String ds = (new SimpleDateFormat("yyyyMMddHHmmss")).format(date);
			System.out.println(ds);
			String filename = ds + "." + img.getOriginalFilename();
			System.out.println(filename);

			File targetFile = new File(uploadDir + filename);
			try {
				if (!targetFile.exists()) {
					targetFile.createNewFile();
				}
				System.out.println("文件上传到: " + targetFile.getAbsolutePath());
				img.transferTo(targetFile);

				imageURL = prop.getProperty("const.base_url") + "/" + prop.getProperty("const.upload")
									+ "/" + subdir + "/" + filename;								
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imageURL;
	}

	@RequestMapping("/oneUpload")
	public String oneUpload(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) {

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

		return "redirect:http://localhost:8111/upload/upload/" + filename;
	}

	@RequestMapping("/moreUpload")
	public String moreUpload(HttpServletRequest request) {

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();

		String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		List<String> fileList = new ArrayList<String>();

		for (MultipartFile file : files.values()) {
			File targetFile = new File(uploadUrl + file.getOriginalFilename());
			if (!targetFile.exists()) {
				try {
					targetFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					file.transferTo(targetFile);
					fileList.add("http://localhost:8111/upload/upload/" + file.getOriginalFilename());
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		request.setAttribute("files", fileList);

		return "moreUploadResult";
	}

}
