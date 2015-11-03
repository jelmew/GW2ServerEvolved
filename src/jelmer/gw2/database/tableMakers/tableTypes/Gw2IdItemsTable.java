package jelmer.gw2.database.tableMakers.tableTypes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;

import jelmer.gw2.sql.*;
import jelmer.gw2.database.tableMakers.writerClasses.SQLitemWriter;
import jelmer.gw2.jdbc.ConnectionPoolManager;
import jelmer.gw2.json.GW2Reader;

public class Gw2IdItemsTable implements Table {

	
	public void create() throws SQLException{
		String sql="CREATE TABLE IF NOT EXISTS items( id INT(6) UNSIGNED PRIMARY KEY, name VARCHAR(60),icon VARCHAR(120),"
				+ "description VARCHAR(300))";
		SQLStatementHelper.createStatementExecutor(sql, ConnectionPoolManager.getConnection());
		
	}


	public void update() throws SQLException {
		ExecutorService ex=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());  
		ArrayList<Long> idList= new ArrayList<Long>();
		try {
			JSONArray itemIdListJSONArray=GW2Reader.getListOfAllItemIds();
			for(int i=0;i<itemIdListJSONArray.length();i++) {
				idList.add(itemIdListJSONArray.getLong(i));
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		try {
			
			for(int i=0; i<idList.size();i+=stepSize) {
				int start=i;
				int end=i+stepSize;
				if(end>idList.size()) end=idList.size();
				JSONArray idSubListJSONArray=GW2Reader.getItemsFromId(idList.subList(start, end)); 
				//System.out.println(idSubListJSONArray);
				for(int j=0; j<idSubListJSONArray.length();j++) {
					ex.execute(new SQLitemWriter(idSubListJSONArray.getJSONObject(j)));
				}
			}

		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}finally {
			ex.shutdown();
		}
			
	}

}
