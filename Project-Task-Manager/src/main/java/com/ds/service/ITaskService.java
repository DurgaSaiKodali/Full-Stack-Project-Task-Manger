package com.ds.service;

import java.util.List;

import com.ds.dto.TaskDto;

public interface ITaskService {
	List<TaskDto> getAllTask();
	TaskDto getTaskById(Long id);
	TaskDto createTask(TaskDto dto);
	TaskDto updateTask(Long id,TaskDto dto);
	String deleteTask(Long id);
	

}
