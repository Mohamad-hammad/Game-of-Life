package UI;

import javax.swing.JButton;

public class Button {
	private JButton button;
	private int x,y,height,width;
	private String text;
	public Button(String Text)
	{
		text=Text;
		button = new JButton(text);
	}
	public Button(String Text,int X,int Y,int Height,int Width)
	{
		text=Text;
		x=X;
		y=Y;
		height=Height;
		width=Width;
		button = new JButton(text);
		button.setBounds(x,y,height,width);
	}
	JButton Get_Button()
	{
		return button;
	}

}
