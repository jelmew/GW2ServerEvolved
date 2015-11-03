package jelmer.gw2.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;


public class SQLStatementHelper {

	public static void createStatementExecutor(String sql, Connection givenConnection) throws SQLException {
		PreparedStatement stmt=givenConnection.prepareStatement(sql);
		//Statement stmt=givenConnection.createStatement();

		stmt.executeUpdate(sql);
		givenConnection.close();
	}
	
	public static void fillTransactionStatement(PreparedStatement pstmt, JSONObject transactionLsting) throws JSONException, SQLException {
		pstmt.setLong(1, transactionLsting.getLong("id"));
		pstmt.setInt(2, transactionLsting.getInt("item_id"));
		pstmt.setInt(3, transactionLsting.getInt("price"));
		pstmt.setInt(4, transactionLsting.getInt("quantity"));
		DateTime created= new DateTime(transactionLsting.getString("created"));
		java.sql.Date date=new java.sql.Date(created.getMillis());
		pstmt.setDate(5,date);
		if(transactionLsting.has("purchased")) {
			DateTime purchased= new DateTime(transactionLsting.getString("purchased"));
			java.sql. Date date2=new java.sql.Date(purchased.getMillis());
			pstmt.setDate(6, date2);
	
		}
	}
}
