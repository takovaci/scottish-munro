package si.tadej.kovacic.scottishmunro.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MunroDTO {
	@CsvBindByName
	private String name;
	@CsvBindByName(column = "Height (m)")
	private Float height;  //height in meters
	@CsvBindByName(column = "post 1997")
	private String hillCategory;
	@CsvBindByName(column = "Grid Ref")
	private String gridReference;
}
