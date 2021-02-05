package com.qa.tdl.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qa.tdl.persistance.domain.TaskDomain;
import com.qa.tdl.persistance.dtos.TaskDTO;
import com.qa.tdl.persistance.repos.TaskRepo;

@Service
public class TaskService {

	private TaskRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public TaskService(TaskRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private TaskDTO mapToDto(TaskDomain model) {
		return this.mapper.map(model, TaskDTO.class);
	}
	
	// POST
	public TaskDTO create(TaskDomain model) {
		return model.getDateTimeSet() == null ?
			this.mapToDto(this.repo.save
				(new TaskDomain(model.getTitle(), model.getCompleted(), Timestamp.from(Instant.now()), null)))
			: this.mapToDto(this.repo.save(model));
	}

	// GET
	public List<TaskDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public TaskDTO readTask(long id) {
		return this.mapToDto(this.repo.findById(id).orElseThrow());
	}

	// DELETE
	public boolean delete(long id) {
		try {
			this.repo.deleteById(id);
			return !(this.repo.existsById(id));
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	// PUT
	public TaskDTO update(long id, TaskDomain model) {
		Optional<TaskDomain> oc = this.repo.findById(id);
		TaskDomain existing = oc.orElseThrow();

		existing.setTitle(model.getTitle());
		existing.setCompleted(model.getCompleted());
//		existing.setDateTimeSet(model.getDateTimeSet());

		return this.mapToDto(this.repo.save(existing));
	}
	
}
