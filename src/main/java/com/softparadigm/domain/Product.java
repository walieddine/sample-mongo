package com.softparadigm.domain;

import java.io.Serializable;

import javax.jdo.annotations.Unique;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.softparadigm.annotation.CascadeSave;

@Document(collection = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;

	private String name;

	private String description;

	private double price;
	private String slug;

	@DBRef
	private User user;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String id, String name, String description, double price, String slug,User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.slug = slug;
		 this.user = user;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public User getUser() {
		return user;
	}

	//
	public void setUser(User user) {
		this.user = user;
	}

}
