package tempName.server;

import tempName.client.service.GreetingService;
import tempName.server.data.daoimpl.MYSQLOperatoerDAO;
import tempName.server.data.daoimpl.MYSQLWeightDAO;
import tempName.server.data.daointerface.DALException;
import tempName.server.data.database.Connector;
import tempName.server.data.dto.OperatoerDTO;
import tempName.server.data.dto.WeightDTO;
import tempName.server.data.password.PasswordMethods;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	private MYSQLWeightDAO weightDAO = new MYSQLWeightDAO();
	private MYSQLOperatoerDAO operatoerDAO = new MYSQLOperatoerDAO();
	private PasswordMethods passMeth = new PasswordMethods(operatoerDAO);
	
	public void connectDatabase(){
		try { 
			new Connector(); 
		} catch (InstantiationException e) {
			e.printStackTrace(); 
		}catch (IllegalAccessException e) {
			e.printStackTrace(); 
		}catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	public void createOp(int id, String name, String init, String cpr, String password, boolean admin){
		try {
			operatoerDAO.createOperatoer(new OperatoerDTO(id, name, init, cpr, password, admin));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String checkLogin(int id, String pass){
		String check;
		if (passMeth.correctUserPassword(id, pass)){
			check = "true";
		} else{
			check = "false";
		}
		return check;
	}

	public ArrayList<HashMap<String, String>> getOperators(){
		List<OperatoerDTO> rawList = new ArrayList<OperatoerDTO>();
		ArrayList<HashMap<String, String>> finalData = new ArrayList<HashMap<String, String>>();
		try {
			rawList = this.operatoerDAO.getOperatoerList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < rawList.size(); i++){
			finalData.add(new HashMap<String, String>());
			finalData.get(i).put("ID", Integer.toString(rawList.get(i).getOprId()));
			finalData.get(i).put("Username", rawList.get(i).getOprNavn());
			finalData.get(i).put("Password", rawList.get(i).getPassword());
			finalData.get(i).put("cpr", rawList.get(i).getCpr());
			finalData.get(i).put("Initials", rawList.get(i).getIni());
			finalData.get(i).put("AdminStatus", Boolean.toString(rawList.get(i).getAdminStatus()));
		}
		return finalData;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList> getMeasurements(){
		List<WeightDTO> rawList = new ArrayList<WeightDTO>();
		ArrayList<ArrayList> finalData = new ArrayList<ArrayList>();
		try {
			rawList = this.weightDAO.getWeightList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < rawList.size(); i++){
			finalData.add(new ArrayList<>());
			finalData.get(i).add(rawList.get(i).getWID());
			finalData.get(i).add(rawList.get(i).getopID());
			finalData.get(i).add(rawList.get(i).getMS());
		}
		
		return finalData;
	}


}