package si.tadej.kovacic.scottishmunro.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import si.tadej.kovacic.scottishmunro.model.MunroDTO;

@Component
public class DataInitializer {
	
	@Value("${si.tadej.kovacic.scottishmunro.data.file-location}")
	private String fileName;
	
	@PostConstruct
	private void fatchData() {
		Optional<List<MunroDTO>> munros = loadMunrosFromCsv();

	}
	

	public Optional<List<MunroDTO>> loadMunrosFromCsv(){
		 try (Reader reader = new FileReader(new ClassPathResource(fileName).getFile())) {
			 CsvToBean<MunroDTO> csvToBean = new CsvToBeanBuilder<MunroDTO>(reader)
                     .withType(MunroDTO.class)
                     .withIgnoreLeadingWhiteSpace(true)
                     .build();
			 return Optional.ofNullable(csvToBean.parse());
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
		
	}
	
}
