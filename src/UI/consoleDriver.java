package elements;

import java.util.*;
import java.lang.Thread;
import java.io.*;
public class consoleDriver extends board{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int CurrentX=30;
        int CurrentY=30;
        int counter=0;
        int current[][] = new int[CurrentY][CurrentX];
        board b1=new board(30,30,current);
        System.out.println("Enter S to start game:");
        System.out.println("Enter P to pause game:");
        System.out.println("Enter L to load state:");
        System.out.println("Enter s to save game:");
        System.out.println("Enter R to reset game:");
        System.out.println("Enter N to display next generation");
        char a = sc.next().charAt(0);
        if (a == 'S') {
            b1.printBoard();
        }
        while (true) {
           // b1.clearConsole();
            //Runtime.getRuntime().exec("cls");
            System.out.println("Enter P to pause game:");
            System.out.println("Enter L to load state:");
            System.out.println("Enter s to save game:");
            System.out.println("Enter R to reset game:");
            System.out.println("Enter C If you want to make cell alive");
            System.out.println("Enter N to display next generation");
            char input = sc.next().charAt(0);
            if(input=='P'){
                //pause
                int time_lapse=500;
                try
                {
                    Thread.sleep(time_lapse);
                }
                catch(InterruptedException e) {
                    // this part is executed when an exception (in this example InterruptedException) occurs
                    System.out.println("Error in sleep \n");
                }
                b1.printBoard();
            }

            if(input=='R'){
                //reset
                int t=500;
                try
                {
                    Thread.sleep(t);
                }
                catch(InterruptedException e) {
                    // this part is executed when an exception (in this example InterruptedException) occurs
                    System.out.println("Error in sleep \n");
                }
                b1.resetBoard(current);
                System.out.print("New grid \n");
                b1.printBoard();
            }
            if(input=='L'){
                //View
                b1.viewState(current);
            }
            if(input=='s'){
                //save
            }
            if(input=='C') {
                System.out.println("Enter the coordinates of cell you want to make alive. x ,y ");
                int x = sc.nextInt();
                int y = sc.nextInt();
                b1.setAlive(x, y,current);
                b1.printBoard();
                //attach game logic for neighbours and rules
            }
            if(input=='N'){
                b1.nextState(current);
                System.out.println("Next Generation");
                b1.printBoard();
                counter++;
            }
        }
    }
}