package Elements;

public class Game {
	boolean flag = true;
	Box grid[][] = new Box[302][402];
	int time_lapse; // Speed variable

	public Game() {
		for (int i = 0; i < 302; i++) {
			for (int j = 0; j < 402; j++) {
				grid[i][j] = new Box(i, j);
			}
		}
		System.out.println("Game() Called;");
		SetInitStates();		
		System.out.println();
		System.out.println("Alive Before: ");
		PrintAlive();
		Play();
		System.out.println();
		System.out.println("Alive After: ");
		PrintAlive();
		
		//int alive = CountAlive(GetNeighbors(grid[150][150]));
		//System.out.println("Alive: " + CountAlive(GetNeighbors(grid[150][150])));
	}

	void SetInitStates() {
		grid[150][150].SetAlive();
		grid[150][151].SetAlive();

		grid[151][150].SetAlive();
		//grid[151][151].SetAlive();
	}

	
	  void Play() 
	  { 
		  int ALive_Counter = 0; 
		  while (flag)
		  { 
			  for (int i = 1; i < 301; i++) 
			  { 
				  for (int j = 1; j < 401; j++) 
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
			  
			  flag = false;
		  }
		  
	  } 
		  
		  
	  
	 
	int[][] GetNeighbors(Box box)
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
	  
		if (box.IsLeft() && box.IsTop())
		{ 
			neighbors[0][0] = -1; 
			neighbors[1][0] = -1;
			neighbors[2][0] = -1; 
			neighbors[3][0] = -1;
			neighbors[5][0] = -1; }
		else if (box.IsRight(401) && box.IsTop())
		{
			neighbors[0][0] = -1; 
			neighbors[1][0] = -1;
			neighbors[2][0] = -1;
			neighbors[4][0] = -1;
			neighbors[7][0] = -1;
		}
		else if (box.IsLeft() && box.IsBottom(301))
		{ 
			neighbors[0][0] = -1;
			neighbors[3][0] = -1;
			neighbors[5][0] = -1;
			neighbors[6][0] = -1;
			neighbors[7][0] = -1;
		}
	  
		else if (box.IsRight(401) && box.IsBottom(301)) 
		{ 
			neighbors[2][0] = -1;
			neighbors[4][0] = -1; 
			neighbors[5][0] = -1; 
			neighbors[6][0] = -1;
			neighbors[7][0] = -1;
		}
	  
		else if (box.IsTop()) 
		{ 
			neighbors[0][0] = -1;
			neighbors[1][0] = -1;
			neighbors[2][0] = -1;
		}
	  
		else if (box.IsLeft()) 
		{ 
			neighbors[0][0] = -1; 
			neighbors[3][0] = -1;
			neighbors[5][0] = -1; 
		}
	  
		if (box.IsRight(401))
		{
			neighbors[2][0] = -1; 
			neighbors[4][0] = -1;
			neighbors[8][0] = -1; 
		}
	  
		else if (box.IsBottom(301)) 
		{ 
			neighbors[5][0] = -1; 
			neighbors[6][0] = -1;
			neighbors[7][0] = -1; 
		}
	  
		return neighbors; 
	  
	} 
	
	int CountAlive(int[][] neighbors) 
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

	void PrintAlive() {
		for (int i = 1; i < 301; i++) {
			for (int j = 1; j < 401; j++) {
				if (grid[i][j].GetState() == true) {
					System.out.println();
					System.out.println("X: " + i);
					System.out.println("Y: " + j);
				}
			}
		}
	}

	void Print(Box box) {
		System.out.println();
		System.out.println("X: " + box.GetX());
		System.out.println("X: " + box.GetY());
	}
}