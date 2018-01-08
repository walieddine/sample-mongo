package com.softparadigm;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.softparadigm.repository.AddressRepository;
import com.softparadigm.repository.ProductRepository;
import com.softparadigm.repository.UserRepository;
import com.softparadigm.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Mock
	UserService userService;
	@InjectMocks
	UserRepository userRepository;
	@InjectMocks
	ProductRepository productRepository;
	@InjectMocks
	AddressRepository addressRepository;

}
