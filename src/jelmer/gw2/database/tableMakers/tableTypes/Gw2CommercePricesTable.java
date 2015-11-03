package jelmer.gw2.database.tableMakers.tableTypes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;

import jelmer.gw2.database.tableMakers.writerClasses.SQLCommercePricesWriter;
import jelmer.gw2.jdbc.ConnectionPoolManager;
import jelmer.gw2.json.GW2Reader;
import jelmer.gw2.sql.SQLStatementHelper;

public class Gw2CommercePricesTable implements Table {

	@Override
	public void create() throws SQLException {
		String sql="CREATE TABLE IF NOT EXISTS commercePrices( id INT(6) UNSIGNED PRIMARY KEY, buyQuantity INT(10), buyUnit_Price INT(10),"
				+ " sellQuantity INT(10), sellUnit_Price INT(10))";
		SQLStatementHelper.createStatementExecutor(sql, ConnectionPoolManager.getConnection());
				

	}

	@Override
	public void update() throws SQLException {
		ExecutorService ex= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		ArrayList<Long> idList= new ArrayList<>();
		try {
			JSONArray pricesIdListJSONArray=GW2Reader.getListOfAllTpIds();
			for(int i=0; i<pricesIdListJSONArray.length();i++) {
				idList.add(pricesIdListJSONArray.getLong(i));
			}
		} catch(JSONException|IOException e) {
			e.printStackTrace();
		}
		try {
			for(int i=0; i<idList.size(); i+=stepSize) {
				int start=i;
				int end=i+stepSize;
				if(end>idList.size()) end=idList.size();
				JSONArray tpPricesSubListJSONArray=GW2Reader.getTpPricesFromID(idList.subList(start, end));
				for(int j=0; j<tpPricesSubListJSONArray.length(); j++) {
					ex.execute(new SQLCommercePricesWriter(tpPricesSubListJSONArray.getJSONObject(j)));
				}
			}
		} catch(JSONException|IOException e) {
			e.printStackTrace();
		} finally {
			ex.shutdown();
		}
	}

}
