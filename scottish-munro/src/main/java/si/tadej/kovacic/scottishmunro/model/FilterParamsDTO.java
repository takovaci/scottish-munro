package si.tadej.kovacic.scottishmunro.model;

import javax.annotation.Generated;

public class FilterParamsDTO {

	private String catagory;
	private FilterOrder filterByHeight;
	private FilterOrder filterByName;
	private Integer limitResults;
	private Float minHeight;
	private Float maxHeight;

	@Generated("SparkTools")
	private FilterParamsDTO(Builder builder) {
		this.catagory = builder.catagory;
		this.filterByHeight = builder.filterByHeight;
		this.filterByName = builder.filterByName;
		this.limitResults = builder.limitResults;
		this.minHeight = builder.minHeight;
		this.maxHeight = builder.maxHeight;
	}
	
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

	/**
	 * Creates builder to build {@link FilterParamsDTO}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link FilterParamsDTO}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String catagory;
		private FilterOrder filterByHeight;
		private FilterOrder filterByName;
		private Integer limitResults;
		private Float minHeight;
		private Float maxHeight;

		private Builder() {
		}

		public Builder withCatagory(String catagory) {
			this.catagory = catagory;
			return this;
		}

		public Builder withFilterByHeight(FilterOrder filterByHeight) {
			this.filterByHeight = filterByHeight;
			return this;
		}

		public Builder withFilterByName(FilterOrder filterByName) {
			this.filterByName = filterByName;
			return this;
		}

		public Builder withLimitResults(Integer limitResults) {
			this.limitResults = limitResults;
			return this;
		}

		public Builder withMinHeight(Float minHeight) {
			this.minHeight = minHeight;
			return this;
		}

		public Builder withMaxHeight(Float maxHeight) {
			this.maxHeight = maxHeight;
			return this;
		}

		public FilterParamsDTO build() {
			return new FilterParamsDTO(this);
		}
	}	
}
