package com.ds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.dto.TaskDto;
import com.ds.service.ITaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
	@Autowired
	private ITaskService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<TaskDto>> getAllTasks(){
		List<TaskDto> getting=service.getAllTask();
		return new ResponseEntity<List<TaskDto>>(getting,HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id){
		TaskDto byId=service.getTaskById(id);
		return new ResponseEntity<TaskDto>(byId,HttpStatus.OK);
	}
	@PostMapping("/create")
	public ResponseEntity< TaskDto>createTask(@RequestBody TaskDto dto) {
		 TaskDto saved=service.createTask(dto);
		 return new ResponseEntity<TaskDto>(saved,HttpStatus.CREATED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDto> updateTask(@PathVariable Long id,@RequestBody TaskDto dto){
		TaskDto update=service.updateTask(id, dto);
		return new ResponseEntity<TaskDto>(update,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Long id) {
		String deleted=service.deleteTask(id);
		return new ResponseEntity<String>(deleted,HttpStatus.OK);
		
	}

}
