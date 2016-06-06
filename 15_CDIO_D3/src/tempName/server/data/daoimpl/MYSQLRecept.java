package tempName.server.data.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tempName.server.data.daointerface.DALException;
import tempName.server.data.daointerface.ReceptDAO;
import tempName.server.data.database.Connector;
import tempName.server.data.dto.ReceptDTO;


public class MYSQLRecept implements ReceptDAO{
	private Connector connector;
	
	public MYSQLRecept() {
		try {
			connector = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		ResultSet rs = connector.doQuery("SELECT * FROM recept WHERE receptId = " + receptId);
		try{
			if (!rs.first()) throw new DALException("Recepten " + receptId + " findes ikke");
			ReceptDTO recDTO = new ReceptDTO();
			recDTO.receptId(rs.getInt("receptId"));
			recDTO.receptNavn(rs.getString("receptNavn"));
			ReceptDTO result = recDTO;
			return result;
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = connector.doQuery("SELECT * FROM recept");
		try{
			while (rs.next()){
				ReceptDTO recDTO = new ReceptDTO();
				recDTO.receptId(rs.getInt("receptId"));
				recDTO.receptNavn(rs.getString("receptNavn"));
				list.add(recDTO);
			}
		} catch(SQLException e){
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createRecept(ReceptDTO rec) throws DALException {
		connector.doUpdate(
				"INSERT INTO recept(receptId, receptNavn) VALUES "
						+"(" + rec.receptId() + ", '" + rec.receptNavn() + ")"
				);		
	}

	@Override
	public void updateRecept(ReceptDTO rec) throws DALException {
		connector.doUpdate(
				"UPDATE recept SET receptId = '" + rec.receptId() + "', receptNavn = '" + rec.receptNavn()
				 + " WHERE oprId = " + rec.receptId()			
				);		
	}
}