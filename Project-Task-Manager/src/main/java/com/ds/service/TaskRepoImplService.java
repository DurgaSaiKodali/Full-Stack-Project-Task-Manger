package com.ds.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.dto.TaskDto;
import com.ds.entity.Task;
import com.ds.exceptionAdvice.ResourceNotFoundException;
import com.ds.repository.TaskRepository;


@Service
public class TaskRepoImplService implements ITaskService {
	
	@Autowired
	private TaskRepository repo;

	@Override
	public List<TaskDto> getAllTask() {
	
		return repo.findAll().stream().map(this :: mapToDto).collect(Collectors.toList());
	}

	@Override
	public TaskDto getTaskById(Long id) {
		Task task=repo.findById(id)
				.orElseThrow(()->new  ResourceNotFoundException("Task not found"));
		return mapToDto(task);
	}

	@Override
	public TaskDto createTask(TaskDto dto) {
		Task task=mapToEntity(dto);
		Task savedTask=repo.save(task);
		return mapToDto(savedTask);
	}

	@Override
	public TaskDto updateTask(Long id, TaskDto dto) {
		Task task=repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Task Not Found"));
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		Task updatedTask=repo.save(task);
		
		return mapToDto(updatedTask);
	}

	@Override
	public String deleteTask(Long id) {
		if(!repo.existsById(id)) {
			throw new ResourceNotFoundException("Task Not Found For Deletion ");
		}
		repo.deleteById(id);	
		return id+" Task Deleted";
	}
	
	//mapping methods
	private TaskDto mapToDto(Task task) {
		TaskDto dto=new TaskDto();
		dto.setId(task.getId());
		dto.setTitle(task.getTitle());
		dto.setDescription(task.getDescription());
		return dto;
	}
	
	private Task mapToEntity(TaskDto dto) {
		Task task=new Task();
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		return task;
	}

}
