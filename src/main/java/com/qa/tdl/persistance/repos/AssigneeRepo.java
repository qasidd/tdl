package com.qa.tdl.persistance.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl.persistance.domain.AssigneeDomain;

@Repository
public interface AssigneeRepo extends JpaRepository<AssigneeDomain, Long> {

}
