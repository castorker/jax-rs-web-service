package com.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.webservice.model.Quibble;
import com.webservice.repository.QuibbleRepository;
import com.webservice.repository.QuibbleRepositoryStub;

@Path("quibbles")	// http://localhost:8080/quibble-service/webapi/quibbles
public class QuibbleResource {

	private QuibbleRepository quibbleRepository = new QuibbleRepositoryStub();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Quibble> getAll() { 
		return quibbleRepository.GetAll();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{quibbleId}")	// http://localhost:8080/quibble-service/webapi/quibbles/1
	public Quibble getById(@PathParam ("quibbleId") int quibbleId) {
		
		// System.out.println("Getting quibble Id: " + quibbleId);
		
		return quibbleRepository.GetById(quibbleId);
	}
	
}
