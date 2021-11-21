import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Queue;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Stack;

import javax.print.DocFlavor.CHAR_ARRAY;
import javax.print.DocFlavor.STRING;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.*; 
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class GlobalMembers {

	public static void writing_in_the_File(int Grid_Row,int Grid_Column,int Box[][],int save_id,int Counter) {
		
		Queue<String> q
		= new LinkedList<>();

		try {
			File myObj = new File("C:\\Users\\abdul\\OneDrive\\Desktop\\File.txt");
			Scanner myReader = new Scanner(myObj);

			while(myReader.hasNextLine()){
			String data=myReader.nextLine();
			q.add(data);
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		
		File log = new File("C:\\Users\\abdul\\OneDrive\\Desktop\\File.txt");
		try {
			if (log.exists() == false) {
				System.out.println("We had to make a new file.");
				log.createNewFile();
			}
			PrintWriter out = new PrintWriter(log);
			
			out.append("--------------------------"+"\n\n");
			out.append("Save_id		: "+Integer.toString(save_id)+"\n");
			out.append("Grid_Size	: "+Integer.toString(Grid_Row)+"x"+Integer.toString(Grid_Column)+"\n");
			out.append("Counter		: "+Integer.toString(Counter)+"\n\n");

			for(int i=0;i<Grid_Row;i++){
				for(int j=0;j<Grid_Column;j++){
					out.append(Integer.toString(Box[i][j])+" ");
				}
				out.append("\n");
			}
			out.append("\n");
			out.append("--------------------------"+"\n\n");
			int l=0;
			
			if(l==0){
				out.append("\n");
				l++;
			}
			else{}
			
			while(!(q.isEmpty())){

				out.append(q.remove());
				
				 if(l==1){
					out.append("\n");
				}
				else if(l==2||l==3||l==4){
					out.append("\n");
				}
				else if(l==5){
					out.append("\n");
				}
	//			else if(l==Grid_Row+3){
	//				out.append("\n");
	//			}
				else{
					out.append("\n");
				}
				l++;

			}
			out.close();
		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");
		}
		
	}

	

	/*
	 * try { if (!log.exists()) { System.out.println("We had to make a new file.");
	 * log.createNewFile(); }
	 * 
	 * FileWriter fileWriter = new FileWriter(log, true);
	 * 
	 * BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	 * bufferedWriter.write("******* " + timeStamp.toString() + "******* " + "\n");
	 * bufferedWriter.close();
	 * 
	 * System.out.println("Done"); } catch (IOException e) {
	 * System.out.println("COULD NOT LOG!!"); }
	 * 
	 }
	 */
	// public static int intergerReturn(string x){}

	public static void file_Handling() {

		int save_id = 0;
		int Grid_Row = 0;
		int Grid_Column = 0;
		int Counter = 0;

		try {
			File myObj = new File("C:\\Users\\abdul\\OneDrive\\Desktop\\File.txt");
			Scanner myReader = new Scanner(myObj);

			String data1 = "";

			// For input of Save_State

			myReader.nextLine();
			myReader.nextLine();
			data1 = myReader.nextLine();
			data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
			save_id = Integer.parseInt(new String(data1));

			// ----

			// For input of Grid_Size

			data1 = myReader.nextLine();
			data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
			Grid_Row = Integer.parseInt(new String(data1.substring(0, data1.indexOf('x'))));
			Grid_Column = Integer.parseInt(new String(data1.substring(data1.indexOf('x') + 1, data1.length())));

			// ----

			// For input of Counter

			data1 = myReader.nextLine();
			data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
			Counter = Integer.parseInt(new String(data1));

			// ----

			myReader.nextLine();

			// For input of Grid

			// c
			int[][] twoDarray = new int[Grid_Row][Grid_Column];
			// \c

			for (int i = 0; i < Grid_Row; i++) {
				data1 = myReader.nextLine();
				for (int j = 0; j < Grid_Column; j++) {

					char c = data1.charAt(j + j);
					int number = c - '0';
					twoDarray[i][j] = number;
				}
			}

			for (int i = 0; i < Grid_Row; i++) {
				for (int j = 0; j < Grid_Column; j++) {
					System.out.print(twoDarray[i][j] + " ");
				}

				System.out.println();
			}

			// ----

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {

	//	 file_Handling();
	int[][] BOX = {
		{0,0,1,1,0,0},
		{0,0,1,0,0,0},
		{0,0,0,0,0,0} 
	};
	     writing_in_the_File(3,6,BOX,8,45);
	}
}