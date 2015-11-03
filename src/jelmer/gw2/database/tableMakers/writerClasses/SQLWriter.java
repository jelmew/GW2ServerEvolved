package jelmer.gw2.database.tableMakers.writerClasses;

import java.sql.SQLException;

import org.json.JSONObject;

import java.sql.*;
import jelmer.gw2.jdbc.ConnectionPoolManager;



public class SQLWriter {
	
	 JSONObject listing;
	 Connection conn;
	 public SQLWriter(JSONObject inputObject) throws SQLException {
		conn=ConnectionPoolManager.getConnection();
		listing=inputObject;

	}

}
