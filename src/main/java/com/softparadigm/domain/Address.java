package com.softparadigm.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection="address")
public class Address implements Serializable {

	
	private static final long serialVersionUID = 1L;
    @Id
	private String id;
	private String address;
	private String email;
     
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String id, String address, String email) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
