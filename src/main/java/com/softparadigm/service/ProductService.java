package com.softparadigm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.softparadigm.domain.Product;
import com.softparadigm.repository.ProductRepository;

@Service
public class ProductService {

	private final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductRepository productRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Boolean isProductExists(Product product) {
		return productRepository.findByName(product.getName()) != null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Product product) {
		productRepository.save(product);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Product product) {
		logger.info("product " + product.getId());
		Product updated = productRepository.findById(product.getId());
		logger.info("user of product is " + updated.getUser().getName());
		updated.setDescription(product.getDescription());
		updated.setName(product.getName());
		updated.setPrice(product.getPrice());
		updated.setSlug(product.getSlug());
		productRepository.save(updated);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateProduct(Product product) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(product.getId()));
		logger.info("Query " + query);
		Product updated = mongoTemplate.findOne(query, Product.class);
		logger.info("updated " + updated.getDescription()+" "+updated.getId());
		/*
		 * updated.setDescription(product.getDescription());
		 * updated.setName(product.getName()); updated.setPrice(product.getPrice());
		 * updated.setSlug(product.getSlug());
		 */

		Update update = new Update();
		update.set("name", product.getName());
		update.set("description", product.getDescription());
		update.set("slug", product.getSlug());
		update.set("price", product.getPrice());
		mongoTemplate.updateFirst(query, update, Product.class);
		//mongoTemplate.

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Product findOne(String id) {
		return productRepository.findById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(String id) {
		productRepository.deleteById(id);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteproducts(List<Product> products) {
		productRepository.delete(products);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAll() {
		productRepository.deleteAll();
	}

}
