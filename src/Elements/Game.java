package Elements;
import org.json.*;
import DB.*;
import Main.Abstract_Factory;
import Main.DB_Factory;

public class Game implements BL_Interface {
	Counter currCounter;
	public Counter getCurrCounter() {
		return currCounter;
	}
	public void setCurrCounter(Counter currCounter) {
		this.currCounter = currCounter;
	}
	public int getCurrentX() {
		return CurrentX;
	}
	public void setCurrentX(int currentX) {
		CurrentX = currentX;
	}
	public int getCurrentY() {
		return CurrentY;
	}
	public void setCurrentY(int currentY) {
		CurrentY = currentY;
	}
	public int getTime_lapse() {
		return time_lapse;
	}
	public void setTime_lapse(int time_lapse) {
		this.time_lapse = time_lapse;
	}
	public DBInterface getObj() {
		return Obj;
	}
	public void setObj(DBInterface obj) {
		Obj = obj;
	}
	public int[] getBox_row() {
		return box_row;
	}
	public void setBox_row(int[] box_row) {
		this.box_row = box_row;
	}
	public int[] getBox_Column() {
		return box_Column;
	}
	public void setBox_Column(int[] box_Column) {
		this.box_Column = box_Column;
	}
	public int[][] getBOX() {
		return BOX;
	}
	public void setBOX(int[][] bOX) {
		BOX = bOX;
	}

	int CurrentX =300;//---------------------Width
	int CurrentY =200;//---------------------Height
	int time_lapse; // Speed variable
	DBInterface Obj;
	Box grid[][] = new Box[CurrentY][CurrentX];
	
	
	boolean flag = true;
	boolean PreReset[][] = new boolean[CurrentY][CurrentX];
	int current[][] = new int[CurrentY][CurrentX];
	String url = "jdbc:mysql://localhost:3306/db6?user=root&password=hanijani";
	String FilePath = "E:\\file.txt";	
	//String FilePath = "E:\\GUI_Proj\\file.txt";
	//String FilePath = "C:\\Users\\mg\\Desktop\\SDA Project\\GOL2\\src\\DB\\File.txt";
    // ------
	int[] box_row = new int[] {CurrentY};
	int[] box_Column = new int[]{CurrentX};
	int[][] BOX = new int[box_row[0]][box_Column[0]];//2D int array of BOX
	Abstract_Factory D_Fac;
	public Abstract_Factory getD_Fac() {
		return D_Fac;
	}
	@Override
	public void setD_Fac(JSONObject Fac_J) {
		try {
			D_Fac =(Abstract_Factory) Fac_J.get("Factory");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// d_Fac;
	}

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
	public void CreateDB(JSONObject Fac_J) {
		String s1= null;
		try {
			s1 = Fac_J.getString("DB_Type");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//D_Fac= new DB_Factory();
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
	public JSONObject get_Counter()
	{
		JSONObject s1=new JSONObject();
		try {
			s1.put("DeleteStates",currCounter.GetCounter());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s1;
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
	
	public JSONObject Next(JSONObject s1) {
//	    try {
//			System.out.println(s1.toString(4));
//		} catch (JSONException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		boolean flag = false;
		int arr[][];
		int speed =1000;
		
		try {
			speed=s1.getInt("Speed");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			flag=s1.getBoolean("Flag");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//arr= (int[][]) s1.stringToValue("Grid");
		arr=new int [200][300];
		//-=-----------------------------testing array----------------
		//JSONObject jsonObj = new JSONObject(s1);
		JSONArray jsonArry1 = null;
	    try {
			 jsonArry1 = s1.getJSONArray("Grid");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    for(int i = 0; i<200; i++){
	        JSONArray jsa1 = null;
			try {
				jsa1 = jsonArry1.getJSONArray(i);
//				System.out.print("\n-----------------------\n");
//				System.out.print(jsa1);
//				System.out.print("\n-----------------\n");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        for(int j = 0; j<jsa1.length();j++){
	            try {
					arr[i][j] = jsa1.getInt(j);
					//System.out.print(jsa1.getInt(j)+" ");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }


	    }
		//---------------------------------------------------------
		System.out.print("grid in bl: \n");
		//System.out.println(arr);
//		for (int i = 0; i < 200; i++) {
//	        for (int j = 0; j < 300; j++)
//	            System.out.print(arr[i][j] + " ");
//	        System.out.print("\n");
//	        }
		//public void Play(boolean flag,int arr[][],int speed)	
			
		
		
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
				
//							if(decide(i, j)==0)
//							{
//								grid[i][j].SetDead();
//								arr[i][j]=0;
//							}
//							if(decide(i, j)==1)
//							{
//								grid[i][j].SetAlive();
//								arr[i][j]=1;
//							}
					
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
	
	
			//create an json obejct to return
			JSONObject jsonobj = new JSONObject();
		    try {
		    	jsonobj.put("Speed",speed);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		    JSONArray jsonArray = new JSONArray();
		    for (int i=0;i<200;i++) {
		      JSONArray jarr = new JSONArray();
		      for (int j=0;j<300;j++) {
		        jarr.put(Integer.toString(arr[i][j])); // or some other conversion
		      }
		      jsonArray.put(jarr);
		    }
		    
		    try {
		    	jsonobj.put("Grid", jsonArray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jsonobj;
		}
		
	/*@Override
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
	}*/
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
	 @SuppressWarnings("static-access")
	@Override
	public JSONObject Play(JSONObject s1) {
//    try {
//		System.out.println(s1.toString(4));
//	} catch (JSONException e2) {
//		// TODO Auto-generated catch block
//		e2.printStackTrace();
//	}
	boolean flag = false;
	int arr[][];
	int speed =1000;
	
	try {
		speed=s1.getInt("Speed");
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		flag=s1.getBoolean("Flag");
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	//arr= (int[][]) s1.stringToValue("Grid");
	arr=new int [200][300];
	//-=-----------------------------testing array----------------
	//JSONObject jsonObj = new JSONObject(s1);
	JSONArray jsonArry1 = null;
    try {
		 jsonArry1 = s1.getJSONArray("Grid");
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    for(int i = 0; i<200; i++){
        JSONArray jsa1 = null;
		try {
			jsa1 = jsonArry1.getJSONArray(i);
//			System.out.print("\n-----------------------\n");
//			System.out.print(jsa1);
//			System.out.print("\n-----------------\n");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        for(int j = 0; j<jsa1.length();j++){
            try {
				arr[i][j] = jsa1.getInt(j);
				//System.out.print(jsa1.getInt(j)+" ");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }


    }
	//---------------------------------------------------------
	System.out.print("grid in bl: \n");
	//System.out.println(arr);
//	for (int i = 0; i < 200; i++) {
//        for (int j = 0; j < 300; j++)
//            System.out.print(arr[i][j] + " ");
//        System.out.print("\n");
//        }
	//public void Play(boolean flag,int arr[][],int speed)	
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
					
//						if(decide(i, j)==0)
//						{
//							grid[i][j].SetDead();
//							arr[i][j]=0;
//						}
//						if(decide(i, j)==1)
//						{
//							grid[i][j].SetAlive();
//							arr[i][j]=1;
//						}
						
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
		
		//create an json obejct to return
		JSONObject jsonobj = new JSONObject();
	    try {
	    	jsonobj.put("Speed",speed);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    JSONArray jsonArray = new JSONArray();
	    for (int i=0;i<200;i++) {
	      JSONArray jarr = new JSONArray();
	      for (int j=0;j<300;j++) {
	        jarr.put(Integer.toString(arr[i][j])); // or some other conversion
	      }
	      jsonArray.put(jarr);
	    }
	    
	    try {
	    	jsonobj.put("Grid", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
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
	public JSONObject ViewSavedStates() {
		
 		int arr[] = {};
        JSONObject FP = new JSONObject();
        
        try {
			FP.put("FilePath", DB_Path);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
 		JSONObject jsonobj=new JSONObject();
 		jsonobj = Obj.viewSaveId(FP);
 		JSONArray myarr=new JSONArray();
 		try {
			myarr=jsonobj.getJSONArray("Saved_IDs");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		arr=new int[myarr.length()];
 		for(int i=0;i<myarr.length();i++) {
			try {
				arr[i]=myarr.getInt(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 		JSONArray jsonArray = new JSONArray();
 		for(int i=0;arr[i]!=-2;i++)
	     jsonArray.put(Integer.toString(arr[i]));
	    
	    
	    try {
	    	jsonobj.put("SavedStates", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return jsonobj;
	}
	
	
	//----------------------------------LOAD STATES
	@Override
	public void LoadSaveStates(JSONObject s1){
		int[] Counter= new int[] {currCounter.GetCounter()};
		
		JSONObject Load_Obj = new JSONObject();
		JSONObject Save_C = new JSONObject();
	
		try {
			Load_Obj.put("Row", box_row[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Load_Obj.put("Column", box_Column[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Load_Obj.put("Counter", Counter[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Load_Obj.put("FilePath", DB_Path);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		int [][]myarr=this.BoxToInt();
		JSONArray jsonArray = new JSONArray();
	    for (int i=0;i<200;i++) {
	      JSONArray jarr = new JSONArray();
	      for (int j=0;j<300;j++) {
	        jarr.put(Integer.toString(myarr[i][j])); // or some other conversion
	      }
	      jsonArray.put(jarr);
	    }

	    try {
	    	Load_Obj.put("Grid", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		try {
//			Load_Obj.put("Grid", this.BoxToInt());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int s=-1;
		try {
			s=s1.getInt("LoadStates");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try {
			Load_Obj.put("Save_ID", s);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject Load_C=Obj.Load(Load_Obj);
	
		int x=1;
		try {
			x = Load_C.getInt("returnv");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (x==0) {
			System.out.println("Loaded!");
		}
		else {
			System.out.println("Error Loading!");
			//return this.BoxToInt();
		}
		currCounter.SetCounter(Counter[0]);
		
		JSONArray jsonArry1 = null;
        try {
    		 jsonArry1 = Load_C.getJSONArray("Grid");
    	} catch (JSONException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
        for(int i = 0; i<200; i++){
            JSONArray jsa1 = null;
    		try {
    			jsa1 = jsonArry1.getJSONArray(i);
//    			System.out.print("\n-----------------------\n");
//    			System.out.print(jsa1);
//    			System.out.print("\n-----------------\n");
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
            for(int j = 0; j<jsa1.length();j++){
                try {
                	BOX[i][j] = jsa1.getInt(j);
    				//System.out.print(jsa1.getInt(j)+" ");
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }
        }
        
		this.IntToBox(BOX);//Saved in Current BL Grid
		
		//return this.BoxToInt();
	}
	
	
	
	//-----------------------------DELETE STATES
	@Override
	public void DeleteStates(JSONObject s1){
		int s=-1;
		try {
			s=s1.getInt("DeleteStates");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject Remov = new JSONObject();
		try {
			Remov.put("save_id", s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Remov.put("FilePath", DB_Path);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Remov=Obj.RemoveSaveId(Remov);
		int x=0;
		try {
			x = Remov.getInt("returnv");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		JSONObject Save_Obj = new JSONObject();
		JSONObject Save_C = new JSONObject();
		try {
			Save_Obj.put("Row", box_row[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Save_Obj.put("Column", box_Column[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Save_Obj.put("Counter", Counter[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Save_Obj.put("FilePath", DB_Path);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int [][]myarr=this.BoxToInt();
		JSONArray jsonArray = new JSONArray();
	    for (int i=0;i<200;i++) {
	      JSONArray jarr = new JSONArray();
	      for (int j=0;j<300;j++) {
	        jarr.put(Integer.toString(myarr[i][j])); // or some other conversion
	      }
	      jsonArray.put(jarr);
	    }

	    try {
	    	Save_Obj.put("Grid", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
//		try {
//			Save_Obj.put("Grid", this.BoxToInt());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Save_C=Obj.Save(Save_Obj);
	    int Save_Check=1;
		try {
			Save_Check = Save_C.getInt("returnv");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	       if(Save_Check==0) {
				System.out.println("Grid with Counter: "+Counter[0]+" Saved!!");
			}
	       else {
	    	   System.out.println("Grid Could NOT BE Saved!!");
	       }
	}
	
	
	@Override
	public void Set_Cell_Dead(JSONObject XY)
	{
		int x=0,y=0;
		try {
			x=XY.getInt("X");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			y=XY.getInt("Y");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		grid[x][y].SetDead();
		System.out.printf("set dead at x = %d - y = %d \n",x,y);
	}
	@Override
	public void Set_Cell_Alive(JSONObject XY) {//set these cordinate box as alive
		int x=0,y=0;
		try {
			x=XY.getInt("X");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			y=XY.getInt("Y");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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