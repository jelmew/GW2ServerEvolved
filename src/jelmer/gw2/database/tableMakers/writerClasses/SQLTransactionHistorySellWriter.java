package jelmer.gw2.database.tableMakers.writerClasses;

import java.sql.*;

import org.json.JSONObject;

import jelmer.gw2.sql.SQLStatementHelper;


public class SQLTransactionHistorySellWriter extends SQLWriter implements SQLWriterInterface {

	
	public  SQLTransactionHistorySellWriter( JSONObject inputJSONObject) throws SQLException { // TODO Auto-generated constructor stub
		super(inputJSONObject);
	}
	@Override
	public void run() {
		try {
			writeToDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void writeToDatabase() throws SQLException {
		try {
			String sql="INSERT IGNORE INTO transactionsHistorySells(transactionID,id,price,quantity"
					+ ",created,purchased)"+"VALUES(?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			SQLStatementHelper.fillTransactionStatement(pstmt, listing);
			int row=pstmt.executeUpdate();
		}
		finally {
			conn.close();
		}
	}

}
