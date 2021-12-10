/**
 * 
 */
package DB;

import Elements.BL_Interface;
import Elements.Game;
import Elements.*;

/**
 * @author Hani
 *
 */
public class SQL_Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
    	
    	
    	
    	
    	//-------------SQL---------------
    	
    	DBInterface object= new ReadFromDB();
    	String url = "jdbc:mysql://localhost:3306/db6?user=root&password=hanijani";
    	
    	int[] box_row = new int[] {300};
 		int[] box_Column = new int[]{200};
 		int[] Counter= new int[] {19};
 		int[][] BOX = new int[box_row[0]][box_Column[0]];
 		
 		BL_Interface g1 = new Game();
 		//g1.SetInitStates();

		//g1.PrintAlive();
		//------------------SAVE STATE--------------------
		
		/*
      	int Save_Check = object.Save(box_row, box_Column, g1.BoxToInt(), Counter, url);
		if(Save_Check==0) {
			System.out.println("Grid with Counter: "+Counter[0]+" Saved!!");
		}
 		*/
		
		//---------------LOAD------------------
		/*		int x=object.Load(box_row, box_Column, BOX, 6,Counter,url);
				System.out.println("Function Return Value: "+ x);
				
				System.out.println("Counter: " +Counter[0]);
				System.out.println("Rows: " +box_row[0]);
				System.out.println("Columns: " + box_Column[0]);
				
				for(int i=0;i<box_row[0];i++) {
					for(int j=0;j<box_Column[0];j++) {
						System.out.print(BOX[i][j]);
						System.out.print(' ');            		
					}
					System.out.println();
				}
		
 		*/
 		//----------------------DELETE----------
 		
 		/*
 		int x=object.RemoveSaveId(13,url);
 		if (x==0) {
 			System.out.println("Removed!");
 		}
 		else {
 			System.out.println("Could Not Remove!");
 		}
 		*/
		
		
		//-----------------View Save IDs--------------
		/*
 		int arr[]=object.viewSaveId(url);
			
			for(int i=0;arr[i]!=-2;i++) {
				
				System.out.println("Save ID " + (i+1) +": "+arr[i]);
			}
		
		*/	
		
    	
    	
    }

}
