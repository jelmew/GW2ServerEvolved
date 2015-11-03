package jelmer.gw2.database.tableMakers.writerClasses;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import jelmer.gw2.sql.SQLStatementHelper;




public class SQLTransactionHistoryBuyWriter extends SQLWriter implements SQLWriterInterface {

	public  SQLTransactionHistoryBuyWriter(JSONObject inputTransaction) throws SQLException {
		super(inputTransaction);
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
			String sql="INSERT IGNORE INTO transactionsHistoryBuys(transactionID,id,price,quantity"
				+ ",created,purchased)"+"VALUES(?,?,?,?,?,?)";
			PreparedStatement pstmt= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			SQLStatementHelper.fillTransactionStatement(pstmt, listing);
			int row=pstmt.executeUpdate();
		}
		finally {
			conn.close();
		}
	}	
}
