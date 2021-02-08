package si.tadej.kovacic.scottishmunro.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import si.tadej.kovacic.scottishmunro.model.MunroDTO;
import si.tadej.kovacic.scottishmunro.repository.MunroStore;

@Component
public class DataInitializer {
	
	@Value("${si.tadej.kovacic.scottishmunro.data.file-location}")
	private String fileName;
	
	@Autowired
	private MunroStore munroStore;
	
	@PostConstruct
	private void fatchData() {
		// removing all munros without category 
		munroStore.setMunros(loadMunrosFromCsv().stream()
				.filter(m -> !StringUtils.isBlank(m.getHillCategory())).collect(Collectors.toList()));
		
	}
	

	public List<MunroDTO> loadMunrosFromCsv(){
		 try (Reader reader = new FileReader(new ClassPathResource(fileName).getFile())) {
			 CsvToBean<MunroDTO> csvToBean = new CsvToBeanBuilder<MunroDTO>(reader)
                     .withType(MunroDTO.class)
                     .withIgnoreLeadingWhiteSpace(true)
                     .build();
			 return csvToBean.parse();
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
}
