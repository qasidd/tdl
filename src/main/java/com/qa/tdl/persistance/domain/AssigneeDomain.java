package com.qa.tdl.persistance.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class AssigneeDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "assignees")
	Set<TaskDomain> tasks;

	public AssigneeDomain() {
		super();
	}

	public AssigneeDomain(Long id, String name, Set<TaskDomain> tasks) {
		super();
		this.id = id;
		this.name = name;
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<TaskDomain> getTasks() {
		return tasks;
	}
	
	public void setTasks(Set<TaskDomain> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "AssigneeDomain [id=" + id + ", name=" + name + "]";
	}

}
