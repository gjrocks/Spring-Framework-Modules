package com.gj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gj.model.ToDo;


public interface ToDoRepository extends JpaRepository<ToDo, Long>{

}
