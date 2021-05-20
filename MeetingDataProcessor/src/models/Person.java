package models;

import java.time.LocalDateTime;

public class Person {
	private int id;
	private LocalDateTime lastPositiveCheck;
	private LocalDateTime fakedDateTime =LocalDateTime.of(2021,03,21,20,00);

	public Person(int id, LocalDateTime lastPositiveCheck) {
		this.id = id;
		this.lastPositiveCheck = lastPositiveCheck;
	}
	
	public Person(int id) {
		this.id = id;
	}
	
	public void setLast_positive_check(LocalDateTime lastPositiveCheck) {
		this.lastPositiveCheck = lastPositiveCheck;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getLastPositiveCheck() {
		return lastPositiveCheck;
	}

	@Override
	public String toString() {
		return "Persons [id=" + id + ", last_positive_check=" + lastPositiveCheck + "]";
	}
}
