package com.qa.tdl.persistance.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaskEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private Boolean completed;
	
	@Column(nullable = false)
	private Timestamp dateTimeSet;

	public TaskEntity() {
		super();
	}

	public TaskEntity(Long id, String title, Boolean completed, Timestamp dateTimeSet) {
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
		return "TaskEntity [id=" + id + ", title=" + title + ", completed=" + completed + ", dateTimeSet=" + dateTimeSet
				+ "]";
	}

}
