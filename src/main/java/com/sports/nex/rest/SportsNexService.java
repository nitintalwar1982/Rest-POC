package com.sports.nex.rest;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sports.nex.dao.UserDao;
import com.sports.nex.entities.Users;

@Component
@Path("/sports")
public class SportsNexService {

	@Autowired
	private UserDao UserDao;
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/registerUser")	
	@Transactional
	public Response registerUser(Users user) {
		String response = UserDao.save(user);		
		return Response.status(201)
				.entity("{'status': " + response + "}").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.TEXT_HTML })
	@Transactional
	public Response createUsersFromForm(@FormParam("emailAddress") String emailAddress,
			@FormParam("password") String password,
			@FormParam("sportsType") String sportsType,
			@FormParam("modifiedBy") String modifiedBy,
			@FormParam("userTypePid") String userTypePid) {
		
		Users user = new Users();
        user.setUserEmailAddress("nitintalwar1982@gmail.com");
        user.setUserPassword("tetst");
        user.setSportsType("Individual");
        user.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        user.setLastModifiedBy("nitin talwar");
        user.setLastModifiedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        user.setUserTypePID(0);
        UserDao.save(user);

		return Response.status(201)
				.entity("A new Users/resource has been created").build();
	}
	
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces("application/json")
	public Users findById(@PathParam("id") Long id) {
		
		Users user = new Users();
        user.setUserEmailAddress("nitintalwar1982@gmail.com");
        user.setUserPassword("tetst");
        user.setSportsType("Individual");
        user.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        user.setLastModifiedBy("nitin talwar");
        user.setLastModifiedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        user.setUserTypePID(0);
			
			/*eturn Response
					.status(404)
					.entity("The podcast with the id " + id + " does not exist")
					.build();*/
		
			return user;// Response.status(200).entity("{'username':'nitin test', 'pid':0}").build();
	}

}