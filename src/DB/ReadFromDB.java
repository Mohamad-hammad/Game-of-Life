package DB;
import java.sql.*;
import org.json.*;


public class ReadFromDB implements DBInterface{
    
    public JSONObject viewSaveId(JSONObject FP){
    	JSONObject jsonobj = new JSONObject();
    	String FilePath=null;
		try {
			FilePath = FP.getString("FilePath");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	int arr[]=new int[100];
        
        int i=0;

        Connection connection = null;

		try {
			connection = DriverManager.getConnection(FilePath);
		
        String st1 = "select Save_id from Save_State;";
        
        Statement statement1;
  
	    statement1 = connection.createStatement();
			
	    ResultSet result = statement1.executeQuery(st1);
		    
	    while(result.next()){
	    	arr[i++] = result.getInt("Save_id");
	    }
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arr[0]=-1;
			arr[1]=-2;
			 
            
		}
		
		arr[i]=-2;
		try {
			jsonobj.put("Saved_IDs", arr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //return arr;
		 return jsonobj;
    }
    
    //public JSONObject RemoveSaveId(int save_id,String FilePath) 
    public JSONObject RemoveSaveId(JSONObject Remov) {
    	int save_id=0;
    	String FilePath=null;
    	try {
			save_id = Remov.getInt("save_id");
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	try {
			FilePath = Remov.getString("FilePath");
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	JSONObject retObj = new JSONObject();
        Connection connection = null;
        int returnv=0;

		try {
			
		connection = DriverManager.getConnection(FilePath);
		
        String st1 = "select RemoveSaveState("+save_id+") as Rstatus;";
        
        Statement statement1;
  
	    statement1 = connection.createStatement();
			
	    ResultSet result = statement1.executeQuery(st1);
		    
	    while(result.next()){
	    	returnv = result.getInt("Rstatus");
	    }
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnv=-1;
			try {
				retObj.put("returnv", returnv);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return retObj;
			//return returnv;
		}
		try {
			retObj.put("returnv", returnv);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //return returnv;
		return retObj;

    }
    @Override
    public boolean CheckFileEmpty(String FilePath) {
    	return true;
    }
    
    @Override
    public int getCurrentSaveID(String FilePath) throws SQLException { 

        int Saveid = 0;
        
        Connection connection = null;

		connection = DriverManager.getConnection(FilePath);
		
        String st1 = "select max(Save_id) from Save_State;";
        
        Statement statement1;
  
	    statement1 = connection.createStatement();
			
	    ResultSet result = statement1.executeQuery(st1);
		    
	    while(result.next()){
	    	Saveid = result.getInt("max(Save_id)");
	    }
		
        return Saveid;
    }

    public JSONObject Load(JSONObject Save_Obj){
    	JSONObject retObj = new JSONObject();
    	int save_id=0;
    	try {
    		save_id= Save_Obj.getInt("Save_ID");
		} catch (JSONException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}  
    	int Box_Row[]= {} ;
    	try {
			Box_Row[0]= Save_Obj.getInt("Row");
		} catch (JSONException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}      
    	
    	int Box_Column[]= {} ;
    	try {
			Box_Column[0]= Save_Obj.getInt("Column");
		} catch (JSONException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}      
    	
    	int Counter[]= {} ;
    	try {
			Counter[0]= Save_Obj.getInt("Counter");
		} catch (JSONException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}      
    	
    	String FilePath= null;
		try {
			FilePath = Save_Obj.getString("FilePath");
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}      
    	
    	
    	int Box[][];
		//Box= (int[][]) s1.stringToValue("Grid");
		Box=new int [200][300];
		//-=-----------------------------testing array----------------
		//JSONObject jsonObj = new JSONObject(s1);
		JSONArray jsonArry1 = null;
	    try {
			 jsonArry1 = Save_Obj.getJSONArray("Grid");
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
					Box[i][j] = jsa1.getInt(j);
					//System.out.print(jsa1.getInt(j)+" ");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }


	    }
    	Connection connection = null;
		int ith=0;
    	try {
			connection = DriverManager.getConnection(FilePath);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			try {
				retObj.put("returnv", -1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return retObj;
		}

        Integer ie = new Integer(save_id);
        String st1 = "SELECT Counter, Box_row, Box_Column FROM Save_State WHERE Save_id = " + ie.toString() + ";";
        Statement statement1;
		try {
			statement1 = connection.createStatement();
			ResultSet result = statement1.executeQuery(st1);
		    while(result.next()){
		            Counter[0] = result.getInt("Counter");
		            Box_Row[0] = result.getInt("Box_row");
		            Box_Column[0] = result.getInt("Box_Column");
		            ith++;
		    }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			try {
				retObj.put("returnv", -1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return retObj;
		}
       
		   if(ith==0) {
			   try {
				retObj.put("returnv", 2);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return retObj;
	       }
	        else {}
		   
                String st = "SELECT Box_id,Box_ithPosition, Box_jthPosition FROM Box WHERE Save_id = "+save_id+";";
                Statement statement;
				try {
					statement = connection.createStatement();
					ResultSet result1 = statement.executeQuery(st);
	                while (result1.next()) {
	                	int ith_Row=result1.getInt("Box_ithPosition");
	                	int jth_Column=result1.getInt("Box_jthPosition");
	                    Box[ith_Row][jth_Column] = result1.getInt("Box_id");
	                }	
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						retObj.put("returnv", -1);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return retObj;
				}

          
		try {
			retObj.put("returnv", 0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Save_Obj.remove("Grid");
		try {
			Save_Obj.put("Grid", Box);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retObj;
    }

    
    @SuppressWarnings("deprecation")
	//public int Save(int Box_Row[], int Box_Column[], int Box[][],int Counter[], String FilePath){
    public JSONObject Save(JSONObject Save_Obj){
    	JSONObject retObj = new JSONObject();
    	int Box_Row[]= {} ;
    	try {
			Box_Row[0]= Save_Obj.getInt("Row");
		} catch (JSONException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}      
    	
    	int Box_Column[]= {} ;
    	try {
			Box_Column[0]= Save_Obj.getInt("Column");
		} catch (JSONException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}      
    	
    	int Counter[]= {} ;
    	try {
			Counter[0]= Save_Obj.getInt("Counter");
		} catch (JSONException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}      
    	
    	String FilePath= null;
		try {
			FilePath = Save_Obj.getString("FilePath");
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}      
    	
    	
    	int Box[][];
		//Box= (int[][]) s1.stringToValue("Grid");
		Box=new int [200][300];
		//-=-----------------------------testing array----------------
		//JSONObject jsonObj = new JSONObject(s1);
		JSONArray jsonArry1 = null;
	    try {
			 jsonArry1 = Save_Obj.getJSONArray("Grid");
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
					Box[i][j] = jsa1.getInt(j);
					//System.out.print(jsa1.getInt(j)+" ");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }


	    }
    	
    	
    	Connection connection = null;
    	Integer ie;
		try {
			connection = DriverManager.getConnection(FilePath);
			ie= new Integer (getCurrentSaveID(FilePath)+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				retObj.put("returnv", -1);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return retObj;
		}
        
        Integer i1 = new Integer(Counter[0]);
        Integer i2 = new Integer(Box_Column[0]);
        Integer i3 = new Integer(Box_Row[0]);
      
        String st1 = "call getindb_Save_State( "+ie+","+i1+","+i3+","+i2+");";
        Statement statement1;
		try {
			statement1 = connection.createStatement();
	        statement1.execute(st1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				retObj.put("returnv", -1);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return retObj;
		}
		   
		int box_Num=1;
		String x2="Alive";
		String st="";
		
		for(int i=0;i<Box_Row[0];i++) {
			for(int j=0;j<Box_Column[0];j++) {
				int aa=i;
				int bb=j;
				if(Box[i][j]==0) {}
				else if(Box[i][j]==1) {
				     st = "INSERT INTO Box (Box_num, Box_id, Status, Save_id,Box_ithPosition,Box_jthPosition)VALUES ("+box_Num+","+Box[i][j]+",\"Alive\","+ie+","+aa+","+bb+");";	
				     box_Num++;
				 	Statement statement;
					try {
						statement = connection.createStatement();
						statement.execute(st);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					else {}				
			}
		}
		try {
			retObj.put("returnv", 0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retObj;
    }

  
}


//    public static void main(String[] args) {
    	
    	
  //      ReadFromDB object= new ReadFromDB();
  /* 
    		
        String url = "jdbc:mysql://localhost:3306/db6?user=root&password=hanijani";
        int[] box_row = new int[] {2};
		int[] box_Column = new int[]{2};
		int[] Counter= new int[] {3};
		
		int[][] BOX = new int[box_row[0]][box_Column[0]];
		
		
		//----------------------DELETE STATE---------------
		/*
		int x=object.RemoveSaveId(2,url);;
		System.out.print(x);
		
		
		    BOX[0][0] = 1;BOX[0][1] = 1;
		    BOX[1][0] = 1;BOX[1][1] = 1;
		    BOX[2][0] = 1;BOX[2][1] = 0;
		*/
		
		//---------------LOAD------------------
		/*int x=object.Load(box_row, box_Column, BOX, 3,Counter,url);
		System.out.println("Function Return Value: "+ x);
		
		System.out.println("Counter: " +Counter[0]);
		System.out.println("Rows: " +box_row[0]);
		System.out.println("Columns: " + box_Column[0]);
		
		for(int i=0;i<box_row[0];i++) {
			for(int j=0;j<box_Column[0];j++) {
				System.out.print(BOX[i][j]);
				System.out.print(' ');            		
			}
			System.out.print('\n');
		}
		*/
		
		
		
		//---------------------VIEW SAVE ID------------ 
		/*int arr[]=object.viewSaveId(url);
		
		for(int i=0;arr[i]!=-2;i++) {
			
			System.out.println("Save ID " + (i+1) +": "+arr[i]);
		}*/
		
		
		
		//-----------------------SAVE STATE------------
		/*BL_Interface g1 = new Game();
		box_row[0] = 20;
		box_Column[0] = 20;
		Counter[0]= 2;

		
      	int Save_Check = object.Save(box_row, box_Column, g1.BoxToInt(), Counter, url);
		if(Save_Check==0) {
			System.out.println("Grid with Counter: "+Counter[0]+" Saved!!");
		}
		*/
		
		
	//	System.out.print(object.RemoveSaveId(3,url));
		
