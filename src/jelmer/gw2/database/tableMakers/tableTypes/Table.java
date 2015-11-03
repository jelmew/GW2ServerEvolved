package jelmer.gw2.database.tableMakers.tableTypes;

import java.sql.SQLException;

import jelmer.gw2.api.ApiKeyHolder;

public interface Table {
	String apiKey=ApiKeyHolder.key1.getApiKey();
	int stepSize=50;
	
	public void create() throws SQLException;
	
	public void update() throws SQLException;
	

}
