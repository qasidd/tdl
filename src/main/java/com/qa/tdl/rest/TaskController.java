package com.qa.tdl.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.persistance.domain.TaskDomain;
import com.qa.tdl.persistance.dtos.TaskDTO;
import com.qa.tdl.services.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	private TaskService service;
	
	@Autowired
	public TaskController(TaskService service) {
		super();
		this.service = service;
	}
	
	// GET
	@GetMapping("/read/all")
	public ResponseEntity<List<TaskDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<TaskDTO> readTask(@PathVariable int id) {
		return ResponseEntity.ok(this.service.readTask(id));
	}
	
	// POST
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDomain model) {
		return new ResponseEntity<TaskDTO>(this.service.create(model), HttpStatus.CREATED);
	}
	
	// PUT
	@PutMapping("/update")
	public ResponseEntity<TaskDTO> updateTask(@PathParam("id") int id, @RequestBody TaskDomain model) {
		return new ResponseEntity<>(this.service.update(id, model), HttpStatus.ACCEPTED);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeTask(@PathVariable long id) {
		return new ResponseEntity<>(this.service.delete(id) ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR);
	}

}