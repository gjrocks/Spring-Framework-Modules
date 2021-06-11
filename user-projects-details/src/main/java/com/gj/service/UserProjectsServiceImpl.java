package com.gj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gj.model.UserProject;
import com.gj.repository.UserProjectsRepository;

@Service("toDoService")
@Transactional
public class UserProjectsServiceImpl implements UserProjectsService{

	@Autowired
	private UserProjectsRepository userProjectsRepository;
	
	@Override
	public List<UserProject> getAllUserProject() {
		return userProjectsRepository.findAll();
	}

	@Override
	public UserProject getUserProjectById(long id) {
		return userProjectsRepository.findOne(id);
	}

	@Override
	public UserProject saveUserProject(UserProject todo) {
		return userProjectsRepository.save(todo);
	}

	@Override
	public void removeUserProject(UserProject todo) {
		userProjectsRepository.delete(todo);
	}
	

}
