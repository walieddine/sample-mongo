package com.softparadigm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		if (userService.isUserExists(user)) {
			// logger.error("Unable to create. A product with name {} already exist",
			// user.getName());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A user with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userService.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PatchMapping
	public ResponseEntity<?> patchUser(@RequestBody User user, UriComponentsBuilder builder) {
		
		userService.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<?> findAllUser() {
		List<User> users = userService.findAll();

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	@GetMapping("/{userid}/products")
	public ResponseEntity<?> findAllProductsByUser(@PathVariable String userid) {
		List<Product> products = userService.findAllProductsByUser(userid);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable String id) {
		User user = userService.findById(id);
		if (user == null) {
			// logger.error("User with id {} not found", id);

			return new ResponseEntity(new CustomErrorType("User  with id " + id + " not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	@GetMapping("/findByname/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable String name) {
		User user = userService.findByName(name);
		if (user == null) {
			// logger.error("User with id {} not found", id);
			return new ResponseEntity(new CustomErrorType("User  with name " + name + " not found"),
					HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	@DeleteMapping
	public ResponseEntity<User> deleteAllUser() {
		userService.deleteAll();

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable String id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity(new CustomErrorType("User  with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		userService.deleteById(id);

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user, UriComponentsBuilder builder) {
		
		userService.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

}
