package jelmer.gw2.database.tableMakers.writerClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import com.mysql.jdbc.Statement;

public class SQLCommercePricesWriter extends SQLWriter implements SQLWriterInterface {

	public SQLCommercePricesWriter(JSONObject inputObject) throws SQLException {
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
		String sql="INSERT INTO commercePrices(id,buyQuantity, buyUnit_Price, sellQuantity,sellUnit_Price) VALUES(?,?,?,?,?)"+
				"ON DUPLICATE KEY UPDATE buyQuantity=VALUES(buyQuantity), buyUnit_Price=VALUES(buyUnit_Price),"
				+ " sellQuantity=VALUES(sellquantity)+ sellUnit_Price=VALUES(sellUnit_Price)";
		//String sql="INSERT IGNORE INTO commercePrices(id,buyQuantity, buyUnit_Price, sellQuantity,sellUnit_Price)"+"VALUES(?,?,?,?,?)";
		ResultSet rs=null;
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, listing.getInt("id"));
			pstmt.setLong(2, listing.getJSONObject("buys").getLong("quantity"));
			pstmt.setLong(3, listing.getJSONObject("buys").getLong("unit_price"));
			pstmt.setLong(4, listing.getJSONObject("sells").getLong("quantity"));
			pstmt.setLong(5, listing.getJSONObject("sells").getLong("unit_price"));
			int row=pstmt.executeUpdate();
			if(row==1) rs=pstmt.getGeneratedKeys();
		}finally {
			conn.close();
		}
	}

}
