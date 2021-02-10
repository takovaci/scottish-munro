package si.tadej.kovacic.scottishmunro.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si.tadej.kovacic.scottishmunro.model.FilterParamsDTO;
import si.tadej.kovacic.scottishmunro.model.MunroDTO;
import si.tadej.kovacic.scottishmunro.repository.MunroStore;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;

@Service
public class MunroService {
	
	@Autowired
	private MunroStore store;

	public List<MunroDTO> getMunrosByFilter(FilterParamsDTO filter) {
		List<MunroDTO> munros = store.getMunros();
		if(!StringUtils.isBlank(filter.getCatagory())){
			munros = munros.stream().filter(m -> filter.getCatagory().trim().equalsIgnoreCase(m.getHillCategory())).collect(toList());
		}
		if(filter.getMinHeight() != null) {
			munros = munros.stream().filter(m -> filter.getMinHeight() <= m.getHeight()).collect(toList());
		}
		if(filter.getMaxHeight() != null) {
			munros = munros.stream().filter(m -> filter.getMaxHeight() >= m.getHeight()).collect(toList());
		}
		
		if(munros.size() > filter.getLimitResults()) {
			munros = munros.subList(0, filter.getLimitResults()-1);
		}
		if(filter.getFilterByHeight() != null || filter.getFilterByName() != null) {
			Comparator<MunroDTO> compare = null;
			if(filter.getFilterByHeight() != null) {
				switch(filter.getFilterByHeight()) {
				case ASC:
					compare = Comparator.comparing(MunroDTO::getHeight);
					break;
				case DESC:
					compare = Comparator.comparing(MunroDTO::getHeight).reversed();
					break;
				default:
					break;
				}
			}
			if(filter.getFilterByName() != null) {
				Comparator<MunroDTO> compareByHeight = null;
				switch(filter.getFilterByHeight()) {
				case ASC:
					compareByHeight = Comparator.comparing(MunroDTO::getHeight);
					break;
				case DESC:
					compareByHeight = Comparator.comparing(MunroDTO::getHeight).reversed();
					break;
				default:
					break;
				
				}
				if(compare != null) {
					compare = compare.thenComparing(compareByHeight);
				} else {
					compare = compareByHeight;
				}
			}
			munros.sort(compare);
		}
		return munros;
	}

}
