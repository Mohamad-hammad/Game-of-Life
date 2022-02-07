package DB;

import java.sql.SQLException;

import org.json.JSONObject;

public interface  DBInterface {
	
	 public JSONObject Save(JSONObject Save_Obj); // interface method (does not have a body)
	 public JSONObject Load(JSONObject Save_Obj); // interface method (does not have a body)
    public JSONObject viewSaveId	(JSONObject FP);
    public JSONObject RemoveSaveId(JSONObject Remov);	  
    public int getCurrentSaveID(String FilePath) throws SQLException;
    public boolean CheckFileEmpty(String FilePath);
}
