package com.gj.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gj.exception.DuplicatePersonException;
import com.gj.exception.ServiceFault;
import com.gj.exception.ServiceFaultDetails;
import com.gj.model.Person;
import com.gj.model.PersonDeptList;
import com.gj.model.PersonList;
import com.gj.service.IPersonManager;
import com.gj.ws.filter.Logged;
import com.gj.ws.filter.LoggingFilter;


@Path("/person")
//do following so that filter is applied for all methods in the controller, remove it if not required global, and add it to specific methods 
@Logged
@Component
public class RestFrontController {

	@Autowired
	IPersonManager personManager;
	
	@Context
	private Configuration config;

	@POST
	@Path("/add")
	@Produces({ "application/xml", "application/json" })
	@Consumes({ "application/xml", "application/json",
			"application/x-www-form-urlencoded" })
	public Response putPerson(@BeanParam Person p,@Context HttpServletRequest req ) throws DuplicatePersonException {
		//without proper exception mapper provider or programtically handling exception, the client get HTTP error code 500, and the entire stack trace.
		    try{
		    	
			p = personManager.addPerson(p);
		    }catch(DuplicatePersonException e){
		    	/*Status s=Status.PRECONDITION_FAILED;
		    	throw new WebApplicationException(s);
		    	 */	
		    	/*Response r= Response.status(209).build();
		    	r.serverError();
		        throw new WebApplicationException(r);*/
		    	//the following rethrow will be managed by configured exception mapper.
		    	throw e;
		    }
		return Response.ok("Person added ").build();
	}

	@GET
	@Path("/getPerson/{id}")
	@Produces({ "application/xml", "application/json" })
	@Consumes({ "application/xml", "application/json" })
	public Response getPerson(@PathParam("id") int id) throws ServiceFault {
		Person p = null;

		try {
			p = personManager.getPersonByID(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Response rs = null;
		if (p != null) {
			rs = Response.ok(p).build();
		} else {

			System.out.println("Person not found");
			// rs=Response.ok("Person not found").build();
			ServiceFaultDetails[] details = new ServiceFaultDetails[1];
			ServiceFaultDetails detail = new ServiceFaultDetails(
					"Person not found with id :" + id, 101);
			details[0] = detail;

			throwServiceFault(new ServiceFault(details));
		}
		return rs;
	}

	public IPersonManager getPersonManager() {
		return personManager;
	}

	public void setPersonManager(IPersonManager personManager) {
		this.personManager = personManager;
	}

	public void throwServiceFault(ServiceFault fault) throws ServiceFault {
		ResponseBuilder builder = Response
				.status(Response.Status.NOT_ACCEPTABLE);
		builder.type("application/json");
		builder.entity(fault.getFaults());
		throw new WebApplicationException(builder.build());
	}
	
	
	@GET
	//@Logged /* now the logging filter is mapped to this method only*/
	@Path("/getAllPersons")
	@Produces({ "application/xml", "application/json" })
	@Consumes({ "application/xml", "application/json" })
	public Response getAllPersons() throws ServiceFault {
		List<Person> p = null;
		PersonList personList=new PersonList();
		try {
			p = personManager.getAllPersons();
			personList.setPersonList(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Response rs = null;
		if (p != null) {
			rs = Response.ok(personList).build();
		} else {

			System.out.println("No Person found-returning empty list");
			// rs=Response.ok("Person not found").build();
			ServiceFaultDetails[] details = new ServiceFaultDetails[1];
			ServiceFaultDetails detail = new ServiceFaultDetails(
					"No Person found-returning empty list", 102);
			details[0] = detail;

			throwServiceFault(new ServiceFault(details));
		}
		return rs;
	}
	
	@GET
	@Path("/getAllPersonDeptLinks")
	@Produces({ "application/xml", "application/json" })
	@Consumes({ "application/xml", "application/json" })
	public Response getAllPersonDeptLinks() throws ServiceFault {
		PersonDeptList list=null;
		try {
			list = personManager.getPersonDeptList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		Response rs = null;
		if (list != null) {
			rs = Response.ok(list).build();
		} else {

			System.out.println("No Person dept link found-returning empty ");
			// rs=Response.ok("Person not found").build();
			ServiceFaultDetails[] details = new ServiceFaultDetails[1];
			ServiceFaultDetails detail = new ServiceFaultDetails(
					"No Person dept link found-returning empty", 103);
			details[0] = detail;

			throwServiceFault(new ServiceFault(details));
		}
		return rs;
	}

	
	@GET
	@Path("/testjaxrscontext")
	@Produces({ "application/xml", "application/json" })
	@Consumes({ "application/xml", "application/json" })
	public Response viewJaxRSContexts(@Context UriInfo infos,@Context HttpServletRequest req,@Context Configuration config,@Context SecurityContext secCtx,@Context HttpHeaders headers,@Context ServletContext servletContext, @Context ResourceContext resourceCtx,@Context ServletConfig servletConfig) throws ServiceFault {
		
		Map<String,String> contextInfoMap=new HashMap<String,String>();
		StringBuilder queryParamNames=new StringBuilder();
		StringBuilder headerNames=new StringBuilder();
		try {
			
			for (String queryParameterName:infos.getQueryParameters().keySet()){
				
				queryParamNames.append(queryParameterName);
				queryParamNames.append("::");
				
			}
			contextInfoMap.put("QueryParamNames", queryParamNames.toString());
			
			//lets add headers 
			
			for(String header:headers.getRequestHeaders().keySet()){
				headerNames.append(header);
				headerNames.append("::");
			}
			contextInfoMap.put("HeaderNames", headerNames.toString());
		
		    //lets check if loggingfilter ( provider) is registered using Configuration
			String key=LoggingFilter.class.getName();
			Boolean flag=false;
			if( config.isRegistered(LoggingFilter.class)){
				flag=true;
			}
			
			contextInfoMap.put(key, flag.toString());
			
			//similarly you can use injected httpservletrequest, servletconfig, servletcontext etc.
		} catch (Exception e) {
			e.printStackTrace();
		}

		Response rs = null;
		
			rs = Response.ok(contextInfoMap).build();
		
		return rs;
	}
	
	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	
}
