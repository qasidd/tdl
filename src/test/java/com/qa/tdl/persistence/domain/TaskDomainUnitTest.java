package com.qa.tdl.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskDomainUnitTest {

	private TaskDomain task;
	private AssigneeDomain jane;

	@BeforeEach
	public void setUp() {
		Set<AssigneeDomain> assignees = new HashSet<>();
		jane = new AssigneeDomain(1L, "Jane");
		assignees.add(jane);
		
		task = new TaskDomain(1L, "Do laundry", false, Timestamp.valueOf("2021-02-05 08:00:00"), assignees);
	}

	@Test
	public void settersTest() {
		assertNotNull(task.getId());
		assertNotNull(task.getTitle());
		assertNotNull(task.getCompleted());
		assertNotNull(task.getDateTimeSet());
		assertNotNull(task.getAssignees());
		
		task.setId(null);
		assertNull(task.getId());
		task.setTitle(null);
		assertNull(task.getTitle());
		task.setCompleted(null);
		assertNull(task.getCompleted());
		task.setDateTimeSet(null);
		assertNull(task.getDateTimeSet());
		task.setAssignees(null);
		assertNull(task.getAssignees());
	}

	@Test
	public void createTaskDomainWithId() {
		assertEquals(1L, task.getId(), 0);
		assertEquals("Do laundry", task.getTitle());
		assertEquals(false, task.getCompleted());
		assertEquals(Timestamp.valueOf("2021-02-05 08:00:00"), task.getDateTimeSet());
		Assertions.assertThat(Set.of(new AssigneeDomain(1L, "Jane"))).usingRecursiveComparison().isEqualTo(task.getAssignees());
	}
	
	@Test
	public void addOneAssignee() {
		Set<AssigneeDomain> compareSet = new HashSet<>();
		compareSet.add(new AssigneeDomain(1L, "Jane"));
		compareSet.add(new AssigneeDomain(2L, "Bob"));
		
		task.addAssignee(new AssigneeDomain(2L, "Bob"));
		Assertions.assertThat(task.getAssignees()).usingRecursiveComparison().isEqualTo(compareSet);
	}
	
	@Test
	public void removeOneAssignee() {
		task.removeAssignee(jane);
		Assertions.assertThat(task.getAssignees()).usingRecursiveComparison().isEqualTo(Set.of());
	}
	
	@Test
	public void emptyAssignees() {
		task.emptyAssignees();
		Assertions.assertThat(task.getAssignees()).usingRecursiveComparison().isEqualTo(Set.of());
	}


	@Test
	public void constructorWithoutId() {
		TaskDomain task = new TaskDomain("Do laundry", false, Timestamp.valueOf("2021-02-05 08:00:00"), Set.of(jane));
		assertNull(task.getId());
		assertNotNull(task.getTitle());
		assertNotNull(task.getCompleted());
		assertNotNull(task.getDateTimeSet());
		assertNotNull(task.getAssignees());
	}
	
	@Test
	public void constructorWithoutAssignees() {
		TaskDomain task = new TaskDomain(1L, "Do laundry", false, Timestamp.valueOf("2021-02-05 08:00:00"));
		assertNotNull(task.getId());
		assertNotNull(task.getTitle());
		assertNotNull(task.getCompleted());
		assertNotNull(task.getDateTimeSet());
		assertNotNull(task.getAssignees());
	}
	
	@Test
	public void constructorWithoutIdAndAssignees() {
		TaskDomain task = new TaskDomain("Do laundry", false, Timestamp.valueOf("2021-02-05 08:00:00"));
		assertNull(task.getId());
		assertNotNull(task.getTitle());
		assertNotNull(task.getCompleted());
		assertNotNull(task.getDateTimeSet());
		assertNotNull(task.getAssignees());
	}
	
	@Test
	public void emptyConstructor() {
		TaskDomain task = new TaskDomain();
		assertNull(task.getId());
		assertNull(task.getTitle());
		assertNull(task.getCompleted());
		assertNull(task.getDateTimeSet());
		assertNull(task.getAssignees());
	}
	
	@Test
	public void toStringTest() {
		String toString = "TaskDomain [id=1, title=Do laundry, completed=false, dateTimeSet=2021-02-05 08:00:00.0, assignees=[AssigneeDomain [id=1, name=Jane]]]";
		assertEquals(toString, task.toString());
	}

}
