package si.tadej.kovacic.scottishmunro.model;

import com.opencsv.bean.CsvBindByName;
import javax.annotation.Generated;

public class MunroDTO {
	@CsvBindByName
	private String name;
	@CsvBindByName(column = "Height (m)")
	private Float height;  //height in meters
	@CsvBindByName(column = "post 1997")
	private String hillCategory;
	@CsvBindByName(column = "Grid Ref")
	private String gridReference;
	
	public MunroDTO() {
		super();
	}


	@Generated("SparkTools")
	private MunroDTO(Builder builder) {
		this.name = builder.name;
		this.height = builder.height;
		this.hillCategory = builder.hillCategory;
		this.gridReference = builder.gridReference;
	}
	
	
	public String getName() {
		return name;
	}


	public Float getHeight() {
		return height;
	}


	public String getHillCategory() {
		return hillCategory;
	}


	public String getGridReference() {
		return gridReference;
	}


	/**
	 * Creates builder to build {@link MunroDTO}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link MunroDTO}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String name;
		private Float height;
		private String hillCategory;
		private String gridReference;

		private Builder() {
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withHeight(Float height) {
			this.height = height;
			return this;
		}

		public Builder withHillCategory(String hillCategory) {
			this.hillCategory = hillCategory;
			return this;
		}

		public Builder withGridReference(String gridReference) {
			this.gridReference = gridReference;
			return this;
		}

		public MunroDTO build() {
			return new MunroDTO(this);
		}
	}
	
}
