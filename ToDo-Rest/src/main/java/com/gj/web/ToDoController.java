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

import com.gj.exception.ToDoException;
import com.gj.model.Response;
import com.gj.model.ToDo;
import com.gj.service.ToDoService;
import com.gj.util.PayloadValidator;

@RestController
public class ToDoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@Autowired
	private ToDoService toDoService;
	
	@RequestMapping(value="/ping", method=RequestMethod.GET)
	public ResponseEntity<String> ping(){
    	
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	@RequestMapping(value="/todo", method=RequestMethod.GET)
	public ResponseEntity<List<ToDo>> getAllToDo(){
    	logger.info("Returning all the ToDo´s");
		return new ResponseEntity<List<ToDo>>(toDoService.getAllToDo(), HttpStatus.OK);
	}
	
    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public ResponseEntity<ToDo> getToDoById(@PathVariable("id") long id) throws ToDoException{
    	logger.info("ToDo id to return " + id);
    	ToDo toDo = toDoService.getToDoById(id);
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo doesn´t exist");
    	}
		return new ResponseEntity<ToDo>(toDoService.getToDoById(id), HttpStatus.OK);
	}

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeToDoById(@PathVariable("id") long id) throws ToDoException{
    	logger.info("ToDo id to remove gj " + id);
    	ToDo toDo = toDoService.getToDoById(id);
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo to delete doesn´t exist");
    	}
		toDoService.removeToDo(toDo);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "ToDo has been deleted"), HttpStatus.OK);
	}
    
    @RequestMapping(value = "/todo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<ToDo> saveToDo(@RequestBody ToDo payload) throws ToDoException{
    	logger.info("Payload to save gj " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new ToDoException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<ToDo>(toDoService.saveToDo(payload), HttpStatus.OK);
   	}
    @RequestMapping(value = "/todox", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
   	public ResponseEntity<ToDo> saveToDo2(@RequestBody ToDo payload) throws ToDoException{
    	logger.info("Payload to save xml gj " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new ToDoException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<ToDo>(toDoService.saveToDo(payload), HttpStatus.OK);
   	}
    
   // @PostMapping(value = "/zoot", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @RequestMapping(value = "/zoot", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> handleMessage(@RequestBody String xmlOrJson, HttpServletRequest request) throws Exception {
    	System.out.println(xmlOrJson);
    	 return new ResponseEntity<String>(xmlOrJson, HttpStatus.OK);
    }
    @RequestMapping(value = "/todo", method = RequestMethod.PATCH)
   	public ResponseEntity<ToDo>  updateToDo(@RequestBody ToDo payload) throws ToDoException{
    	logger.info("Payload to update " + payload);
    	ToDo toDo = toDoService.getToDoById(payload.getId());
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo to update doesn´t exist");
    	}
		return new ResponseEntity<ToDo>(toDoService.saveToDo(payload), HttpStatus.OK);
   	}
	
}
