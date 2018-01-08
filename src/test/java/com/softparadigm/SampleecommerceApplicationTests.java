package com.softparadigm;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.softparadigm.domain.Address;
import com.softparadigm.domain.User;
import com.softparadigm.repository.UserRepository;
import com.softparadigm.service.UserService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SampleecommerceApplicationTests {

	/*User userTest = new User();
	Address addressTest = new Address();
	List<User> listUsers = new ArrayList();

	@Mock
	private UserRepository daoUserMock;

	@InjectMocks
	private UserService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		addressTest.setAddress("email");
		addressTest.setEmail("test@test.com");
		addressTest.setId("1");
		userTest.setAge("29");
		userTest.setName("test");
		userTest.setAddress(addressTest);
		userTest.setDateOfBirth("1989");
		listUsers.add(userTest);
	}

	@Test
	public void contextLoads() {

	}
	@Test
	public void testSave() {
		when(daoUserMock.save(userTest)).thenReturn(userTest);
		assertEquals(service.save(userTest),userTest);
	}
	@Test
	public void testfindByName() {
		when(daoUserMock.findByName(userTest.getName())).thenReturn(userTest);
		assertEquals(service.findByName("test"),userTest);
	}
	
	@Test
	public void testfindAll() {
		when(daoUserMock.findAll()).thenReturn(listUsers);
		assertEquals(service.findByName("test"),userTest);
	}*/

}
