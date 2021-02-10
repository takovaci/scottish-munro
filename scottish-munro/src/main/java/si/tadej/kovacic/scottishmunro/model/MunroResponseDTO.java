package si.tadej.kovacic.scottishmunro.model;

import java.util.List;

public class MunroResponseDTO {

	private List<MunroDTO> munros;
	private FilterParamsDTO filter;
	private String error;
	
	public List<MunroDTO> getMunros() {
		return munros;
	}
	public void setMunros(List<MunroDTO> munros) {
		this.munros = munros;
	}
	public FilterParamsDTO getFilter() {
		return filter;
	}
	public void setFilter(FilterParamsDTO filter) {
		this.filter = filter;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
}
