package jelmer.gw2.database.tableMakers.writerClasses;

import java.sql.Statement;
import java.sql.Types;

import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLitemWriter extends SQLWriter implements SQLWriterInterface {

	
	public SQLitemWriter(JSONObject inputObject) throws SQLException {
		super(inputObject);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			writeToDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToDatabase() throws SQLException {
		String sql="INSERT IGNORE INTO items(id,name,icon,description)"+"VALUES(?,?,?,?)";
		@SuppressWarnings("unused")
		ResultSet rs=null;
		//int id=0;
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, listing.getInt("id"));
			pstmt.setString(2, listing.getString("name"));
			pstmt.setString(3, listing.getString("icon"));
			if(listing.has("description")) {
				pstmt.setString(4, listing.getString("description"));
			} else {
				pstmt.setNull(4, Types.VARCHAR);
			}
			int row=pstmt.executeUpdate();
			if(row==1) {
				rs=pstmt.getGeneratedKeys();
			}
		}
		finally {
		conn.close();	
		}
		
	}

}
