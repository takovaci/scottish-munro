package si.tadej.kovacic.scotishmunro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import si.tadej.kovacic.scottishmunro.model.FilterOrder;
import si.tadej.kovacic.scottishmunro.model.FilterParamsDTO;
import si.tadej.kovacic.scottishmunro.model.MunroDTO;
import si.tadej.kovacic.scottishmunro.repository.MunroStore;
import si.tadej.kovacic.scottishmunro.service.MunroService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MunroServiceTest {
	
	private MunroService service;
	
	private MunroStore store = Mockito.mock(MunroStore.class);
	
	private MunroDTO munro1 = MunroDTO.builder()
			.withGridReference("A")
			.withHeight(1.1f)
			.withHillCategory("CAT1")
			.withName("name A")
			.build();
	
	private MunroDTO munro2 = MunroDTO.builder()
			.withGridReference("B")
			.withHeight(1.0f)
			.withHillCategory("CAT2")
			.withName("name A")
			.build();
	
	private MunroDTO munro3 = MunroDTO.builder()
			.withGridReference("C")
			.withHeight(1.1f)
			.withHillCategory("CAT2")
			.withName("name B")
			.build();
	
	private MunroDTO munro4 = MunroDTO.builder()
			.withGridReference("D")
			.withHeight(1.1f)
			.withHillCategory("CAT1")
			.withName("name C")
			.build();

	
	@BeforeEach
	public void setup() {
		service = new MunroService(store);
		when(store.getMunros()).thenReturn(Arrays.asList(
				munro1,
				munro2,
				munro3,
				munro4
				));
	}

	@Test
	public void no_filter() {
		List<MunroDTO> munros = service.getMunrosByFilter(new FilterParamsDTO());
		assertEquals(munros.size(), 4);
		assertEquals(munros, Arrays.asList(
				munro1,
				munro2,
				munro3,
				munro4
				));	
	}
	
	@Test
	public void limit_results() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setLimitResults(1);
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 1);
		assertEquals(munros, Arrays.asList(
				munro1
				));	
	}
	
	@Test
	public void only_category_CAT1() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setCatagory("CAT1");
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 2);
		assertEquals(munros, Arrays.asList(
				munro1,
				munro4
				));	
	}
	
	@Test
	public void max_height_1_0() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setMaxHeight(1.0f);
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 1);
		assertEquals(munros, Arrays.asList(
				munro2
				));	
	}
	@Test
	public void min_height_1_1() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setMinHeight(1.1f);
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 3);
		assertEquals(munros, Arrays.asList(
				munro1,
				munro3,
				munro4
				));	
	}
	
	@Test
	public void filter_by_height_asc() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setFilterByHeight(FilterOrder.ASC);
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 4);
		assertEquals(munros, Arrays.asList(
				munro2,
				munro1,
				munro3,
				munro4
				));	
	}
	
	@Test
	public void filter_by_height_desc() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setFilterByHeight(FilterOrder.DESC);
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 4);
		assertEquals(munros, Arrays.asList(
				munro1,
				munro3,
				munro4,
				munro2
				));	
	}
	
	@Test
	public void filter_by_name_desc() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setFilterByName(FilterOrder.DESC);
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 4);
		assertEquals(munros, Arrays.asList(
				munro4,
				munro3,
				munro1,
				munro2
				));	
	}
	
	@Test
	public void filter_by_name_asc() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setFilterByName(FilterOrder.ASC);
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 4);
		assertEquals(munros, Arrays.asList(
				munro1,
				munro2,
				munro3,
				munro4
				));	
	}
	
	@Test
	public void filter_by_name_asc_and_height_desc() {
		FilterParamsDTO filter = new FilterParamsDTO();
		filter.setFilterByName(FilterOrder.ASC);
		filter.setFilterByHeight(FilterOrder.DESC);
		List<MunroDTO> munros = service.getMunrosByFilter(filter);
		assertEquals(munros.size(), 4);
		assertEquals(munros, Arrays.asList(
				munro1,
				munro3,
				munro4,
				munro2
				));	
	}
}
