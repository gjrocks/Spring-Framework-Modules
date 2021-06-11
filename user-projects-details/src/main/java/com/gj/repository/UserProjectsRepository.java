package com.gj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gj.model.UserProject;


public interface UserProjectsRepository extends JpaRepository<UserProject, Long>{

}
