package com.appllication.teluslibrary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.models.BookModel;
import com.appllication.teluslibrary.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port = 8080;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
  	private ObjectMapper objectMapper;

	@Test 
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Hello, World");
	}

	@Test
	void test_registerNewUser() throws Exception {
		UserModel usr = new UserModel("Joe", "Swanson","nolos@gmail.com");
		mockMvc.perform(post("/users")
		//.contentType(MediaType.APPLICATION_JSON).content(json))
		.content(objectMapper.writeValueAsString(usr))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is(200))
		.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Joe"));

		/*
		String json = "{\"firstName\": \"Joe\", \"lastName\": \"Swanson\", \"email\":\"jonas251@gmail.com\" }";
        .contentType(MediaType.APPLICATION_JSON).content(json))
		
		mockMvc.perform(
		post("/users")
		.content(objectMapper.writeValueAsString(usr))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Joe"));
		*/

		/*
		mockMvc.perform(MockMvcRequestBuilders.post("/users", 42L)
				.contentType("application/json")
				.param("sendWelcomeMail", "true")
				.content(objectMapper.writeValueAsString(usr)))
				.andExpect(status().isOk());

		UserEntity userEntity = userRepository.findByName("Zaphod");
		assertThat(userEntity.getEmail()).isEqualTo("zaphod@galaxy.net");
		*/
	}

	@Test
	void test_registerNewBook() throws Exception {
		BookModel book = new BookModel("Jarry Popotes :v", 35);
		mockMvc.perform(post("/books")
		.content(objectMapper.writeValueAsString(book))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is(200))
		.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Jarry Popotes :v"));
	}
}
