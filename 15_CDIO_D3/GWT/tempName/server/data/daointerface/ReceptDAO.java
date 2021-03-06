package tempName.server.data.daointerface;

import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.shared.dto.ReceptKomponentDTO;
import tempName.shared.dto.ReceptDTO;

public interface ReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO rec) throws DALException;
	void updateRecept(ReceptDTO rec) throws DALException;
	void deleteRecept(int receptId) throws DALException;
	
	//Methods for "receptkomponent" table access
		ReceptKomponentDTO getReceptKomp(int receptId, int raavareId) throws DALException;
		List<ReceptKomponentDTO> getReceptKompList(int receptId) throws DALException;
		List<ReceptKomponentDTO> getReceptKompList() throws DALException;
		void updateReceptKomp(ReceptKomponentDTO receptkomponent) throws DALException;
		void deleteReceptKomp(int receptId, int raavareId) throws DALException;
	
}
