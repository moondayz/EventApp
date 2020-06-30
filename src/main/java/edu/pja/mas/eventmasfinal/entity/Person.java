package edu.pja.mas.eventmasfinal.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;;

//Table per class strategy is to create a table for each sub entity.
//The person table will be created but it will contain null records.
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPerson;

	@Size(min = 2, message = "First Name should be having at least 2 characters") // validation api
	private String firstName;

	@Size(min = 2, message = "Last Name should be having at least 2 characters") // validation api
	private String lastName;

	@Column(unique = true)
	@NotNull(message = "Pesel cannot be null")
	private int pesel;

	@Pattern(regexp = "(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{3}", message = "phone format (xxx-xxx-xxx)")
	private String phoneNumber;

	public Person() { // this constructor for db

	}

	public Person(@Size(min = 2, message = "First Name should be having at least 2 characters") String firstName,
			@Size(min = 2, message = "Last Name should be having at least 2 characters") String lastName,
			 int pesel,
			@Pattern(regexp = "(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{3}", message = "phone format (xxx-xxx-xxx)") String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
		this.phoneNumber = phoneNumber;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPesel() {
		return pesel;
	}

	public void setPesel(int pesel) {
		this.pesel = pesel;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return " Name :" + firstName + " " + lastName + " number : " + phoneNumber + " Pesel : " + pesel;
	}
}
