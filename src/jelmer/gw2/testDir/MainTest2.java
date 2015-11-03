package jelmer.gw2.testDir;

import java.sql.SQLException;

import jelmer.gw2.database.Gw2DatabaseManager;
import jelmer.gw2.database.tableThreads.commercePricesUpdate;
import jelmer.gw2.database.tableThreads.itemsUpdate;
import jelmer.gw2.database.tableThreads.transactionCurrentBuysUpdate;
import jelmer.gw2.database.tableThreads.transactionCurrentSellsUpdate;
import jelmer.gw2.database.tableThreads.transactionHistoryBuysUpdate;
import jelmer.gw2.database.tableThreads.transactionHistorySellsUpdate;

public class MainTest2 {
	
	public static void main(String[] args) {
		Gw2DatabaseManager test = null;
		try {
			test= new Gw2DatabaseManager();
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
			test.createGw2Database();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Thread items= new Thread(new itemsUpdate(test));
		Thread commercePrices= new Thread(new commercePricesUpdate(test));
		Thread transactionCurreBuys= new Thread(new transactionCurrentBuysUpdate(test));
		Thread transactionCurrentSells=new Thread(new transactionCurrentSellsUpdate(test));
		Thread transactionHistoryBuys= new Thread(new transactionHistoryBuysUpdate(test));
		Thread transactionHistorySells = new Thread(new transactionHistorySellsUpdate(test));

		items.start();
		commercePrices.start();
		transactionCurreBuys.start();
		transactionCurrentSells.start();
		transactionHistoryBuys.start();
		transactionHistorySells.start();
		
		try {
			items.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
