package com.david.florczak.westmarches.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.david.florczak.westmarches.testUtilities.BaseMvcTests;

public class UserControllerTest extends BaseMvcTests {
	
	@Test
	void testCreateAccountValid() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\": \"robertNew2@fakemail.com\",\"password\": \"qweqwe1!\"}");
		
		ResultActions result = perform(builder);
		result.andExpect(status().isCreated());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/controllers/UserController/createAccountValidation.csv", numLinesToSkip = 1, delimiterString = DELIMITER)
	void testCreateAccountNotValid(String json) throws Exception {
		MockHttpServletRequestBuilder builder = builder(HttpMethod.POST, "/user", MediaType.APPLICATION_JSON, json);
		
		ResultActions result = perform(builder);
		result.andExpect(status().isBadRequest());
	}
}