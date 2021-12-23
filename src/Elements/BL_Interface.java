package Elements;

import DB.DBInterface;
import Main.Abstract_Factory;

public interface BL_Interface {
	//Dummy function to set some initial states as alive
	void SetInitStates() ;
	
	//Creates Database object via factory
	public void CreateDB(String s1);
	
	public void Play(boolean flag,int arr[][],int speed);
	//Next Generation Created of an array sent to BL
	public void Next(int arr[][]);
	//Speed changed in the function of Play according to magnitude of integer
	public void ChangeSpeed(int i) ;
	//Resets all values of Grid to dead
	public void Reset(); 
	//View list and number of Saved states
	public int[] ViewSavedStates();
	//Loads a particular state of a particular SavedState
	public void LoadSaveStates(int s);
	//Delete a particular state from the ones saved
	public void DeleteStates(int s);
	
	
	public void setD_Fac(Abstract_Factory d_Fac);
	public int get_Counter();
	//Gets Neighbors of a particular box in a grid
	public void Set_Cell_Alive(int x, int y);
	public void Set_Cell_Dead(int x, int y);
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
