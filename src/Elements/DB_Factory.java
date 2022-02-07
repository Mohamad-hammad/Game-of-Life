package Elements;
import DB.*;

public class DB_Factory {
	public DBInterface getDB(String s1) {
		DBInterface Obj = null;
		if (s1.equals("SQL")) {
			Obj = new ReadFromDB();
		}
		else if (s1.equals("Text")) {
			Obj = new ReadFromText();
		}
		return Obj;
	}
}
