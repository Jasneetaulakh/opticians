package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class OpticiansApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

//	@Test
//	public void testGetExamination() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders.get("/examination");
//		mockMvc.perform(request)
//				.andExpect(status().is2xxSuccessful())
//				.andExpect(view().name("examination"))
//				.andExpect(model().attributeExists("examination"));
//
//	}

}
//preferences,build,compiler,java compiler 11