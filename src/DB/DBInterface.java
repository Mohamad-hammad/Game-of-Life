package DB;

import java.sql.SQLException;

public interface DBInterface {
    public void Save(int Box_Row[], int Box_Column[], int Box[][], int save_id, int Counter[],String FilePath )throws SQLException; // interface method (does not have a body)
    public void Load(int Box_Row[], int Box_Column[], int Box[][], int save_id, int Counter[],String FilePath )throws SQLException; // interface method (does not have a body)
    public int[] viewSaveId(String FilePath )throws SQLException;
}