package com.david.florczak.westmarches.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.david.florczak.westmarches.testUtilities.BaseMvcTests;

public class TileControllerTest extends BaseMvcTests {

	@ParameterizedTest
	@CsvFileSource(resources = "/controllers/TileController/editTileValidation_Valid.csv", numLinesToSkip = 1, delimiterString = DELIMITER)
	void testEditTileValid(String json) throws Exception {
		MockHttpServletRequestBuilder builder = builder(HttpMethod.PATCH, "/tile/", MediaType.APPLICATION_JSON, json);
		ResultActions result = perform(builder);
		result.andExpect(status().isNoContent());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/controllers/TileController/editTileValidation_notValid.csv", numLinesToSkip = 1, delimiterString = DELIMITER)
	void testEditTileNotValid(String json) throws Exception {
		MockHttpServletRequestBuilder builder = builder(HttpMethod.PATCH, "/tile/", MediaType.APPLICATION_JSON, json);
		ResultActions result = perform(builder);
		result.andExpect(status().isBadRequest());
	}
}
