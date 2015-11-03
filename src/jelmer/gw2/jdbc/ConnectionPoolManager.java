package jelmer.gw2.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPoolManager {
	
	private static ComboPooledDataSource cpd;
	static {
		cpd=Connector.getComboPooledDataSource("");
	}
	

	public static Connection getConnection() throws SQLException {
		return cpd.getConnection();
	}

	
	public static void changeComboPooledDataSource(String append) {
		cpd=Connector.getComboPooledDataSource(append);
	}
}
