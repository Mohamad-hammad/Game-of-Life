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
    
}
