package UI;
import Elements.*;
import Elements.BL_Interface;

import java.util.*;
import java.lang.Thread;
import java.io.*;
public class ConsoleInterface extends board{
    private BL_Interface  BL=new Elements.Game();
    //flag to keep a check on whether user has clicked the play button or not
    boolean play;
    //height and width of the grid
     int height=202;
     int width=302;
     int counter=0;
    //arrays to store current and next state/generation
    int Current_State[][] = new int[height][width];
    int[][] Next_State = new int[height][width];
    //generation counter
    int countervalue=10;
    //array to store the id's of all the states
    private int[] statesid;
    public ConsoleInterface(){
        board b1=new board();
        b1.setHeight(height);
        b1.setWidth(width);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter S to start game:");
        System.out.println("Enter P to pause game:");
        System.out.println("Enter L to load state:");
        System.out.println("Enter s to save game:");
        System.out.println("Enter R to reset game:");
        System.out.println("Enter N to display next generation");
        char a = sc.next().charAt(0);
        if(a=='S'){
            play = true;
        }
       // BL.Play(play,Current_State);
        //b1.Draw( width, height, Current_State);
        //counter=BL.get_Counter();
        //System.out.println("Counter value: ");
        //System.out.println(counter);
        Timer time = new Timer();
        TimerTask task = new TimerTask(){
        public void run() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter M to play game using play function");
            System.out.println("Enter P to pause game ");
            System.out.println("Enter V to view a saved state ");
            System.out.println("Enter s to save state ");
            System.out.println("Enter R to reset game");
            System.out.println("Enter C If you want to make cell alive");
            System.out.println("Enter N to display next generation");
            char input = sc.next().charAt(0);
            if (input == 'M') {
                BL.Play(play, Current_State,0);
                b1.Draw(width, height, Current_State);
                counter = BL.get_Counter();
                System.out.println("Counter value: ");
                System.out.println(counter);
            }
            if (input == 'P') {
                //pause
                int time_lapse = 500;
                try {
                    Thread.sleep(time_lapse);
                } catch (InterruptedException e) {
                    // this part is executed when an exception (in this example InterruptedException) occurs
                    System.out.println("Error in sleep \n");
                }
                b1.Draw(width, height, Current_State);
                counter = BL.get_Counter();
                System.out.println("Counter value: ");
                System.out.println(counter);
            }

            if (input == 'R') {
                //reset
                int t = 500;
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    // this part is executed when an exception (in this example InterruptedException) occurs
                    System.out.println("Error in sleep \n");
                }
                System.out.print("New grid \n");
                BL.Reset_States(Current_State);
                b1.Draw(width, height, Current_State);
                BL.Reset_Counter();
                counter = BL.get_Counter();
                System.out.println("Counter value: ");
                System.out.println(counter);
            }
            if (input == 's') {
                //save
                BL.SaveState();
            }

            if (input == 'V') {
                //view
                System.out.println("----------------------View States--------------------------");
                statesid = BL.ViewSavedStates();
                System.out.println("LIST OF STATES");
                for (int i = 0; i < statesid.length; i++) {
                    System.out.println(statesid[i]);
                    if(statesid[i]==-2) {
                        break;
                    }
                }
                System.out.println("Enter L to load state ");
                System.out.println("Enter s to save state ");
                System.out.println("Enter D to delete a saved state ");
                char in = sc.next().charAt(0);
            if (in == 'L') {
                //load
                int num = sc.nextInt(); //intput which states to load
                BL.LoadSaveStates(num);
            }
            if (in == 'D') {
                //delete
                System.out.println("Enter ID of the state you want to delete");
                int num = sc.nextInt(); //intput which states to remove
                BL.DeleteStates(num);
            }
        }
            if(input=='C') {
                System.out.println("Enter the coordinates of cell you want to make alive. x ,y ");
                int x = sc.nextInt();
                int y = sc.nextInt();
                Current_State[x][y] = 1;
                BL.Set_Cell_Alive(x, y);
                b1.Draw(width,height,Current_State);
                counter=BL.get_Counter();
                System.out.println("Counter value: ");
                System.out.println(counter);
            }
            if(input=='N'){
                System.out.println("Next Generation");
                BL.Next(Current_State);
                b1.Draw(width,height,Current_State);
                counter=BL.get_Counter();
                System.out.println(counter);
            }
        }
    };
        time.scheduleAtFixedRate(task, 0, 100);
    }
    public static void main(String[] args) {

    }
}