package com.tsinghuadtv.www.service.task.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsinghuadtv.www.entity.Student;
import com.tsinghuadtv.www.entity.energy.SchoolEnergy;
import com.tsinghuadtv.www.entity.energy.StudentEnergy;
import com.tsinghuadtv.www.entity.report.ReportType;
import com.tsinghuadtv.www.entity.task.Task;
import com.tsinghuadtv.www.entity.task.TaskType;
import com.tsinghuadtv.www.entity.task.UserTask;
import com.tsinghuadtv.www.mapper.EnergyMapper;
import com.tsinghuadtv.www.mapper.StudentMapper;
import com.tsinghuadtv.www.mapper.TaskMapper;
import com.tsinghuadtv.www.model.Bool;
import com.tsinghuadtv.www.model.common.PagingData;
import com.tsinghuadtv.www.model.common.PagingResult;
import com.tsinghuadtv.www.model.filter.SearchResult;
import com.tsinghuadtv.www.model.filter.task.TaskFilter;
import com.tsinghuadtv.www.model.filter.task.UserTaskFilter;
import com.tsinghuadtv.www.service.task.ITaskService;

@Service
public class TaskService implements ITaskService {

	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private EnergyMapper energyMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public Task getTaskById(int id) {
		return taskMapper.getTaskById(id);
	}
	
	@Override
	public Task getTaskByTopicId(int topicId) {
		return taskMapper.getTaskByTopicId(topicId);
	}
	
	@Override
	public List<Task> getTaskList(String userNumber) {
		TaskFilter filter = new TaskFilter();
		
		filter.setDate(new Date());
		filter.setOpen(Bool.Y);
		filter.setInProgress(true);
		filter.setUserNumber(userNumber);
		
		return taskMapper.selectTaskByFilter(filter);
	}
	
	@Override
	public SearchResult<UserTask> searchUserTaskByFilter(UserTaskFilter filter) {
		
        SearchResult<UserTask> result = new SearchResult<>();

        List<UserTask> list = taskMapper.selectUserTaskByFilter(filter);
        result.setResult(list);

        PagingData pagingData = filter.getPagingData();
        if (filter.isPaged() && pagingData != null) {
            int recordNumber = taskMapper.countUserTaskByFilter(filter);

            PagingResult pagingResult = new PagingResult();

            pagingResult.setRecordNumber(recordNumber);
            pagingResult.setPageSize(pagingData.getPageSize());
            pagingResult.setPageNumber(pagingData.getPageNumber());

            result.setPaged(true);
            result.setPagingResult(pagingResult);
        }

        return result;
    }
	
	@Override
	@Transactional
	public void minusTaskProgress(Integer topicId, ReportType reportType, String userNumber) {
		
		Task task = taskMapper.getTaskByTopicId(topicId);
		if (task == null) {
			return;
		}
		if (task.getReportType() != null && task.getReportType() != reportType) {
			return;
		}
		
		UserTask userTask = taskMapper.selectUserTaskByUserAndTask(userNumber, task.getId());
		
		userTask.setCurrentCount(userTask.getCurrentCount() - 1);
		
		boolean finished = userTask.getCurrentCount() >= task.getFinishCount();
		if (!finished) {
			userTask.setFinished(Bool.N);
			userTask.setNullFinishTime(true);
		}
		
		taskMapper.updateUserTask(userTask);
		
		refreshStudentAndSchoolEnergy(userNumber);
	}
	
	@Override
	@Transactional
	public void addTaskProgress(Integer taskId, String userNumber) {
		
		Task task = taskMapper.getTaskById(taskId);
		if (task == null) {
			return;
		}
		
		addTaskProgress(task, userNumber);
	}
	
	@Override
	@Transactional
	public void addTaskProgress(Integer topicId, ReportType reportType, String userNumber) {
		
		Task task = taskMapper.getTaskByTopicId(topicId);
		if (task == null) {
			return;
		}
		if (task.getReportType() != null && task.getReportType() != reportType) {
			return;
		}
		
		addTaskProgress(task, userNumber);
	}
	
	private void addTaskProgress(Task task, String userNumber) {
		
		boolean insert = false;
		
		UserTask userTask = taskMapper.selectUserTaskByUserAndTask(userNumber, task.getId());
		if (userTask == null) {				
			userTask = new UserTask();
			userTask.setTaskId(task.getId());
			userTask.setUserNumber(userNumber);
			userTask.setCurrentCount(1);
			
			insert = true;
		} else {
			userTask.setCurrentCount(userTask.getCurrentCount() + 1);
		}
		
		boolean finished = task.getType() == TaskType.ASK_ANSWER
				|| userTask.getCurrentCount() >= task.getFinishCount();
		
		if (finished) {
			userTask.setFinished(Bool.Y);
			userTask.setFinishTime(new Date());			
		}
		
		if (insert) {
			taskMapper.insertUserTask(userTask);
		} else {
			taskMapper.updateUserTask(userTask);
		}
		
		if (finished) {
			refreshStudentAndSchoolEnergy(userNumber);
		}		
	}
	
	private void refreshStudentAndSchoolEnergy(String userNumber) {		
		// 学生能量值
		StudentEnergy studentEnergy = energyMapper.selectStudentEnergyByUserNumber(userNumber);
		if (studentEnergy == null) {
			studentEnergy = new StudentEnergy();
			studentEnergy.setUserNumber(userNumber);
			studentEnergy.setUpdateTime(new Date());
			studentEnergy.setEnergy(0);
			
			energyMapper.insertStudentEnergy(studentEnergy);
		}
		
		energyMapper.updateStudentEnergy(userNumber);
		
		Student student = studentMapper.selectStudentByUserNumber(userNumber);
		String schoolName = student.getSchool();
		
		// 学校能量值
		SchoolEnergy schoolEnergy = energyMapper.selectSchoolEnergyBySchoolName(schoolName);
		if (schoolEnergy == null) {
			schoolEnergy = new SchoolEnergy();
			schoolEnergy.setSchoolName(schoolName);
			schoolEnergy.setUpdateTime(new Date());
			schoolEnergy.setEnergy(0);
			schoolEnergy.setEnergyRank(0);
			
			energyMapper.insertSchoolEnergy(schoolEnergy);
		}
		
		energyMapper.updateSchoolEnergy(schoolName);
		
		// 重新生成排名
		energyMapper.updateSchoolEnergyRank();
	}
}
