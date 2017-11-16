package com.webservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.webservice.model.Quibble;

public class QuibbleRepositoryStub implements QuibbleRepository {

	/* (non-Javadoc)
	 * @see com.webservice.repository.QuibbleRepository#GetAll()
	 */
	@Override
	public List<Quibble> GetAll () {
		
		List<Quibble> quibbles = new ArrayList<Quibble>();
		
		Quibble quibble1 = new Quibble();
		quibble1.setId(1);
		quibble1.setText("Why do Java Programmers wear glasses? Because they don't see sharp.");
		quibble1.setCategory("Technology");
		
		quibbles.add(quibble1);
		
		Quibble quibble2 = new Quibble();
		quibble2.setId(2);
		quibble2.setText("Old programmers never die, they just can't C as well.");
		quibble2.setCategory("Technology");
		
		quibbles.add(quibble2);
		
		return quibbles;
	}
}
