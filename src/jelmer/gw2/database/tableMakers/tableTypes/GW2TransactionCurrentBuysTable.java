package jelmer.gw2.database.tableMakers.tableTypes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;

import jelmer.gw2.database.tableMakers.writerClasses.SQLTransactionCurrentBuyWriter;
import jelmer.gw2.jdbc.ConnectionPoolManager;
import jelmer.gw2.json.GW2Reader;
import static jelmer.gw2.sql.SQLStatementHelper.*;

public class GW2TransactionCurrentBuysTable implements Table {

	@Override
	public void create() throws SQLException {
		String sql="CREATE TABLE IF NOT EXISTS transactionsCurrentBuys (transactionID INT(6) UNSIGNED PRIMARY KEY, id INT(6), price INT(6),"
				+ " quantity INT(3), created DATETIME);";
		createStatementExecutor(sql, ConnectionPoolManager.getConnection());
	}

	@Override
	public void update() throws SQLException {
		ExecutorService ex=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());  
		try {
			JSONArray transactionHistoryBuys = GW2Reader.getCommerceTransactionsCurrentBuysFromApiKey(apiKey);
			for(int i=0; i<transactionHistoryBuys.length();i++) {
				ex.execute(new SQLTransactionCurrentBuyWriter(transactionHistoryBuys.getJSONObject(i)));
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}finally {
			ex.shutdown();
		}
	}

}
