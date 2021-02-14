package si.tadej.kovacic.scotishmunro.controler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import si.tadej.kovacic.scottishmunro.ScottishMunroApplication;
import si.tadej.kovacic.scottishmunro.model.FilterOrder;
import si.tadej.kovacic.scottishmunro.model.FilterParamsDTO;
import si.tadej.kovacic.scottishmunro.model.MunroResponseDTO;
import si.tadej.kovacic.scottishmunro.service.MunroService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ScottishMunroApplication.class)
@WebAppConfiguration
public class MunroControllerTest {
	protected MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	MunroService service;

	@BeforeEach
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void getMunros() throws Exception {
		FilterParamsDTO filter = FilterParamsDTO.builder()
				.withCatagory("C")
				.withFilterByHeight(FilterOrder.ASC)
				.withFilterByName(FilterOrder.ASC)
				.withLimitResults(4)
				.withMaxHeight(10f)
				.withMinHeight(3f)
				.build();
		when(service.getMunrosByFilter(filter)).thenReturn(Collections.emptyList());
		String uri = "/munros?filterByHeight=ASC&filterByName=ASC&category=C&limitResults=4&maxHeight=10.0&minHeight=3.0";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		MunroResponseDTO response = mapFromJson(content, MunroResponseDTO.class);
		assertTrue(EqualsBuilder.reflectionEquals(response.getFilter(),filter));
	}
}
