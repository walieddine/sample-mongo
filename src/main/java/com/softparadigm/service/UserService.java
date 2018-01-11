package com.softparadigm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softparadigm.domain.Product;
import com.softparadigm.domain.User;
import com.softparadigm.repository.ProductRepository;
import com.softparadigm.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	

	@Transactional(propagation = Propagation.REQUIRED)
	public User save(User user) {
		return userRepository.save(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateuser(User user) {
		User updated = userRepository.findById(user.getId());
		//System.out.println("updated "+updated.getId());
		
		
		//addressRepository.save(oldadress);
		//updated.setAddress(oldadress);
		updated.setAge(user.getAge());
		updated.setDateOfBirth(user.getDateOfBirth());
		updated.setName(user.getName());
		userRepository.save(updated);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Boolean isUserExists(User user) {
		return userRepository.findByName(user.getName()) != null;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findById(String id) {
		return userRepository.findById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(String id) {
	List<Product> products = productRepository.findAllByUser(id);
		for (Product product : products) {
			product.setUser(null);
			
		}
		productRepository.save(products);	
		
		userRepository.deleteById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAll() {
		userRepository.deleteAll();
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteuser(User user) {
		userRepository.delete(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Product> findAllProductsByUser(String userid) {
		return productRepository.findAllByUser(userid);
	}
}
