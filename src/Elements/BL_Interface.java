package Elements;

public interface BL_Interface {
	void SetInitStates() ;

	public void Next();
	
	
	public void ChangeSpeed(int i) ;
	public void Reset(); 
	public void ViewSavedStates();
	public void DeleteStates();
	public void Draw(int x, int y);
	//-------------------------add flag for Interrupt
	void Play(boolean flag);
	
	int[][] GetNeighbors(Box box);
	

	int CountAlive(int[][] neighbors);

	public void PrintAlive() ;

	void Print(Box box);

}
