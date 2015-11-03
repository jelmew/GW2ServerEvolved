package jelmer.gw2.database.tableMakers.writerClasses;

import java.sql.SQLException;

public interface SQLWriterInterface extends Runnable{

	public abstract void writeToDatabase() throws SQLException;
}