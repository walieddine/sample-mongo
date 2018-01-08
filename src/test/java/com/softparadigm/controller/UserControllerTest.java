package com.softparadigm.controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.softparadigm.SampleecommerceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SampleecommerceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void verifyFindAllUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}

	@Test
	public void verifyGetUser() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/users/5a53405f6dfefe1b6050876c")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.age").exists())
				.andExpect(jsonPath("$.id").value("5a53405f6dfefe1b6050876c"))
				.andExpect(jsonPath("$.dateOfBirth").value("1988"))
				.andExpect(jsonPath("$..address.id").exists())
				.andExpect(jsonPath("$..address.address").value("05 rue nnnnn"))
				.andExpect(jsonPath("$..address.email").exists())
				.andDo(print());
	}

	@Test
	public void verifyCreateUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"name\" : \"naaa1\", \"age\" : 20, \"dateOfBirth\":\"1988\", \"address\" : { \"address\" : \"05 rue \", \"email\" : \"na10@softparadigm.com\" }}")
				.accept(MediaType.APPLICATION_JSON))
		    
				.andDo(print());
	}

}
