package com.qa.tdl.persistance.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl.persistance.domain.TaskDomain;

@Repository
public interface TaskRepo extends JpaRepository<TaskDomain, Long> {

}
