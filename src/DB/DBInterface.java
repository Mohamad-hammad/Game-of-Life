package DB;

import java.sql.SQLException;

public interface  DBInterface {
	
    public int 	 Save(int Box_Row[], int Box_Column[], int Box[][], int Counter[],String FilePath ); // interface method (does not have a body)
    public int 	 Load(int Box_Row[], int Box_Column[], int Box[][], int save_id, int Counter[],String FilePath ); // interface method (does not have a body)
    public int[] viewSaveId(String FilePath );
    public int	 RemoveSaveId(int save_id,String FilePath);	  
    public int getCurrentSaveID(String FilePath) throws SQLException;
    public boolean CheckFileEmpty(String FilePath);
}
