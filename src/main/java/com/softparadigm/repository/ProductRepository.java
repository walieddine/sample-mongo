package com.softparadigm.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.softparadigm.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	Product findByName(String name);
    Product findBySlug(String slug);
	Product findById(String id);
    List<Product> findAllByUser(String id);
	void deleteById(String id);
	
//	@Query("update")
//	 void update(Product product);
}
