package com.company;

public class Box {
    private boolean state;
    private int YBox;
    private int XBox;

    public Box()
    {
        state = false;
        System.out.println("Box Created");
    }
    public Box(int x, int y)
    {
        state = false;
        XBox = x;
        YBox = y;
        System.out.println("Box with" + x + "," + y +" Created");
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
    public void SetAlive()
    {
        state = true;
    }
    public void SetDead()
    {
        state = false;
    }
    public boolean IsAlive()
    {
        if (state == true)
            return true;
        else return false;
    }
    public boolean GetState()
    {
        return state;
    }
    public boolean IsTop()
    {
        if (YBox == 0)
            return true;
        else return false;
    }
    public boolean IsBottom(int height)
    {
        if (YBox == height)
            return true;
        else return false;
    }
    public boolean IsLeft()
    {
        if (XBox == 0)
            return true;
        else return false;
    }
    public boolean IsRight(int width)
    {
        if (XBox == width)
            return true;
        else return false;
    }

}