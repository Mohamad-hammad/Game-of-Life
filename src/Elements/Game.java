package Elements;

import DB.*;
import Main.DB_Factory;

public class Game implements BL_Interface {
	Counter currCounter;
	int CurrentX =300;//---------------------Width
	int CurrentY =200;//---------------------Height
	boolean flag = true;
	Box grid[][] = new Box[CurrentY][CurrentX];
	boolean PreReset[][] = new boolean[CurrentY][CurrentX];
	int current[][] = new int[CurrentY][CurrentX];
	int time_lapse; // Speed variable
	
	
	String url = "jdbc:mysql://localhost:3306/db6?user=root&password=hanijani";
	String FilePath = "C:\\Users\\mg\\Desktop\\SDA Project\\GOL2\\src\\DB\\File.txt";	
	//String FilePath = "E:\\GUI_Proj\\file.txt";
    // ------
	int[] box_row = new int[] {CurrentY};
	int[] box_Column = new int[]{CurrentX};
	int[][] BOX = new int[box_row[0]][box_Column[0]];//2D int array of BOX
	DBInterface Obj;
	String DB_Path;
	
	public Game()
	{	//hardcoded
		//CreateDB("Text"); //hardcoded
		currCounter = new Counter();
		for (int i = 0; i < CurrentY; i++) {
			for (int j = 0; j < CurrentX; j++) {
				grid[i][j] = new Box(i, j);
			}
		}
		time_lapse = 1000;

		System.out.println("Game() Called;");
		//System.out.println();
		
		//System.out.println("Alive Initially: ");
		//PrintAlive();
		
		//Next();
		//System.out.println();
		//System.out.println("Alive After: ");
		//PrintAlive();

		//int alive = CountAlive(GetNeighbors(grid[150][150]));
		//System.out.println("Alive: " + CountAlive(GetNeighbors(grid[150][150])));
	}
	public void CreateDB(String s1) {
		DB_Factory D_Fac= new DB_Factory();
		Obj = D_Fac.getobj(s1);
		if (s1.equals("SQL")) {
			DB_Path = url;
		}
		else {
			DB_Path = FilePath;
		}
	}
	public void SaveForReset() {	//CALL this function before play functionality started
		for (int i=0; i<CurrentY; ++i) {
			for (int j=0; j<CurrentX; ++j) {
				PreReset[i][j]=grid[i][j].GetState();
			}
		}
	}
    public void Reset_Counter()
    {
    	currCounter.ResetCounter();
    }
	public void Reset_States(int arr[][])
	{
		for (int i = 0; i < CurrentY; i++)
		{
			for (int j = 0; j < CurrentX; j++)
			{
				if (PreReset[i][j]) {
					arr[i][j]=1;
					grid[i][j].SetAlive();
				}
				else {
					arr[i][j]=0;
					grid[i][j].SetDead();
				}
				
			}
		}
		Reset_Counter();	//Generations will go back to 0
	}
	public int get_Counter()
	{
		return currCounter.GetCounter();
	}
	@Override
	public void SetInitStates() {
		/*
		for (int j=0; j<30; ++j) {
			for (int i=5; i<CurrentX-5;++i) {
				grid[2+(10*j)][i].SetAlive();
			}
		}
		*/
	
		for (int j=0; j<CurrentY; ++j) {
			for (int i=0; i<CurrentX;++i) {
				grid[j][i].SetAlive();
				
				
			}
		}
	
	}

	@Override
	public void Next(int arr[][])
	{	
		System.out.println("Next() Called: \n");
//		try
//		{
//			Thread.sleep(time_lapse);
//		}
//		catch(InterruptedException e) {
//			// this part is executed when an exception (in this example InterruptedException) occurs
//			System.out.println("Error in sleep \n");
//		}
		
		for (int i = 1; i < CurrentY-1; i++)
		{
			for (int j = 1; j < CurrentX-1; j++)
			{
				current[i][j] = CountAlive(GetNeighbors(grid[i][j]));
			}
		}
		for (int i = 1; i < CurrentY-1; i++)
		{ 
			for (int j = 1; j < CurrentX-1; j++)
			{
				if (current[i][j] == 1 || current[i][j] == 0) // Under Population
				{
					arr[i][j]=0;
					grid[i][j].SetDead();
				}
				else if(current[i][j] >= 4) // Over Population
				{
					arr[i][j]=0;
					grid[i][j].SetDead();
				}
				else if(current[i][j] == 2 || current[i][j] == 3)	//Populated
				{
					if (grid[i][j].GetState() == false && current[i][j] == 3)
					{
						arr[i][j]=1;
						grid[i][j].SetAlive();
					}
				}
			}
		}

			
		currCounter.IncCounter();
	}
	private int getneighbours(int i,int j)
	{
		 int neighbors = 0;
	       
	        if(j > 0){
	            if(grid[i][j-1].GetState()==true) neighbors++;
	            if(i>0) if(grid[i-1][j-1].GetState()==true) neighbors++;
	            if(i<CurrentY-1) if(grid[i+1][j-1].GetState()==true) neighbors++;
	        }
	        if(j < CurrentX-1){
	            if(grid[i][j+1].GetState()==true) neighbors++;
	            if(i>0) if(grid[i-1][j+1].GetState()==true) neighbors++;
	            if(i<CurrentY-1) if(grid[i+1][j+1].GetState()==true) neighbors++;
	        }
	        if(i>0) if(grid[i-1][j].GetState()==true) neighbors++;
	        if(i<CurrentY-1) if(grid[i+1][j].GetState()==true) neighbors++;
	        return neighbors;
	}
	 private int decide(int i, int j){
	        int neighbors = 0;
	       
	        if(j > 0){
	            if(grid[i][j-1].GetState()==true) neighbors++;
	            if(i>0) if(grid[i-1][j-1].GetState()==true) neighbors++;
	            if(i<CurrentY-1) if(grid[i+1][j-1].GetState()==true) neighbors++;
	        }
	        if(j < CurrentX-1){
	            if(grid[i][j+1].GetState()==true) neighbors++;
	            if(i>0) if(grid[i-1][j+1].GetState()==true) neighbors++;
	            if(i<CurrentY-1) if(grid[i+1][j+1].GetState()==true) neighbors++;
	        }
	        if(i>0) if(grid[i-1][j].GetState()==true) neighbors++;
	        if(i<CurrentY-1) if(grid[i+1][j].GetState()==true) neighbors++;
	        if(neighbors == 3) return 1;
	        if(grid[i][j].GetState()==true && neighbors == 2) return 1;
	        return 0;
	    }
	@Override
	public void Play(boolean flag,int arr[][],int speed)
	{	
		if(flag)
		{
			System.out.println("Next() Called: \n");
			try
			{
				Thread.sleep(speed*100);
			}
			catch(InterruptedException e) {
				// this part is executed when an exception (in this example InterruptedException) occurs
				System.out.println("Error in sleep \n");
			}
			
			for (int i = 0; i < CurrentY; i++)
			{
				for (int j = 0; j < CurrentX; j++)
				{
					current[i][j] = getneighbours(i,j);
				}
			}
			for (int i = 0; i < CurrentY; i++)
			{ 
				for (int j = 0; j < CurrentX; j++)
				{
					
//					if(decide(i, j)==0)
//					{
//						grid[i][j].SetDead();
//						arr[i][j]=0;
//					}
//					if(decide(i, j)==1)
//					{
//						grid[i][j].SetAlive();
//						arr[i][j]=1;
//					}
						
					if (current[i][j] == 1 || current[i][j] == 0) // Under Population
					{
						arr[i][j]=0;
						grid[i][j].SetDead();
					}
					else if(current[i][j] >= 4) // Over Population
					{
						arr[i][j]=0;
						grid[i][j].SetDead();
					}
					else if(current[i][j] == 2 || current[i][j] == 3)	//Populated
					{
						if (grid[i][j].GetState() == false && current[i][j] == 3)
						{
							arr[i][j]=1;
							grid[i][j].SetAlive();
						}
					}
				}
			}
	
				
			currCounter.IncCounter();
		}
		else
		{
			for (int i = 0; i < CurrentY; i++)
			{ 
				for (int j = 0; j < CurrentX; j++)
				{
					arr[i][j]=grid[i][j].GetState()? 1 : 0;
				}
			}
				
		}
	}
	@Override
	public void ChangeSpeed(int i ) {
		if ((i>0)&&( (time_lapse-(i*100)) > 200)) {
			time_lapse -= i*100;
		}
		else if((i<0)&&( (time_lapse-(i*100)) < 2200)) {
			time_lapse -= i*100;
		}
	}
	@Override
	public void Reset() {
		currCounter.ResetCounter();
		time_lapse=1000;
		for (int i = 0; i < CurrentY; i++) {
			for (int j = 0; j < CurrentX; j++) {
				grid[i][j] = new Box(i, j);
			}
		}
		System.out.println("Reset() Called;");
			
	}
	
	//-----------------------------VIEW SAVE STATES
	@Override
	public int[] ViewSavedStates() {
		
 		int arr[] = Obj.viewSaveId(DB_Path);
        /*
 		for (int i = 0; arr[i]!=-2; i++) {
        	System.out.println("Save ID " + (i+1) +": "+arr[i]);//Can be commented
        }
 		*/
 		
		return arr;
	}
	
	
	//----------------------------------LOAD STATES
	@Override
	public void LoadSaveStates(int s){
		int[] Counter= new int[] {currCounter.GetCounter()};
		int x=Obj.Load(box_row, box_Column, BOX, s, Counter, DB_Path);
		if (x==0) {
			System.out.println("Loaded!");
		}
		else {
			System.out.println("Error Loading!");
			//return this.BoxToInt();
		}
		currCounter.SetCounter(Counter[0]);
		this.IntToBox(BOX);//Saved in Current BL Grid
		
		//return this.BoxToInt();
	}
	
	
	
	//-----------------------------DELETE STATES
	@Override
	public void DeleteStates(int s){
		int x=Obj.RemoveSaveId(s,DB_Path);
	       if (x==0) {
				System.out.println("Removed!");
			}
			else {
				System.out.println("Could Not Remove!");
			}
	}
	
	
	
	//-----------------------SAVE-----------------
	@Override
	public void SaveState() {
		int[] Counter= new int[] {currCounter.GetCounter()};
		int Save_Check=Obj.Save(box_row, box_Column, this.BoxToInt(),Counter, DB_Path);
	       
	       if(Save_Check==0) {
				System.out.println("Grid with Counter: "+Counter[0]+" Saved!!");
			}
	       else {
	    	   System.out.println("Grid Could NOT BE Saved!!");
	       }
	}
	
	
	@Override
	public void Set_Cell_Dead(int x , int y)
	{
		grid[x][y].SetDead();
		System.out.printf("set dead at x = %d - y = %d \n",x,y);
	}
	@Override
	public void Set_Cell_Alive(int x, int y) {//set these cordinate box as alive
		grid[x][y].SetAlive();
		System.out.printf("set alive at x = %d - y = %d \n",x,y);
	}
	//-------------------------add flag for Interrupt
	@Override
	public void Play(boolean flag)
	{
		System.out.println("Play() Called: \n");
		int ALive_Counter = 0;
		for (int i = 1; i < CurrentY-1; i++)
		{
			for (int j = 1; j < CurrentX-1; j++)
			{
				ALive_Counter =  CountAlive(GetNeighbors(grid[i][j]));
				if (ALive_Counter == 1 || ALive_Counter == 0) // Under Population
					grid[i][j].SetDead();
				else if(ALive_Counter >= 4) // Over Population
					grid[i][j].SetDead();
				else if(ALive_Counter == 2 || ALive_Counter == 3)	//Populated
				{
					if (grid[i][j].GetState() == false && ALive_Counter == 3)
					{
						grid[i][j].SetAlive();
					}
				}
			}
		}

		try
		{
			Thread.sleep(time_lapse);
		}
		catch(InterruptedException e) {
			// this part is executed when an exception (in this example InterruptedException) occurs
			System.out.println("Error in sleep \n");
		}
		currCounter.IncCounter();
		

	}

	@Override
	public int[][] GetNeighbors(Box box)
	{
		int[][] neighbors = new int[8][2];
		neighbors[0][0] = box.GetX()-1;
		neighbors[0][1] = box.GetY()-1;

		neighbors[1][0] = box.GetX()-1;
		neighbors[1][1] = box.GetY();

		neighbors[2][0] = box.GetX()-1;
		neighbors[2][1] = box.GetY()+1;

		neighbors[3][0] = box.GetX();
		neighbors[3][1] = box.GetY()-1;

		neighbors[4][0] = box.GetX();
		neighbors[4][1] = box.GetY()+1;

		neighbors[5][0] = box.GetX()+1;
		neighbors[5][1] = box.GetY()-1;

		neighbors[6][0] = box.GetX()+1;
		neighbors[6][1] = box.GetY();

		neighbors[7][0] = box.GetX()+1;
		neighbors[7][1] = box.GetY()+1;

		return neighbors;

	}

	@Override
	public int CountAlive(int[][] neighbors)
	{
		int count = 0;
		int a,b;
		for (int i = 0; i < 8; i++)
		{
			if(neighbors[i][0] != -1)
			{
				a = neighbors[i][0];
				b = neighbors[i][1];
				if (grid[a][b].GetState() == true)
				{
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public void PrintAlive() 
	{
		for (int i = 0; i < CurrentY; i++) {
			for (int j = 0; j < CurrentX; j++) {
				if (grid[i][j].GetState() == true) {
					System.out.println();
					System.out.println("X: " + i);
					System.out.println("Y: " + j);
				}
			}
		}
	}

	@Override
	public int[][] BoxToInt()
	{
		int grid_int[][] = new int[CurrentY][CurrentX];
		for (int i = 1; i < CurrentY-1; i++)
		{
			for (int j = 1; j < CurrentX-1; j++)
			{
				if (grid[i][j].GetState() == true)
				{
					grid_int[i][j] = 1;
				}
				else
				{
					grid_int[i][j] = 0;
				}
			}
		}
		return grid_int;
	}
	
	@Override
	public void IntToBox(int grid_int[][])
	{
		for (int i = 1; i < CurrentY-1; i++)
		{
			for (int j = 1; j < CurrentX-1; j++)
			{
				if (grid_int[i][j] == 1)
				{
					grid[i][j].SetAlive();
				}
				else
				{
					grid[i][j].SetDead();
				}
			}
		}
	}
	
	@Override
	public void Print(Box box) 
	{
		System.out.println();
		System.out.print("X: " + box.GetX());
		System.out.print("X: " + box.GetY());
	}
}