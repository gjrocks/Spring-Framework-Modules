package com.gj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gj.model.ToDo;
import com.gj.repository.ToDoRepository;

@Service("toDoService")
@Transactional
public class ToDoServiceImpl implements ToDoService{

	@Autowired
	private ToDoRepository toDoRepository;
	
	@Override
	public List<ToDo> getAllToDo() {
		return toDoRepository.findAll();
	}

	@Override
	public ToDo getToDoById(long id) {
		return toDoRepository.findOne(id);
	}

	@Override
	public ToDo saveToDo(ToDo todo) {
		return toDoRepository.save(todo);
	}

	@Override
	public void removeToDo(ToDo todo) {
		toDoRepository.delete(todo);
	}
	

}
