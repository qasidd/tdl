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
import org.springframework.web.bind.annotation.RequestParam;
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
		return new ResponseEntity<>(this.service.create(model), HttpStatus.CREATED);
	}
	
	// PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") int id, @RequestBody TaskDomain model) {
		return new ResponseEntity<>(this.service.update(id, model), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{id}/add-assignee")
	public ResponseEntity<TaskDTO> addAssignee(@RequestParam("assignee_id") int assigneeId, @PathVariable int id) {
		return new ResponseEntity<>(this.service.addAssignee(id, assigneeId), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{id}/remove-assignee")
	public ResponseEntity<TaskDTO> removeAssignee(@RequestParam("assignee_id") int assigneeId, @PathVariable int id) {
		return new ResponseEntity<>(this.service.removeAssignee(id, assigneeId), HttpStatus.ACCEPTED);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeTask(@PathVariable int id) {
		return new ResponseEntity<>(this.service.delete(id) ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
