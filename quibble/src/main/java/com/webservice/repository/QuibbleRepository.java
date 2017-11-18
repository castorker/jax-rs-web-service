package com.webservice.repository;

import java.util.List;

import com.webservice.model.Quibble;

public interface QuibbleRepository {

	List<Quibble> GetAll();

	Quibble GetById(int quibbleId);

	Quibble create(Quibble quibble);

}