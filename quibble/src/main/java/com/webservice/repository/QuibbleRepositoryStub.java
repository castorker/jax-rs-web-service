package com.webservice.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.webservice.model.Author;
import com.webservice.model.Quibble;

@SuppressWarnings("serial")
public class QuibbleRepositoryStub implements QuibbleRepository, Serializable {

	/* (non-Javadoc)
	 * @see com.webservice.repository.QuibbleRepository#GetAll()
	 */
	
	private List<Quibble> quibbles = new ArrayList<Quibble>();

	public List<Quibble> getQuibbles() {
		return quibbles;
	}

	public void setQuibbles(List<Quibble> quibbles) {
		this.quibbles = quibbles;
	}

	@SuppressWarnings("unchecked")
	public QuibbleRepositoryStub() {
		super();

		File file = new File("./quibbles.dat");

		if (file.exists() && file.isFile()) {
			// System.out.println("File exists, and it is a file.");

			// Deserialize object
			try {
				FileInputStream stream = new FileInputStream("./quibbles.dat");
				ObjectInputStream in = new ObjectInputStream(stream);
				//this.quibbles = (List<Quibble>) in.readObject();
				this.setQuibbles((List<Quibble>) in.readObject());
				in.close();
				stream.close();
			} catch (IOException e) {
				System.out.println(e.toString());
				// e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println(e.toString());
				// e.printStackTrace();
			}
		}
		else {
			// System.out.println("File does no exist.");
            Init();
		}
	}
	
	private void Init()
    {
        Quibble quibble = new Quibble();
		quibble.setId(1);
		quibble.setText("Why do Java Programmers wear glasses? Because they don't see sharp.");
		quibble.setCategory("Technology");

		Author author = new Author();
		author.setId(1);
		author.setName("Unknown");
		author.setCountry("Unknown");

		quibble.setAuthor(author);

		this.getQuibbles().add(quibble);
        Save();
    }

	private void Save() {

		// Serialize object
		try {
			FileOutputStream stream = new FileOutputStream("./quibbles.dat");
			ObjectOutputStream out = new ObjectOutputStream(stream);
			out.writeObject(this.getQuibbles());
			out.close();
			stream.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			// e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.toString());
			// e.printStackTrace();
		}
	}
	
	@Override
	public List<Quibble> GetAll () {
		return this.getQuibbles();
	}
	
	@Override
	public Quibble GetById(int quibbleId) {
		return this.getQuibbles().stream().filter(q -> q.getId() == quibbleId).findFirst().orElseGet(() -> new Quibble());
	}

	// Define comparator
    Comparator <Quibble> comparator = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());

	@Override
	public Quibble create(Quibble quibble) {
		// insert into the database
		int lastId = this.getQuibbles().stream().max(comparator).get().getId();
		quibble.setId(lastId + 1);
		this.getQuibbles().add(quibble);
		Save();
		return quibble;
	}

	@Override
	public Quibble update(Quibble quibble) {

		Quibble found = this.getQuibbles().stream().filter(q -> q.getId() == quibble.getId()).findFirst().orElse(null);

		if (found != null) {
			this.getQuibbles().remove(found);
			this.getQuibbles().add(quibble);
			Save();
			return quibble;
		} else {
			return create(quibble);
		}
	}

	@Override
	public String delete(int quibbleId) {

		Quibble found = this.getQuibbles().stream().filter(q -> q.getId() == quibbleId).findFirst().orElse(null);
		if (found != null) {
			this.getQuibbles().remove(found);
			Save();
			return "Quibble " + quibbleId + " has been deleted.";
		} else {
			return "Quibble " + quibbleId + " does not exist.";
		}
	}	
}
