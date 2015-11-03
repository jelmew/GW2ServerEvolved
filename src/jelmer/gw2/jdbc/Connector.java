package jelmer.gw2.jdbc;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
public class Connector {
	
	
	public static Connection getConnection(String append) throws SQLException {
		Connection conn=null;
		try(FileInputStream f = new FileInputStream("/home/jelmer/workspace/Gw2Server/db.properties")){
			Properties pros = new Properties();
			pros.load(f);
			
			String url=pros.getProperty("url")+append;
			String user=pros.getProperty("user");
			String password=pros.getProperty("password");
			conn=DriverManager.getConnection(url, user, password);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
			
	}
	
	
	public static Connection getConnection() throws SQLException {
		return getConnection("");
	}
	
	
	public static ComboPooledDataSource getComboPooledDataSource(String append) throws ExceptionInInitializerError{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		String url=null;
		String user=null;
		String password=null;
		try(FileInputStream f = new FileInputStream("/home/jelmer/workspace/Gw2Server/db.properties")){
			Properties pros =new Properties();
			pros.load(f);
			url=pros.getProperty("url")+append;
			user=pros.getProperty("user");
			password=pros.getProperty("password");
			
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Could not initialize");
		}
		try {
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			cpds.setJdbcUrl(url);
			cpds.setUser(user);
			cpds.setPassword(password);
			
			cpds.setMinPoolSize(1);
			cpds.setAcquireIncrement(1);
			cpds.setMaxPoolSize(20);
			return cpds;
		} catch (PropertyVetoException e) {
			throw new ExceptionInInitializerError("Could not initialise datapool");
		}
	}
	
	

}
