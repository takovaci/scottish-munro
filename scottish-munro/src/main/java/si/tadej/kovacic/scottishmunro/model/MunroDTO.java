package si.tadej.kovacic.scottishmunro.model;

import com.opencsv.bean.CsvBindByName;

public class MunroDTO {
	@CsvBindByName
	private String name;
	@CsvBindByName(column = "Height (m)")
	private Float height;  //height in meters
	@CsvBindByName(column = "post 1997")
	private String hillCategory;
	@CsvBindByName(column = "Grid Ref")
	private String gridReference;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public String getHillCategory() {
		return hillCategory;
	}
	public void setHillCategory(String hillCategory) {
		this.hillCategory = hillCategory;
	}
	public String getGridReference() {
		return gridReference;
	}
	public void setGridReference(String gridReference) {
		this.gridReference = gridReference;
	}
	
	
}
