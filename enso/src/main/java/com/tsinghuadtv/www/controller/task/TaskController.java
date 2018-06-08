package com.tsinghuadtv.www.controller.task;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.constant.SessionConstants;
import com.tsinghuadtv.www.controller.api.common.ServiceResponse;
import com.tsinghuadtv.www.controller.task.api.GetTaskListResponse;
import com.tsinghuadtv.www.controller.task.api.TaskVO;
import com.tsinghuadtv.www.entity.task.Task;
import com.tsinghuadtv.www.service.task.ITaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private ITaskService taskService;
	
	@RequestMapping("/list")
	@ResponseBody
	public ServiceResponse getTaskList(HttpSession session) {		
		
		GetTaskListResponse response = new GetTaskListResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			return response;
		}
		
		List<Task> tasks = taskService.getTaskList(userNumber);
		
		response.setTasks(TaskVO.fromTaskList(tasks));
		
		return response;
	}
}


