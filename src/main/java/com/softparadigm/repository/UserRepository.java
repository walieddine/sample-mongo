package com.softparadigm.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.softparadigm.domain.*;
public interface UserRepository extends MongoRepository<User,String> {
   
	
	User findById(String id);
	User findByName(String name);
	void deleteById(String id);
	Address findByAddress(String id);
	

}
