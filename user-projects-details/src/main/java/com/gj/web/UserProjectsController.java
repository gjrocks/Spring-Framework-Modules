package com.gj.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gj.exception.UserProjectsException;
import com.gj.model.Response;
import com.gj.model.UserProject;
import com.gj.service.UserProjectsService;
import com.gj.util.PayloadValidator;

@RestController
public class UserProjectsController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProjectsController.class);

	@Autowired
	private UserProjectsService userProjectsService;
	
	@RequestMapping(value="/ping", method=RequestMethod.GET)
	public ResponseEntity<String> ping(){
    	
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	@RequestMapping(value="/userproject", method=RequestMethod.GET)
	public ResponseEntity<List<UserProject>> getAllUserProject(){
    	logger.info("Returning all the UserProject´s");
		return new ResponseEntity<List<UserProject>>(userProjectsService.getAllUserProject(), HttpStatus.OK);
	}
	
    @RequestMapping(value = "/userproject/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserProject> getUserProjectById(@PathVariable("id") long id) throws UserProjectsException{
    	logger.info("UserProject id to return " + id);
    	UserProject userProject = userProjectsService.getUserProjectById(id);
    	if (userProject == null || userProject.getId() <= 0){
            throw new UserProjectsException("userProject doesn´t exist");
    	}
		return new ResponseEntity<UserProject>(userProjectsService.getUserProjectById(id), HttpStatus.OK);
	}

    @RequestMapping(value = "/userproject/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeUserProjectById(@PathVariable("id") long id) throws UserProjectsException{
    	logger.info("UserProject id to remove gj " + id);
    	UserProject userProject = userProjectsService.getUserProjectById(id);
    	if (userProject == null || userProject.getId() <= 0){
            throw new UserProjectsException("userProject to delete doesn´t exist");
    	}
		userProjectsService.removeUserProject(userProject);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "UserProject has been deleted"), HttpStatus.OK);
	}
    
    @RequestMapping(value = "/userproject", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<UserProject> saveUserProject(@RequestBody UserProject payload) throws UserProjectsException{
    	logger.info("Payload to save gj " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new UserProjectsException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<UserProject>(userProjectsService.saveUserProject(payload), HttpStatus.OK);
   	}
    @RequestMapping(value = "/todox", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
   	public ResponseEntity<UserProject> saveUserProject2(@RequestBody UserProject payload) throws UserProjectsException{
    	logger.info("Payload to save xml gj " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new UserProjectsException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<UserProject>(userProjectsService.saveUserProject(payload), HttpStatus.OK);
   	}
    
   // @PostMapping(value = "/zoot", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @RequestMapping(value = "/zoot", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> handleMessage(@RequestBody String xmlOrJson, HttpServletRequest request) throws Exception {
    	System.out.println(xmlOrJson);
    	 return new ResponseEntity<String>(xmlOrJson, HttpStatus.OK);
    }
    @RequestMapping(value = "/userproject", method = RequestMethod.PATCH)
   	public ResponseEntity<UserProject>  updateUserProject(@RequestBody UserProject payload) throws UserProjectsException{
    	logger.info("Payload to update " + payload);
    	UserProject userProject = userProjectsService.getUserProjectById(payload.getId());
    	if (userProject == null || userProject.getId() <= 0){
            throw new UserProjectsException("userProject to update doesn´t exist");
    	}
		return new ResponseEntity<UserProject>(userProjectsService.saveUserProject(payload), HttpStatus.OK);
   	}
	
}
