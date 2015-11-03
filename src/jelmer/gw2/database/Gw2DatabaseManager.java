package jelmer.gw2.database;

import jelmer.gw2.database.tableMakers.tableTypes.*;
import jelmer.gw2.jdbc.*;
import jelmer.gw2.sql.SQLStatementHelper;

import java.sql.SQLException;

public class Gw2DatabaseManager {
	
	final private String dbName="GuildWars2";

	public Gw2DatabaseManager() throws SQLException {
		System.out.println("Connecting to database");
		ConnectionPoolManager.changeComboPooledDataSource("");
	}	
	
	public Gw2DatabaseManager(String inputDbName) throws SQLException {
		ConnectionPoolManager.changeComboPooledDataSource("dbName");
	
	}

	public void createGw2Database() throws SQLException{
		String sql="CREATE DATABASE IF NOT EXISTS "+dbName;
		SQLStatementHelper.createStatementExecutor(sql, ConnectionPoolManager.getConnection());
		ConnectionPoolManager.changeComboPooledDataSource(dbName);
	}
	
	public void createTable(Table inputTable) {
		try {
			inputTable.create();
		} catch (SQLException e) {
			System.out.println("Creation failed");
			e.printStackTrace();
		}
	}
	
	public void updateTable(Table inputTable) {
		try {
			inputTable.update();
		} catch (SQLException e) {
			System.out.println("Update failed");
			e.printStackTrace();
		}
	}
}