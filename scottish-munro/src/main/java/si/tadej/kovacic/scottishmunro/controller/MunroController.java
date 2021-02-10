package si.tadej.kovacic.scottishmunro.controller;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import si.tadej.kovacic.scottishmunro.model.FilterOrder;
import si.tadej.kovacic.scottishmunro.model.FilterParamsDTO;
import si.tadej.kovacic.scottishmunro.model.MunroResponseDTO;
import si.tadej.kovacic.scottishmunro.service.MunroService;

@RestController
public class MunroController {

	@Autowired
	private MunroService service;
	
	@GetMapping(path = "/munros", produces = "application/json")
	public MunroResponseDTO getMunros(
			@RequestParam String category, 
			@RequestParam FilterOrder filterByHeight,
			@RequestParam FilterOrder filterByName,
			@RequestParam @Min(1)Integer limitResults,
			@RequestParam @Min(0)Float minHeight,
			@RequestParam @Min(0)Float maxHeight) {
		MunroResponseDTO result = new MunroResponseDTO();
		FilterParamsDTO filter = new FilterParamsDTO(category, filterByHeight, filterByName, limitResults, minHeight, maxHeight);
		result.setFilter(filter);
		result.setMunros(service.getMunrosByFilter(filter));
		return result;
		
	}
}
