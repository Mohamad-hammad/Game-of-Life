package com.company;

class Box{
    private int status ;
    private int YBox;
    private int XBox;
    private Box Neighbours[][];

    Box(){
        System.out.print("Box Created\n");
    }
    public int SetState(int val){}
    public int SetNeighbour(){}
    public int GetState(){}
    public Box[][] GetNeighbor{}
    public int IsTop(){}
    public int IsBottom(){}
    public int IsLeft(){}
    public int IsRight(){}

}



class BusinessLogic {
    BusinessLogic(){}
    public void Start(){}
    public void Stop(){}
    public void MarkAlive(){}
    public void MarkDead(){}
    public int CheckBoundary(){}
    public int CountAliveNeighbours(){}
    public void NextGeneration(){}
}

class Game{
    Game(){}

}



public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}
