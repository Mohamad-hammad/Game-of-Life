package elements;
import java.lang.Thread;
import java.io.*;
public class board {
    public int height,width;
    public String[][] box ;
    public board()
    {

    }
    public board(int h,int w)
    {
        height=h;
        width=w;
       box = new String[height][width];
        for (int i = 0; i < box.length; i++)
            for (int j = 0; j < box[i].length; j++)
                box[i][j] = " - ";
    }
    public void resetBoard()
    {
        for (int i = 0; i < box.length; i++)
            for (int j = 0; j < box[i].length; j++)
                box[i][j] = " - ";
    }
    public  void printBoard() {
        System.out.print("    |");
        for (int i = 0; i < box[0].length; i++){
            System.out.print("    ");
            System.out.print((char)('A' + i));
            System.out.print("    |");
        }
        System.out.println();
        for (int i = 0; i < box.length; i++){
            System.out.print("----+");
            for (int j = 0; j < box[0].length; j++){
                System.out.print("---------+");
            }
            System.out.println();
            System.out.print("  " + (i + 1) + " |");
            for (int j = 0; j < box[0].length; j++){
                if (box[i][j].length() < 10){
                    int spaces = (9 - box[i][j].length()) / 2;
                    for (int k = 0; k < spaces; k++){
                        System.out.print(" ");
                    }
                    System.out.print(box[i][j]);
                    for (int k = 0; k < (9 - box[i][j].length()) - spaces; k++){
                        System.out.print(" ");
                    }
                }
                else{
                    System.out.print(box[i][j].substring(0, 9));
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }
    public void setAlive(int x, int y)
    {
        box[x-1][y-1]=" * ";
    }
    
}
