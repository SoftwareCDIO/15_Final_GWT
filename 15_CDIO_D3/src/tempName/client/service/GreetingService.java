package tempName.client.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	ArrayList<ArrayList> getMeasurements();

	ArrayList<HashMap<String, String>> getOperators();

	String checkLogin(int id, String pass);

	void connectDatabase();
	
	void createOp(int id, String name, String ini, String cpr, String password, boolean admin);
}
