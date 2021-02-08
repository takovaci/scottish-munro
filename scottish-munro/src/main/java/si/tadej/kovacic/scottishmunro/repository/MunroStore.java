package si.tadej.kovacic.scottishmunro.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import si.tadej.kovacic.scottishmunro.model.MunroDTO;

@Repository

public class MunroStore {

	private List<MunroDTO> munros;

	public List<MunroDTO> getMunros() {
		return munros;
	}

	public void setMunros(List<MunroDTO> munros) {
		this.munros = munros;
	}
	
}
