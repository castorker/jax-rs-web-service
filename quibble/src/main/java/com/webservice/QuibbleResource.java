package com.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.webservice.model.Quibble;
import com.webservice.repository.QuibbleRepository;
import com.webservice.repository.QuibbleRepositoryStub;

@Path("quibbles")	// http://localhost:8080/quibble-service/webapi/quibbles
public class QuibbleResource {

	private QuibbleRepository quibbleRepository = new QuibbleRepositoryStub();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Quibble> getAll() { 
		return quibbleRepository.GetAll();
	}
}