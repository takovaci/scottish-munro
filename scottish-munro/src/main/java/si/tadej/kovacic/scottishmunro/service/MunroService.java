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
		if(filter.getFilterByHeight() != null || filter.getFilterByName() != null) {
			Comparator<MunroDTO> compare = null;
			if(filter.getFilterByHeight() != null) {
				switch(filter.getFilterByHeight()) {
				case ASC:
					compare = Comparator.comparing(MunroDTO::getHeight, (m1, m2) -> {
					    if(m1 == m2){
					         return 0;
					    } else if(m1 == null) {
					    	return 1;
					    } else if(m2 == null) {
					    	return -1;
					    } else {
					    	return m1<m2 ? -1 : 1;
					    }
					});
					break;
				case DESC:
					compare = Comparator.comparing(MunroDTO::getHeight, (m1, m2) -> {
					    if(m1 == m2){
					         return 0;
					    } else if(m1 == null) {
					    	return 1;
					    } else if(m2 == null) {
					    	return -1;
					    } else {
					    	return m1>m2 ? -1 : 1;
					    }
					});
					break;
				default:
					break;
				}
			}
			if(filter.getFilterByName() != null) {
				Comparator<MunroDTO> compareByName = null;
				switch(filter.getFilterByName()) {
				case ASC:
					compareByName = Comparator.comparing(MunroDTO::getName, (m1, m2) -> {
					    if(m1 == m2){
					         return 0;
					    } else if(m1 == null) {
					    	return 1;
					    } else if(m2 == null) {
					    	return -1;
					    } else {
					    	return m1.compareToIgnoreCase(m2);
					    }
					});
					break;
				case DESC:
					compareByName = Comparator.comparing(MunroDTO::getName, (m1, m2) -> {
					    if(m1 == m2){
					         return 0;
					    } else if(m1 == null) {
					    	return 1;
					    } else if(m2 == null) {
					    	return -1;
					    } else {
					    	return m2.compareToIgnoreCase(m1);
					    }
					});
					break;
				default:
					break;
				
				}
				if(compare != null) {
					compare = compare.thenComparing(compareByName);
				} else {
					compare = compareByName;
				}
			}
			munros.sort(compare);
		}
		if(filter.getLimitResults() != null && munros.size() > filter.getLimitResults()) {
			munros = munros.subList(0, filter.getLimitResults());
		}
		return munros;
	}

}
