package com.qa.tdl.persistence.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssigneeDTOUnitTest {
	private AssigneeDTO assignee;

	@BeforeEach
	public void setUp() {
		assignee = new AssigneeDTO(1L, "Jane");
	}

	@Test
	public void settersTest() {
		assertNotNull(assignee.getId());
		assertNotNull(assignee.getName());
		
		assignee.setId(null);
		assertNull(assignee.getId());
		assignee.setName(null);
		assertNull(assignee.getName());
	}

	@Test
	public void createAssigneeDTOWithId() {
		assertEquals(1L, assignee.getId(), 0);
		assertEquals("Jane", assignee.getName());
	}
	
	@Test
	public void emptyConstructor() {
		AssigneeDTO assignee = new AssigneeDTO();
		assertNull(assignee.getId());
		assertNull(assignee.getName());
	}
	
	@Test
	public void toStringTest() {
		String toString = "AssigneeDTO [id=1, name=Jane]";
		assertEquals(toString, assignee.toString());
	}
}
