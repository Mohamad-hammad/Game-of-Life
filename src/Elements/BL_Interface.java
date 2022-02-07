package Elements;
import org.json.*;
import DB.DBInterface;
import Main.Abstract_Factory;

public interface BL_Interface {
	//Dummy function to set some initial states as alive
	void SetInitStates() ;
	
	//Creates Database object via factory
	public void CreateDB(JSONObject Fac_J);
	
	public JSONObject Play(JSONObject s1);
	//Next Generation Created of an array sent to BL
	public JSONObject Next(JSONObject s1);
	//Speed changed in the function of Play according to magnitude of integer
	public void ChangeSpeed(int i) ;
	//Resets all values of Grid to dead
	public void Reset(); 
	//View list and number of Saved states
	public JSONObject ViewSavedStates();
	//Loads a particular state of a particular SavedState
	public void LoadSaveStates(JSONObject s2);
	//Delete a particular state from the ones saved
	public void DeleteStates(JSONObject s2);
	
	
	public void setD_Fac(JSONObject Fac_J);
	public JSONObject get_Counter();
	//Gets Neighbors of a particular box in a grid
	public void Set_Cell_Alive(JSONObject XY);
	public void Set_Cell_Dead(JSONObject XY);
	public void Reset_States(int arr[][]);
	//Resets Counter=0, when new game started -- every time Play Button is pressed
	public void Reset_Counter();
	//Generations produced with a time_lapse until pause button pressed
	void Play(boolean flag);
	
	int[][] GetNeighbors(Box box);
	//Change existing grid to int[][] type and return
	public int[][] BoxToInt();
	//Change Int 2D array to Box 2D array and add into grid
	public void IntToBox(int grid_int[][]);
	//Count Alive Neighbors of a box
	int CountAlive(int[][] neighbors);
	//Print Alive Boxes of a Grid
	public void PrintAlive() ;
	//Print the status of a Box
	void Print(Box box);

	void SaveState();


}
