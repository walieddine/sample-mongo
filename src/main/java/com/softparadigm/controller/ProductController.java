package com.softparadigm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.softparadigm.domain.Product;
import com.softparadigm.domain.User;
import com.softparadigm.service.ProductService;
import com.softparadigm.service.UserService;
import com.softparadigm.util.CustomErrorType;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	@PostMapping("/{userId}")
	public ResponseEntity<?> createProduct(@PathVariable String userId, @RequestBody Product product,
			UriComponentsBuilder builder) {
		System.out.println("create product" + product.getName());
		if (productService.isProductExists(product)) {

			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A product with name " + product.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}

		User u = userService.findById(userId);
		if (u == null) {
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A user  with id " + product.getUser().getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		product.setUser(u);
		System.out.println(u + " " + product.getName());
		productService.save(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping()
	public ResponseEntity<?> updateProduct(@RequestBody Product product, UriComponentsBuilder builder) {
		// Product productlast = productService.findOne(id);
		System.out.println("product update"+product.getId()+" "+product.getDescription());

		System.out.println("product" + product.toString());
		productService.updateProduct(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		// return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<?> findAllProducts() {
		List<Product> products = productService.findAll();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductByID(@PathVariable String id) {
		Product product = productService.findOne(id);
		if (product == null) {
			// logger.error("product with id {} not found", id);
			return new ResponseEntity(new CustomErrorType("Product  with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) { //
		// logger.info("Fetching & Deleting Product with id {}", id);

		Product category = productService.findOne(id);
		if (category == null) { //
			// logger.error("Unable to delete. Product with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Product with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		productService.delete(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Product> deleteAllProducts() { //
		// logger.info("Deleting All Products");
		productService.deleteAll();
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

}
