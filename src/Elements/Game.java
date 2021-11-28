package Elements;
public class Game {
	int CurrentX =20;
	int CurrentY =20;
	boolean flag = true;
	Box grid[][] = new Box[CurrentY][CurrentX];
	int current[][] = new int[CurrentY][CurrentX];
	int time_lapse; // Speed variable

	public Game() {
		for (int i = 0; i < CurrentY; i++) {
			for (int j = 0; j < CurrentX; j++) {
				grid[i][j] = new Box(i, j);
			}
		}
		time_lapse = 1000;

		System.out.println("Game() Called;");
		SetInitStates();
		System.out.println();
		System.out.println("Alive Initially: ");
		
		//PrintAlive();
		//Next();
		//System.out.println();
		//System.out.println("Alive After: ");
		//PrintAlive();

		//int alive = CountAlive(GetNeighbors(grid[150][150]));
		//System.out.println("Alive: " + CountAlive(GetNeighbors(grid[150][150])));
	}

	void SetInitStates() {
		grid[15][14].SetAlive();
		grid[15][15].SetAlive();

		grid[15][16].SetAlive();
		//grid[151][151].SetAlive();
	}

	public void Next()
	{
		System.out.println("Next() Called: \n");
		try
		{
			Thread.sleep(time_lapse);
		}
		catch(InterruptedException e) {
			// this part is executed when an exception (in this example InterruptedException) occurs
			System.out.println("Error in sleep \n");
		}
		while (flag)
		{
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
						grid[i][j].SetDead();
					else if(current[i][j] >= 4) // Over Population
						grid[i][j].SetDead();
					else if(current[i][j] == 2 || current[i][j] == 3)	//Populated
					{
						if (grid[i][j].GetState() == false && current[i][j] == 3)
						{
							grid[i][j].SetAlive();
						}
					}
				}
			}

			flag = false;
		}

	}
	public void ChangeSpeed(int i ) {
		if ((i>0)&&(time_lapse<2000)) {
			time_lapse += 100;
		}
		else if((i<0)&&(time_lapse>200)) {
			time_lapse -= 100;
		}
	}
	public void Reset() {
	
	}
	public void Counter() {
		
	}
	public void ViewSavedStates() {
		
	}
	public void DeleteStates() {
		
	}
	public void Draw() {
		
	}
	//-------------------------add flag for Interrupt
	void Play()
	{
		int ALive_Counter = 0;
		while (true)
		{
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

	void Print(Box box) 
	{
		System.out.println();
		System.out.println("X: " + box.GetX());
		System.out.println("X: " + box.GetY());
	}
}