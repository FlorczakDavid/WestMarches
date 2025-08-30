package com.david.florczak.westmarches.testUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BaseMvcTests {

    protected static final String DELIMITER = "|@|";

	@Autowired
	private MockMvc mvc;
	
	protected final ResultActions perform(RequestBuilder builder) throws Exception {
		return mvc.perform(builder);
	}
	
	protected final MockHttpServletRequestBuilder builder(
			HttpMethod method, 
			String path,
			MediaType contentType,
			String json) {
		return MockMvcRequestBuilders
				.request(method, path)
				.contentType(contentType)
				.content(json);
	}
}
