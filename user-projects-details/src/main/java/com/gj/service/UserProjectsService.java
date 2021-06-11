package com.gj.service;

import java.util.List;

import com.gj.model.UserProject;

public interface UserProjectsService {
	public List<UserProject> getAllUserProject();
	public UserProject getUserProjectById(long id);
	public UserProject saveUserProject(UserProject todo);
	public void removeUserProject(UserProject todo);
}
