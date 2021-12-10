package UI;

import java.text.Normalizer.Form;

import javax.swing.JLabel;

public class label {
	private JLabel Label;
	
	public label(String text)
	{
		Label=new JLabel(text);
	}
	public label()
	{
		Label=new JLabel();
	}
	public void SetText(String text)
	{
		Label.setText(text);
	}
	public void SetFont(Form font)
	{
		
	}
}
