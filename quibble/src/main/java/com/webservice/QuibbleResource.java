package com.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.webservice.model.Author;
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

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{quibbleId}/author")	// http://localhost:8080/quibble-service/webapi/quibbles/1/author
	public Author getQuibbleAuthor(@PathParam ("quibbleId") int quibbleId) {
		// Quibble quibble = quibbleRepository.GetById(quibbleId);
		// Author author = quibble.getAuthor();
		// return author;
		return quibbleRepository.GetById(quibbleId).getAuthor();
	}

	@POST
	@Path("quibble")	// http://localhost:8080/quibble-service/webapi/quibbles/quibble
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Quibble createQuibble(Quibble quibble) {
		// System.out.println(quibble.getText());
		// System.out.println(quibble.getCategory());

		return quibbleRepository.create(quibble);
	}
	
	@POST
	@Path("quibble")	// http://localhost:8080/quibble-service/webapi/quibbles/quibble
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Quibble createQuibbleParams(MultivaluedMap<String, String> formParams) {
		// System.out.println(formParams.getFirst("text"));
		// System.out.println(formParams.getFirst("category"));

		Quibble quibble = new Quibble();
		quibble.setText(formParams.getFirst("text"));
		quibble.setCategory(formParams.getFirst("category"));

		return quibbleRepository.create(quibble);
	}

	@PUT
	@Path("{quibbleId}")	// http://localhost:8080/quibble-service/webapi/quibbles/1
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Quibble updateQuibble(Quibble quibble) {
		// System.out.println(quibble.getId());
		// System.out.println(quibble.getText());
		// System.out.println(quibble.getCategory());

		return quibbleRepository.update(quibble);
	 }
}
