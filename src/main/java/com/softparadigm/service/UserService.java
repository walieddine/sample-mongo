package com.softparadigm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softparadigm.domain.Address;
import com.softparadigm.domain.Product;
import com.softparadigm.domain.User;
import com.softparadigm.repository.AddressRepository;
import com.softparadigm.repository.ProductRepository;
import com.softparadigm.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	AddressRepository addressRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public User save(User user) {
		return userRepository.save(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateuser(User user) {
		User updated = userRepository.findById(user.getId());
		Address oldadress = new Address();
		
		System.out.println(updated.toString()+" "+updated.getId()+" ");
		System.out.println(updated.getAddress().getId());
		oldadress.setId(updated.getAddress().getId());
		oldadress.setAddress(user.getAddress().getAddress());
		oldadress.setEmail(user.getAddress().getEmail());
		addressRepository.save(oldadress);
		updated.setAddress(oldadress);
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
		addressRepository.deleteById(userRepository.findById(id).getAddress().getId());
		productRepository.delete(productRepository.findAllByUser(id));
		userRepository.deleteById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Product> findAllProductsByUser(String userid) {
		return productRepository.findAllByUser(userid);
	}
}
