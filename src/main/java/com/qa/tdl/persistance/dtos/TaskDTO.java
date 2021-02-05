package com.qa.tdl.persistance.dtos;

import java.sql.Timestamp;

public class TaskDTO {

	private Long id;
	private String title;
	private Boolean completed;
	private Timestamp dateTimeSet;

	public TaskDTO() {
		super();
	}

	public TaskDTO(Long id, String title, Boolean completed, Timestamp dateTimeSet) {
		super();
		this.id = id;
		this.title = title;
		this.completed = completed;
		this.dateTimeSet = dateTimeSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Timestamp getDateTimeSet() {
		return dateTimeSet;
	}

	public void setDateTimeSet(Timestamp dateTimeSet) {
		this.dateTimeSet = dateTimeSet;
	}

	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", title=" + title + ", completed=" + completed + ", dateTimeSet=" + dateTimeSet
				+ "]";
	}
	
}