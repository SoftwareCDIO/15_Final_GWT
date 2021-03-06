package tempName.server.data.daointerface;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import tempName.shared.dto.RaavareDTO;

public interface RaavareDAO {
	void addRaavare (RaavareDTO w) throws DALException;
	List<RaavareDTO> getRaavareList() throws DALException;
	void updateRaavare (RaavareDTO r) throws DALException;
	void deleteRaavare (int id) throws DALException, SQLIntegrityConstraintViolationException;
}
