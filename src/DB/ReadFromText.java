import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Queue;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;

// interface
interface DBInterface {
    public void Save(int Box_Row, int Box_Column, int Box[][], int save_id, int Counter, String FilePath); // interface
                                                                                                           // method
                                                                                                           // (does not
                                                                                                           // have a
                                                                                                           // body)

    public void Load(int Box_Row, int Box_Column, int Box[][], int save_id, int Counter, String FilePath); // interface
                                                                                                           // method
                                                                                                           // (does not
                                                                                                           // have a
                                                                                                           // body)
}

public class ReadFromText implements DBInterface {

    public void Save(int Box_Row, int Box_Column, int Box[][], int save_id, int Counter, String FilePath) {

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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        File log = new File(FilePath);
        try {
            if (log.exists() == false) {
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(log);

            out.append("--------------------------" + "\n\n");
            out.append("Save_id		: " + Integer.toString(save_id) + "\n");
            out.append("Grid_Size	: " + Integer.toString(Box_Row) + "x" + Integer.toString(Box_Column) + "\n");
            out.append("Counter		: " + Integer.toString(Counter) + "\n\n");

            for (int i = 0; i < Box_Row; i++) {
                for (int j = 0; j < Box_Column; j++) {
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
        }

    }

    public void Load(int Box_Row, int Box_Column, int Box[][], int save_id, int Counter, String FilePath) {

        try {
            File myObj = new File(FilePath);
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
            Box_Row = Integer.parseInt(new String(data1.substring(0, data1.indexOf('x'))));
            Box_Column = Integer.parseInt(new String(data1.substring(data1.indexOf('x') + 1, data1.length())));

            // ----

            // For input of Counter

            data1 = myReader.nextLine();
            data1 = data1.substring(data1.indexOf(':') + 2, data1.length());
            Counter = Integer.parseInt(new String(data1));

            // ----

            myReader.nextLine();

            //

            // Here, Initialize the Box-2d by loading Box_Row and Box_Column from File.
            // this will create an Array with respect to file Box_Row and Box_Column

            Box = new int[Box_Row][Box_Column];

            // ---

            for (int i = 0; i < Box_Row; i++) {
                data1 = myReader.nextLine();
                for (int j = 0; j < Box_Column; j++) {

                    char c = data1.charAt(j + j);
                    int number = c - '0';
                    Box[i][j] = number;

                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

/* Main is added for testing

    public static void main(String[] args) {

        // Set FilePath
        String FilePath = "C:\\Users\\abdul\\OneDrive\\Desktop\\File.txt";
        // ------

        ReadFromText R_Obj = new ReadFromText();

        // -------------------------------

        // Suppose we want to save it in file:

        int[] box_Row = new int[] { 3 };
        int[] box_Column = new int[] { 6 };
        int[] save_id = new int[] { 5 };
        int[] Counter = new int[] { 100 };

        int[][] BOX = new int[box_Row[0]][box_Column[0]];

        BOX[0][0] = 0;
        BOX[0][1] = 0;
        BOX[0][2] = 1;
        BOX[0][3] = 1;
        BOX[0][4] = 0;
        BOX[0][5] = 0;
        BOX[1][0] = 0;
        BOX[1][1] = 0;
        BOX[1][2] = 1;
        BOX[1][3] = 0;
        BOX[1][4] = 0;
        BOX[1][5] = 0;
        BOX[2][0] = 0;
        BOX[2][1] = 0;
        BOX[2][2] = 0;
        BOX[2][3] = 0;
        BOX[2][4] = 0;
        BOX[2][5] = 0;

        R_Obj.Save(box_Row[0], box_Column[0], BOX, save_id[0], Counter[0], FilePath);

        // ----------------------------------------------

        // After saving we want to load it as:

        R_Obj.Load(box_Row[0], box_Column[0], BOX, save_id[0], Counter[0], FilePath);

        // To test wheather it wheather it is load correctly.

        System.out.println("--------------------------\n");
        System.out.println("Saveid      :" + save_id[0]);
        System.out.println("Counter      :" + Counter[0]);

        for (int i = 0; i < box_Row[0]; i++) {
            for (int j = 0; j < box_Column[0]; j++) {
                System.out.print(BOX[i][j] + " ");
            }

            System.out.println();
        }

        // One thing is that .. their is difficulty to pass by reference in java. Load
        // function
        // works but the when you pass an array with new keyword.

    }
 */   
}