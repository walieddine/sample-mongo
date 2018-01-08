package com.softparadigm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softparadigm.repository.AddressRepository;

public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(String id) {

		addressRepository.deleteById(id);
	}
	
}
