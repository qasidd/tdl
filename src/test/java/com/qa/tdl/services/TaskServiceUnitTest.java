package com.qa.tdl.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdl.persistance.domain.TaskDomain;
import com.qa.tdl.persistance.dtos.TaskDTO;
import com.qa.tdl.persistance.repos.TaskRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaskServiceUnitTest {
	
	@MockBean
	private TaskRepo repo;
	
	// only testing service - injecting this will complicate and defeat the purpose
	@MockBean
	private ModelMapper mapper;
	
	@Autowired
	public TaskService service;

	@Test
	public void createTest() {
		TaskDomain TEST_TASK = new TaskDomain(5L, "Food shopping", false, Timestamp.from(Instant.now()));
		TaskDTO TEST_DTO = new TaskDTO(TEST_TASK.getId(), TEST_TASK.getTitle(), TEST_TASK.getCompleted(), TEST_TASK.getDateTimeSet());
		
		Mockito.when(this.repo.save(Mockito.any(TaskDomain.class))).thenReturn(TEST_TASK);
		// wouldn't mocking this mean line 40 would always returns TEST_DTO?
		Mockito.when(this.mapper.map(TEST_TASK, TaskDTO.class)).thenReturn(TEST_DTO);
		
		TaskDTO result = this.service.create(TEST_TASK);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result)
			.usingRecursiveComparison()
			.isEqualTo(TEST_DTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(Mockito.any(TaskDomain.class));
	}
	
	@Test
	public void readTaskTest() {
		Long id = 1L;
		TaskDomain TEST_TASK = new TaskDomain(id, "Do laundry", false, Timestamp.valueOf("2021-02-05 08:00:00"));
		Optional<TaskDomain> TEST_OPTIONAL = Optional.of(TEST_TASK);
		TaskDTO TEST_DTO = new TaskDTO(TEST_TASK.getId(), TEST_TASK.getTitle(), TEST_TASK.getCompleted(), TEST_TASK.getDateTimeSet());
		
		Mockito.when(this.repo.findById(id)).thenReturn(TEST_OPTIONAL);
		Mockito.when(this.mapper.map(TEST_TASK, TaskDTO.class)).thenReturn(TEST_DTO);
		
		TaskDTO result = this.service.readTask(id);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result)
			.usingRecursiveComparison()
			.isEqualTo(TEST_DTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.mapper, Mockito.times(1)).map(TEST_TASK, TaskDTO.class);
	}
	
	@Test
	public void readAllTest() {
		TaskDomain TEST_TASK1 = new TaskDomain(1L, "Do laundry", false, Timestamp.valueOf("2021-02-05 08:00:00"));
		TaskDomain TEST_TASK2 = new TaskDomain(2L, "Make coffee", false, Timestamp.valueOf("2021-01-21 13:00:00"));
		TaskDomain TEST_TASK3 = new TaskDomain(3L, "Take out bins", true, Timestamp.valueOf("2020-12-30 19:00:00"));
		TaskDTO TEST_DTO1 = new TaskDTO(TEST_TASK1.getId(), TEST_TASK1.getTitle(), TEST_TASK1.getCompleted(), TEST_TASK1.getDateTimeSet());
		TaskDTO TEST_DTO2 = new TaskDTO(TEST_TASK2.getId(), TEST_TASK2.getTitle(), TEST_TASK2.getCompleted(), TEST_TASK2.getDateTimeSet());
		TaskDTO TEST_DTO3 = new TaskDTO(TEST_TASK3.getId(), TEST_TASK3.getTitle(), TEST_TASK3.getCompleted(), TEST_TASK3.getDateTimeSet());
		List<TaskDomain> TASK_LIST = List.of(TEST_TASK1, TEST_TASK2, TEST_TASK3);
		List<TaskDTO> DTO_LIST = List.of(TEST_DTO1, TEST_DTO2, TEST_DTO3);
		
		Mockito.when(this.repo.findAll()).thenReturn(TASK_LIST);
		Mockito.when(this.mapper.map(TEST_TASK1, TaskDTO.class)).thenReturn(TEST_DTO1);
		Mockito.when(this.mapper.map(TEST_TASK2, TaskDTO.class)).thenReturn(TEST_DTO2);
		Mockito.when(this.mapper.map(TEST_TASK3, TaskDTO.class)).thenReturn(TEST_DTO3);
		
		List<TaskDTO> result = this.service.readAll();
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result)
			.usingRecursiveComparison()
			.isEqualTo(DTO_LIST);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void deleteTest() {
		this.service.delete(1L);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
	}
	
	@Test
	public void updateTest() {
		Long id = 1L;
		TaskDomain TEST_TASK = new TaskDomain(id, "Do laundry", false, Timestamp.valueOf("2021-02-05 08:00:00"));
		TaskDomain TEST_TASK_UPDATE = new TaskDomain(id, "Buy pens", false, Timestamp.from(Instant.now()));
		Optional<TaskDomain> TEST_OPTIONAL = Optional.of(TEST_TASK);
		TaskDTO TEST_DTO_UPDATE = new TaskDTO(TEST_TASK_UPDATE.getId(),TEST_TASK_UPDATE.getTitle(), TEST_TASK_UPDATE.getCompleted(), TEST_TASK_UPDATE.getDateTimeSet());
	
		Mockito.when(this.repo.findById(id)).thenReturn(TEST_OPTIONAL);
		Mockito.when(this.repo.save(Mockito.any(TaskDomain.class))).thenReturn(TEST_TASK_UPDATE);
		Mockito.when(this.mapper.map(TEST_TASK_UPDATE, TaskDTO.class)).thenReturn(TEST_DTO_UPDATE);
		
		TaskDTO result = this.service.update(id, TEST_TASK_UPDATE);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result)
			.usingRecursiveComparison()
			.isEqualTo(TEST_DTO_UPDATE);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(Mockito.any(TaskDomain.class));
	}
}
