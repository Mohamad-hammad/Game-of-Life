package Elements;

public class Box {
    private boolean state;
    private int YBox;
    private int XBox;

    public Box()
    {
        state = false;
        //System.out.println("Box Created");
    }
    public Box(int x, int y)
    {
        state = false;
        XBox = x;
        YBox = y;
        //System.out.println("Box with" + x + "," + y +" Created");
    }
    //Copy Constructor for Box
    public Box(Box box)
    {
    	this.state = box.state;
    	this.YBox = box.YBox;
    	this.XBox = box.XBox;
    }
    public void SetCoordinates(int x, int y)
    {
        XBox = x;
        YBox = y;
    }
    public int GetX()
    {
        return XBox;
    }
    public int GetY()
    {
        return YBox;
    }
    //sets the box State to Alive
    public void SetAlive()
    {
        state = true;
    }
    //sets the box State to Alive
    public void SetDead()
    {
        state = false;
    }
//    public boolean IsAlive()
//    {
//        if (state == true)
//            return true;
//        else return false;
//    }
    //returns alive or dead
    public boolean GetState()
    {
        return state;
    }
  }