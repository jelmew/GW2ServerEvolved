package jelmer.gw2.database.tableThreads;

import java.util.concurrent.TimeUnit;

import jelmer.gw2.database.Gw2DatabaseManager;
import jelmer.gw2.database.tableMakers.TableFactory;
import jelmer.gw2.database.tableMakers.TableType;
import jelmer.gw2.database.tableMakers.tableTypes.Table;
public class commercePricesUpdate implements Runnable {
	
	private Gw2DatabaseManager connected;
	Table tableType=TableFactory.getTable(TableType.CommercePrices);
	public  commercePricesUpdate(Gw2DatabaseManager input) {
		this.connected=input;
	}

	@Override
	public void run() {
		connected.createTable(tableType);
		while(true) {
			connected.updateTable(tableType);
			try {
				Thread.sleep(TimeUnit.MINUTES.toMillis(15));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}

}
