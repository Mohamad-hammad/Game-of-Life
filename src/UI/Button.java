package UI;

import javax.swing.JButton;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
public class Button {
	private JButton button;
	private int x,y,height,width;
	private String text;
	public Button(String Text,String Colour,String Fill_colour)
	{
		text=Text;
		button = new JButton(text);
		Font newButtonFont=new Font(button.getFont().getName(),Font.BOLD,button.getFont().getSize());
		
		button.setFont(newButtonFont);
		button.setUI(new Button_UI(Colour,Fill_colour));
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
