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
	
	@GetMapping("/munros")
	public MunroResponseDTO getMunros(
			@RequestParam(required = false) String category, 
			@RequestParam(required = false) FilterOrder filterByHeight,
			@RequestParam(required = false) FilterOrder filterByName,
			@RequestParam(required = false) @Min(1)Integer limitResults,
			@RequestParam(required = false) @Min(0)Float minHeight,
			@RequestParam(required = false) @Min(0)Float maxHeight) {
		MunroResponseDTO result = new MunroResponseDTO();
		FilterParamsDTO filter = new FilterParamsDTO(category, filterByHeight, filterByName, limitResults, minHeight, maxHeight);
		result.setFilter(filter);
		result.setMunros(service.getMunrosByFilter(filter));
		return result;
		
	}
}
