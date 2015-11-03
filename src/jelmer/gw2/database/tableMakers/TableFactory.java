package jelmer.gw2.database.tableMakers;
import jelmer.gw2.database.tableMakers.tableTypes.*;

public class TableFactory {
	
	public static Table getTable(TableType type) {
		switch(type) {
		case GW2Items: return new Gw2IdItemsTable();
		case GW2TransactionHistoryBuys: return new Gw2TransactionHistoryBuysTable();
		case GW2TransactionHistorySells: return new GW2TransactionHistorySellsTable();
		case GW2TransactionCurrentBuys: return new GW2TransactionCurrentBuysTable();
		case GW2TransactionCurrentSells: return new GW2TransactionCurrentSellsTable();
		case CommercePrices:	return new Gw2CommercePricesTable();
		default: throw new ExceptionInInitializerError(); 
		}
	}

}
