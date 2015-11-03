package jelmer.gw2.testDir;

import java.sql.SQLException;

import jelmer.gw2.database.Gw2DatabaseManager;
import jelmer.gw2.database.tableMakers.TableFactory;
import jelmer.gw2.database.tableMakers.TableType;

public class MainTest {

	public static void main(String[] args) {
		Gw2DatabaseManager test=null;
		try {
			test= new Gw2DatabaseManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		try {
			test.createGw2Database();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.createTable(TableFactory.getTable(TableType.GW2TransactionHistoryBuys));
		test.updateTable(TableFactory.getTable(TableType.GW2TransactionHistoryBuys));
		test.createTable(TableFactory.getTable(TableType.GW2TransactionHistorySells));
		test.updateTable(TableFactory.getTable(TableType.GW2TransactionHistorySells));
		test.createTable(TableFactory.getTable(TableType.GW2TransactionCurrentBuys));
		test.updateTable(TableFactory.getTable(TableType.GW2TransactionCurrentBuys));
		test.createTable(TableFactory.getTable(TableType.GW2TransactionCurrentSells));
		test.updateTable(TableFactory.getTable(TableType.GW2TransactionCurrentSells));
		test.createTable(TableFactory.getTable(TableType.CommercePrices));
		test.updateTable(TableFactory.getTable(TableType.CommercePrices));
		System.out.println("Time for items");
		test.createTable(TableFactory.getTable(TableType.GW2Items));
		test.updateTable(TableFactory.getTable(TableType.GW2Items));
		}

}
