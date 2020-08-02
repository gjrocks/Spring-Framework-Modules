package com.gj.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gj.exception.UserException;
import com.gj.model.Response;
import com.gj.model.User;
import com.gj.service.UserService;
import com.gj.util.PayloadValidator;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/ping", method=RequestMethod.GET)
	public ResponseEntity<String> ping(){
    	logger.debug("Returning from the ping from container :" +System.getenv("HOSTNAME") + "::" +System.getProperty("HOSTNAME"));
		return new ResponseEntity<String>("<H1>Service is running at "+System.getenv("HOSTNAME") + "::" +System.getProperty("HOSTNAME")+"</H1>", HttpStatus.OK);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser(){
    	logger.debug("Returning all the User");
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}
	
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) throws UserException{
    	logger.debug("User id to return " + id);
    	User toDo = userService.getUserById(id);
    	if (toDo == null || toDo.getId() <= 0){
            throw new UserException("User doesnt exist");
    	}
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeUserById(@PathVariable("id") long id) throws UserException{
    	logger.debug("User id to remove " + id);
    	User toDo = userService.getUserById(id);
    	if (toDo == null || toDo.getId() <= 0){
            throw new UserException("User to delete doesnt exist");
    	}
		userService.removeUser(toDo);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "User has been deleted"), HttpStatus.OK);
	}
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
   	public ResponseEntity<User> saveUser(@RequestBody User payload) throws UserException{
    	logger.debug("Payload to save " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new UserException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<User>(userService.saveUser(payload), HttpStatus.OK);
   	}
    
    @RequestMapping(value = "/user", method = RequestMethod.PATCH)
   	public ResponseEntity<User>  updateUser(@RequestBody User payload) throws UserException{
    	logger.debug("Payload to update " + payload);
    	User toDo = userService.getUserById(payload.getId());
    	if (toDo == null || toDo.getId() <= 0){
            throw new UserException("User to update doesnt exist");
    	}
		return new ResponseEntity<User>(userService.saveUser(payload), HttpStatus.OK);
   	}
	
}
