package DB;

import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Queue;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Stack;
import java.util.Vector;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.sql.*;

public class ReadFromText implements DBInterface {

    public boolean CheckFileEmpty(String FilePath){

        try {

            File myObj = new File(FilePath);
            Scanner myReader = new Scanner(myObj);
            
            // For input of Save_State

            if (myReader.hasNextLine()) {
                myReader.close();
                return false;
            } else {
                myReader.close(); 
            }

        } catch (FileNotFoundException e) {}
        
        return true;

        // For input of Save_State

    }

    public int getCurrentSaveID(String FilePath) {

        int Saveid = 0;

        try {

            File myObj = new File(FilePath);
            Scanner myReader = new Scanner(myObj);

            String data1 = "";

            // For input of Save_State

            if (CheckFileEmpty(FilePath)==false) {}
            else {
                myReader.close();
                return -1;
            }
            
            myReader.nextLine();
            myReader.nextLine();
            data1 = myReader.nextLine();
            data1 = data1.substring(data1.indexOf(':') + 2, data1.length());

            Saveid = Integer.parseInt(new String(data1));
            myReader.close();
        
        } 
        
        catch (FileNotFoundException e) {}

        return Saveid;
    }

    public int[] viewSaveId(String FilePath){
        
    	int arr[] = new int[100];
        int j = 0;
        int boxRow;
      
        try {

            File myObj = new File(FilePath);
            @SuppressWarnings("resource")
			Scanner myReader = new Scanner(myObj);

            String data1 = "";

            if (CheckFileEmpty(FilePath)==false) {
            } else {
                myReader.close();
                arr[0]=-1;
                arr[1]=-2;
                return arr;
            }
    

            // For input of Save_State

            myReader.nextLine();
            myReader.nextLine();
            data1 = myReader.nextLine();
            data1 = data1.substring(data1.indexOf(':') + 2, data1.length());

            arr[j++] = Integer.parseInt(new String(data1));

            boolean breaker = false;

            for (; breaker != true;) {

                if (myReader.hasNextLine()==false) {
                    breaker = true;
                } else {

                    data1 = myReader.nextLine();
                    data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
                    boxRow = Integer.parseInt(new String(data1.substring(0, data1.indexOf('x'))));

                    myReader.nextLine();
                    myReader.nextLine();

                    for (int i = 0; i < boxRow; i++, myReader.nextLine())
                        ;

                    myReader.nextLine();
                    myReader.nextLine();

                    myReader.nextLine();

                    myReader.nextLine();
                    if(myReader.hasNextLine()==false) {breaker=true;}
                    else {
             
                    myReader.nextLine();

                    myReader.nextLine();

                    data1 = myReader.nextLine();
                    data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
                    arr[j++] = Integer.parseInt(new String(data1));
                    
                    }
                }
            }
           
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        arr[j]=-2;
        return arr;
    }
    
    public int RemoveSaveId(int save_id,String FilePath){
    
    	Stack<String> s1 = new Stack<String>();  	
       	Stack<String> s2 = new Stack<String>();      	  
    	try {
            
            File myObj = new File(FilePath);
            @SuppressWarnings("resource")
			Scanner myReader = new Scanner(myObj);
            String data1 = "";

            // For input of Save_State

            if(CheckFileEmpty(FilePath)==true){
                myReader.close();
                return 2;
            }
            else{}

            s1.push(myReader.nextLine());
            s1.push(myReader.nextLine());
            data1 = myReader.nextLine();
            s1.push(data1);
            data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
            
            
            int save_id1;
            save_id1 = Integer.parseInt(new String(data1));
                       
            if((save_id1<save_id)||(save_id<0)) {
            myReader.close();
            	return 3;	
            }
            else {}
            
           
            Boolean breaker=false;
            
            for (int p=0;breaker!=true;p++) {

               if (save_id1 == save_id) {
            	   
            	   s1.pop();
            	   s1.pop();
            	   s1.pop();
            	   
            	   
            	   data1 = myReader.nextLine();
                   //s1.push(data1);
                   data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
                   int Box_Row = Integer.parseInt(new String(data1.substring(0, data1.indexOf('x'))));

                  myReader.nextLine();
                  myReader.nextLine();

                   for (int i = 0; i < Box_Row; i++, myReader.nextLine());
                   
                   
                   myReader.nextLine();
                  
                   myReader.nextLine();
 
                   myReader.nextLine();

                   myReader.nextLine();
                   
                   if(myReader.hasNextLine()==false) {breaker=true;}
                   else {
                   s1.push(myReader.nextLine());

                   s1.push(myReader.nextLine());
    
                   
                   data1 = myReader.nextLine();
                   
                   s1.push(data1);                   
                   
                   data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
                   save_id1 = Integer.parseInt(new String(data1));
                   
                   }    
            	   
 
            	   
               } 
               else {
                	                
                    data1 = myReader.nextLine();
                    s1.push(data1);
                    data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
                    int Box_Row = Integer.parseInt(new String(data1.substring(0, data1.indexOf('x'))));

                    s1.push(myReader.nextLine());
                    s1.push(myReader.nextLine());

                    for (int i = 0; i < Box_Row; i++, s1.push(myReader.nextLine()));
                    
                    
                    s1.push(myReader.nextLine());
                   
                    s1.push(myReader.nextLine());
  
                    s1.push(myReader.nextLine());

                    s1.push(myReader.nextLine());
                    if(myReader.hasNextLine()==false) {breaker=true;}
                    else {
                    s1.push(myReader.nextLine());
 
                    s1.push(myReader.nextLine());
     
                    
                    data1 = myReader.nextLine();
                    
                    s1.push(data1);                   
                    
                    data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
                    save_id1 = Integer.parseInt(new String(data1));
                    
                    }           
                }
           }
            
        	myReader.close();
    	}
    	
    	catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            return -1;
        }
    	

        
		while(s1.isEmpty()!=true) {
   	    s2.push(s1.pop());
    	}
    	
		 File log = new File(FilePath);
		  
     	if (log.exists() == false) {
             System.out.println("\nCreating new file.");
             try {
					log.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         }
         else {}
         
		try {
       
			 PrintWriter out;

   			 out = new PrintWriter(log);
					
		
			while(s2.isEmpty()!=true) {
    		
				out.append(s2.pop()+"\n");
			 
//				System.out.print("\n"+s2.pop());
				
    	     }
			out.close();
		}
		catch (FileNotFoundException e) {
			 //TODO Auto-generated catch block
			return -1;
		}
            
		return 0;
	}   
    
    public int Save(int Box_Row[], int Box_Column[], int Box[][],int Counter[], String FilePath) { // interface method (does not have a body)


        Queue<String> q = new LinkedList<>();

        try {
        	
            File myObj = new File(FilePath);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                q.add(data);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }

        File log = new File(FilePath);
        
        try {
            
        	if (log.exists() == false) {
                System.out.println("\nCreating new file.");
                log.createNewFile();
            }
            else {}
            
            int save_id = getCurrentSaveID(FilePath) + 1;
        	
            PrintWriter out = new PrintWriter(log);

            out.append("--------------------------" + "\n\n");
            out.append("Save_id		: " + Integer.toString(save_id) + "\n");
            out.append("Box Row && Box Column	: " + Integer.toString(Box_Row[0]) + "x" + Integer.toString(Box_Column[0])
                    + "\n");
            out.append("Counter		: " + Integer.toString(Counter[0]) + "\n\n");

            for (int i = 0; i < Box_Row[0]; i++) {
                for (int j = 0; j < Box_Column[0]; j++) {
                    out.append(Integer.toString(Box[i][j]) + " ");
                }
                out.append("\n");
            }
            out.append("\n");
            out.append("--------------------------" + "\n\n");
            int l = 0;

            if (l == 0) {
                out.append("\n");
                l++;
            } else {
            }

            while (!(q.isEmpty())) {

                out.append(q.remove());

                if (l == 1) {
                    out.append("\n");
                } else if (l == 2 || l == 3 || l == 4) {
                    out.append("\n");
                } else if (l == 5) {
                    out.append("\n");
                } else {
                    out.append("\n");
                }
                l++;
            }
            out.close();
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
            return -1;
        }
        return 0;
    }

    public int Load(int Box_Row[], int Box_Column[], int Box[][], int save_id, int Counter[], String FilePath) { // interface method (does not have a body)

        try {
        
            File myObj = new File(FilePath);
            @SuppressWarnings("resource")
			Scanner myReader = new Scanner(myObj);

            String data1 = "";

            // For input of Save_State

            if(CheckFileEmpty(FilePath)==true){
                Box_Row[0]=0;
                Box_Column[0]=0;
                Counter[0]=0;
                myReader.close();
                return 2;
            }
            else{}

            myReader.nextLine();
            myReader.nextLine();
            data1 = myReader.nextLine();
            data1 = data1.substring(data1.indexOf(':') + 2, data1.length());

            int save_id1;
            save_id1 = Integer.parseInt(new String(data1));

            boolean breaker = false;

            for (; breaker != true;) {

                if (save_id1 == save_id) {
                    breaker = true;
                } 
                else if(save_id1<save_id || save_id<0){
                    Box_Row[0]=0;
                    Box_Column[0]=0;
                    Counter[0]=0;
                    myReader.close();
                    return 3;
                }
                
                else {

                    data1 = myReader.nextLine();
                    data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
                    Box_Row[0] = Integer.parseInt(new String(data1.substring(0, data1.indexOf('x'))));

                    myReader.nextLine();
                    myReader.nextLine();

                    for (int i = 0; i < Box_Row[0]; i++, myReader.nextLine());

                    myReader.nextLine();
                    myReader.nextLine();
                    myReader.nextLine();
                    myReader.nextLine();
                    myReader.nextLine();
                    myReader.nextLine();

                    data1 = myReader.nextLine();
                    data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
                    save_id1 = Integer.parseInt(new String(data1));

                }
            }
            // ----

            // For input of Grid_Size

            data1 = myReader.nextLine();
            data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
            Box_Row[0] = Integer.parseInt(new String(data1.substring(0, data1.indexOf('x'))));
            Box_Column[0] = Integer.parseInt(new String(data1.substring(data1.indexOf('x') + 1, data1.length())));

            // ----

            // For input of Counter

            data1 = myReader.nextLine();
            data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
            Counter[0] = Integer.parseInt(new String(data1));

            // ----

            myReader.nextLine();

            //

            // Here, Initialize the Box-2d by loading Box_Row and Box_Column from File.
            // this will create an Array with respect to file Box_Row and Box_Column

            // Box = new int[Box_Row[0]][Box_Column[0]];

            // ---

            for (int i = 0; i < Box_Row[0]; i++) {
                data1 = myReader.nextLine();
                for (int j = 0; j < Box_Column[0]; j++) {

                    char c = data1.charAt(j + j);
                    int number = c - '0';
                    Box[i][j] = number;

                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            return -1;
        }
        
        return 0;
    }


/*
// Main is added for testing

public static void main(String[] args) throws SQLException {

    // Set FilePath
	
    String FilePath = "C:\\Users\\abdul\\OneDrive\\Desktop\\File.txt";
    
    // ------

    ReadFromText R_Obj = new ReadFromText();

    // Suppose we want to save it in file:

    int[] box_Row = new int[] { 4 };
    int[] box_Column = new int[] {7};
    int[] save_id = new int[] { 80 };
    int[] Counter = new int[] { 100 };

    int[][] BOX = new int[box_Row[0]][box_Column[0]];
    
    
    
    BOX[0][0] = 0;BOX[0][6] = 1;
    BOX[0][1] = 0;BOX[1][6] = 1;
    BOX[0][2] = 1;BOX[2][6] = 0;
    BOX[0][3] = 1;BOX[3][6]	= 1;
    BOX[0][4] = 0;
    BOX[0][5] = 0;
    BOX[1][0] = 0;
    BOX[1][1] = 0;
    BOX[1][2] = 1;
    BOX[1][3] = 0;
    BOX[1][4] = 0;
    BOX[1][5] = 0;
    BOX[2][0] = 1;
    BOX[2][1] = 0;
    BOX[2][2] = 0;
    BOX[2][3] = 1;
    BOX[2][4] = 0;
    BOX[2][5] = 1;
    BOX[3][0] = 1;
    BOX[3][1] = 0;
    BOX[3][2] = 0;
    BOX[3][3] = 1;
    BOX[3][4] = 0;
    BOX[3][5] = 1;
    
    System.out.println("Within Function.");
    
  
    int x=R_Obj.RemoveSaveId(10,FilePath);
    System.out.println(x);  
  
    //int x=R_Obj.Save(box_Row, box_Column, BOX,Counter, FilePath);
    
    //System.out.println(x);
    
    
    
        
    int arr[] = R_Obj.viewSaveId(FilePath);
    System.out.println(arr[0]);
    for (int i = 0; i <= R_Obj.getCurrentSaveID(FilePath); i++) {
        System.out.println(arr[i]);
    }
    
    
	
    // -------------------------------
    
      int x1=R_Obj.Load(box_Row, box_Column, BOX, 1, Counter, FilePath);
      System.out.print(x1);

    // To test wheather it wheather it is load correctly.
    
     System.out.println("--------------------------\n");
     System.out.println("Saveid      :" + save_id[0]);
     System.out.println("Counter      :" + Counter[0]);
     
     for(int i=0;i<box_Row[0];i++) {
    	 for(int j=0;j<box_Column[0];j++) {
    		 System.out.print(BOX[i][j]);;    		 
    	 }
    	 System.out.print('\n');
     }
     
     System.out.println(); 
     
  
	}
     
     

    // ----------------------------------------------

    // One thing is that .. their is difficulty to pass by reference in java. Load
    // function
    // works but the when you pass an array with new keyword.
*/
	}

