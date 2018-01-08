package com.softparadigm.domain;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softparadigm.annotation.CascadeSave;

import org.springframework.data.annotation.Id;

@Document(collection = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private String age;
	private String dateOfBirth;
	@DBRef
	@CascadeSave
	private Address address;

	@JsonIgnore
	private Collection<Product> products;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String id, String name, String age, String dateOfBirth, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		// this.products=products;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

}
