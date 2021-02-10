package si.tadej.kovacic.scottishmunro.model;

public class FilterParamsDTO {

	private String catagory;
	private FilterOrder filterByHeight;
	private FilterOrder filterByName;
	private Integer limitResults;
	private Float minHeight;
	private Float maxHeight;
	
	public FilterParamsDTO() {
	}
	
	public FilterParamsDTO(String catagory, FilterOrder filterByHeight, FilterOrder filterByName, Integer limitResults,
			Float minHeight, Float maxHeight) {
		super();
		this.catagory = catagory;
		this.filterByHeight = filterByHeight;
		this.filterByName = filterByName;
		this.limitResults = limitResults;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public FilterOrder getFilterByHeight() {
		return filterByHeight;
	}
	public void setFilterByHeight(FilterOrder filterByHeight) {
		this.filterByHeight = filterByHeight;
	}
	public FilterOrder getFilterByName() {
		return filterByName;
	}
	public void setFilterByName(FilterOrder filterByName) {
		this.filterByName = filterByName;
	}
	public Integer getLimitResults() {
		return limitResults;
	}
	public void setLimitResults(Integer limitResults) {
		this.limitResults = limitResults;
	}
	public Float getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(Float minHeight) {
		this.minHeight = minHeight;
	}
	public Float getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(Float maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	
}
