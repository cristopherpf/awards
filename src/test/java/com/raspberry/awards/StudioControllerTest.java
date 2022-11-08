package com.raspberry.awards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.raspberry.awards.entities.Studio;
import com.raspberry.awards.service.StudioService;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class StudioControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private StudioService service;
	
	@Autowired
	private JacksonTester<Studio> json;
	
	@Test
	public void should_create_studio() throws Exception {
		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/studios")
																		     .accept(MediaType.APPLICATION_JSON)
				                                                             .contentType(MediaType.APPLICATION_JSON)
				                                                             .content(json.write(
				                                                            		 new Studio("Studio 1")).getJson()
				                                                             )
											           ).andReturn()
											            .getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getContentAsString())
		          .isEqualTo(
		              json.write(new Studio(150L, "Studio 1")).getJson()
		          );
	}
}
