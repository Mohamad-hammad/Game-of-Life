/**
 * 
 */
package DB;

import Elements.BL_Interface;
import Elements.Game;

/**
 * @author Hani
 *
 */
public class Txt_Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
    	//-------------------TEXT--------------------
   	 // Set FilePath
   	
      
   	String FilePath = "C:\\Users\\Hani\\Documents\\GitHub\\Second_GOL\\src\\DB\\File.txt";
       
       // ------

       DBInterface R_Obj = new ReadFromText();

       // Suppose we want to save it in file:
       
       
       int[] box_Row = new int[] { 300 };
       int[] box_Column = new int[] {200};
       int[] save_id = new int[] { 80 };
       int[] Counter = new int[] { 100 };

       int[][] BOX = new int[box_Row[0]][box_Column[0]];
       
       BL_Interface g1 = new Game();
       
       
     //--------------------Remove Save ID
       /*
       int x=R_Obj.RemoveSaveId(1,FilePath);
       if (x==0) {
			System.out.println("Removed!");
		}
		else {
			System.out.println("Could Not Remove!");
		}
		*/  
     	
       
       
      //----------------------SAVE----------------
       
       /*
       int Save_Check=R_Obj.Save(box_Row, box_Column, g1.BoxToInt(),Counter, FilePath);
       
       if(Save_Check==0) {
			System.out.println("Grid with Counter: "+Counter[0]+" Saved!!");
		}
       */
       
       
       //--------------View Save ID--------
       
       
       int arr[] = R_Obj.viewSaveId(FilePath);
       //System.out.println(arr[0]);
       for (int i = 0; arr[i]!=-2; i++) {
       	System.out.println("Save ID " + (i+1) +": "+arr[i]);
       }
       
      /* for (int i = 0; i <= R_Obj.getCurrentSaveID(FilePath); i++) {
       	System.out.println("Save ID " + (i+1) +": "+arr[i]);
       }
       */
       
   	
       // ------------------LOAD--------------------
       /*
         int x1=R_Obj.Load(box_Row, box_Column, BOX, 3, Counter, FilePath);
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
        */
     
   	
			
		
   	
   	
   }

}
