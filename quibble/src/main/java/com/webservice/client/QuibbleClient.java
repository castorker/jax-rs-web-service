package com.webservice.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webservice.model.Quibble;

public class QuibbleClient {

	private Client client;

	public QuibbleClient() {
		client = ClientBuilder.newClient();
	}

	public Quibble getById(int id) {

		// http://localhost:8080/quibble-rs-webservice/webapi/quibbles/1
		WebTarget target = client.target("http://localhost:8080/quibble-rs-webservice/webapi/");

		// String response = target.path("quibbles/" + id).request().get(String.class);
		// String response = target.path("quibbles/" + id).request(MediaType.APPLICATION_JSON).get(String.class);
		// Quibble response = target.path("quibbles/" + id).request().get(Quibble.class);
		// Quibble response = target.path("quibbles/" + id).request(MediaType.APPLICATION_JSON).get(Quibble.class);

		Response response = target
				.path("quibbles/" + id).request(MediaType.APPLICATION_JSON)
				.get(Response.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}

		return response.readEntity(Quibble.class);
	}

	public List<Quibble> getAll() {

		// http://localhost:8080/quibble-rs-webservice/webapi/quibbles
		WebTarget target = client.target("http://localhost:8080/quibble-rs-webservice/webapi/");

		List<Quibble> response = target
				.path("quibbles")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Quibble>>() {});

		return response;
	}

	public Quibble create(Quibble quibble) {

		// http://localhost:8080/quibble-rs-webservice/webapi/quibbles/quibble
		WebTarget target = client.target("http://localhost:8080/quibble-rs-webservice/webapi/");

		Response response = target
				.path("quibbles/quibble")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(quibble, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}

		return response.readEntity(Quibble.class);
	}

	public Quibble update(Quibble quibble) {

		// http://localhost:8080/quibble-rs-webservice/webapi/quibbles/14
		WebTarget target = client.target("http://localhost:8080/quibble-rs-webservice/webapi/");

		Response response = target
				.path("quibbles/" + quibble.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(quibble, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}

		return response.readEntity(Quibble.class);
	}

	public void delete(int id) {

		// http://localhost:8080/quibble-rs-webservice/webapi/quibbles/14
		WebTarget target = client.target("http://localhost:8080/quibble-rs-webservice/webapi/");

		Response response = target
				.path("quibbles/" + id)
				.request(MediaType.APPLICATION_JSON)
				.delete();

		if (response.getStatus() != 200 && response.getStatus() != 404) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
	}
}
