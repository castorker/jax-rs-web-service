package com.webservice.client;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.webservice.model.Quibble;

public class QuibbleClientTest {

	@Test
	public void testGetById() {	// HTTP GET request

		QuibbleClient client = new QuibbleClient();
		Quibble quibble = client.getById(1);
		System.out.println(quibble.getId());
		assertNotNull(quibble);
	}

	@Test
	public void testGetAll() {	// HTTP GET request

		QuibbleClient client = new QuibbleClient();
		List<Quibble> quibbles = client.getAll();
		quibbles.forEach(q -> System.out.println(q.getId() + " : " + q.getText() + " : " + q.getCategory())); 
		assertNotNull(quibbles);
	}

	@Test(expected=RuntimeException.class)
	public void testGetWithBadRequest() {
		QuibbleClient client = new QuibbleClient();

		client.getById(-1);
	}

	@Test //(expected=RuntimeException.class)
	public void testGetWithNotFound() {
		QuibbleClient client = new QuibbleClient();

		client.getById(4);
	}

	@Test
	public void testCreate() {	// HTTP POST request
		QuibbleClient client = new QuibbleClient();

		Quibble quibble = new Quibble();
		quibble.setText("Why do Java Programmers wear glasses? Because they don't see sharp.");
		quibble.setCategory("Technology");

		quibble = client.create(quibble);

		assertNotNull(quibble);
	}

	@Test
	public void testUpdate() {	// HTTP PUT request
		Quibble quibble = new Quibble();

		quibble.setId(5);
		quibble.setText("Why do Java Programmers wear glasses? Because they don't see sharp.");
		quibble.setCategory("Technology");

		QuibbleClient client = new QuibbleClient();

		quibble = client.update(quibble);

		assertNotNull(quibble);
	 }

	 @Test
	 public void testDelete() {	// HTTP DELETE request
		 QuibbleClient client = new QuibbleClient();

		 client.delete(10);
	 }

	 @Test(expected=RuntimeException.class)
	 public void testDeleteWithBadRequest() {
		 QuibbleClient client = new QuibbleClient();

		 client.delete(-1);
	 }

	 @Test(expected=RuntimeException.class)
	 public void testDeleteWithNotFound() {
		 QuibbleClient client = new QuibbleClient();

		 client.delete(10);
	 }
}
