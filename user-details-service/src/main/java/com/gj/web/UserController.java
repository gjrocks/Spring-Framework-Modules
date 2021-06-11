package com.gj.web;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger; 
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
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

	private String whereAbouts = getWhereAbouts();

	/*
	 * @Autowired private DiscoveryClient discoveryClient;
	 * 
	 * public Optional<URI> serviceUrl() { return
	 * discoveryClient.getInstances("user-service").stream().map(si ->
	 * si.getUri()).findFirst(); }
	 */

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<String> ping() {
		logger.debug("Returning from the ping from container :" + System.getenv("HOSTNAME") + "::"
				+ System.getProperty("HOSTNAME"));
		 //System.out.println(serviceUrl());
		return new ResponseEntity<String>("<H1>Service is running at <br>" + getWhereAbouts() + "</H1>", HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {
		logger.debug("Returning all the User");
		
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) throws UserException {
		logger.debug("User id to return " + id);
		MDC.put("id", id+"");
		User toDo = userService.getUserById(id);
		if (toDo == null || toDo.getId() <= 0) {
			throw new UserException("User doesnt exist");
		}
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeUserById(@PathVariable("id") long id) throws UserException {
		logger.debug("User id to remove " + id);
		User toDo = userService.getUserById(id);
		if (toDo == null || toDo.getId() <= 0) {
			throw new UserException("User to delete doesnt exist");
		}
		userService.removeUser(toDo);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "User has been deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User payload) throws UserException {
		logger.debug("Payload to save " + payload);
		if (!PayloadValidator.validateCreatePayload(payload)) {
			throw new UserException("Payload malformed, id must not be defined");
		}
		return new ResponseEntity<User>(userService.saveUser(payload), HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PATCH)
	public ResponseEntity<User> updateUser(@RequestBody User payload) throws UserException {
		logger.debug("Payload to update " + payload);
		User toDo = userService.getUserById(payload.getId());
		if (toDo == null || toDo.getId() <= 0) {
			throw new UserException("User to update doesnt exist");
		}
		return new ResponseEntity<User>(userService.saveUser(payload), HttpStatus.OK);
	}

	private static String getWhereAbouts() {
		StringBuilder whereAbouts = new StringBuilder();
		if (System.getenv("HOSTNAME") != null) {
			whereAbouts.append("Docker Container id : ");
			whereAbouts.append(System.getenv("HOSTNAME"));
		}
		whereAbouts.append("-------------------------------------<br> ");
		whereAbouts.append("Instance ip  : ");
		try {
			Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
			while (n.hasMoreElements()) {
				NetworkInterface e = n.nextElement();
				// System.out.println("Interface: " + e.getName());
				Enumeration<InetAddress> a = e.getInetAddresses();
				for (; a.hasMoreElements();) {
					InetAddress addr = a.nextElement();
					whereAbouts.append(addr.getHostAddress());
					whereAbouts.append("<br>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return whereAbouts.toString();
	}
}
