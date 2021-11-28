package Main;
import Elements.*;

public class Main {

    public static void main(String[] args) 
    {
    	BL_Interface g1 = new Game();
 		g1.PrintAlive();
 		//g1.Next();
 		g1.Reset();
    }
}


/*class BusinessLogic {
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




*/