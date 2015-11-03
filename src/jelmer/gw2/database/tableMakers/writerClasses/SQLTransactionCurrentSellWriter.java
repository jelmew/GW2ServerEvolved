package jelmer.gw2.database.tableMakers.writerClasses;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import jelmer.gw2.sql.SQLStatementHelper;

public class SQLTransactionCurrentSellWriter extends SQLWriter implements SQLWriterInterface {

	public SQLTransactionCurrentSellWriter(JSONObject inputObject) throws SQLException {
		super(inputObject);
	}

	@Override
	public void run() {
		try {
			writeToDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeToDatabase() throws SQLException {
		try {
			String sql="INSERT IGNORE INTO transactionsCurrentSells(transactionID,id,price,quantity"
				+ ",created)"+"VALUES(?,?,?,?,?)";
			PreparedStatement pstmt= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			SQLStatementHelper.fillTransactionStatement(pstmt, listing);
			int row=pstmt.executeUpdate();
		}
		finally {
			conn.close();
		}
	}

}
