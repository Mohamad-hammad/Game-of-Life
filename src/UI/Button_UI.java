package UI;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
public class Button_UI extends BasicButtonUI {

	private String colour;
	private String Fill_color;
	public Button_UI(String Colour,String Fill_colour)
	{
		colour=Colour;
		Fill_color=Fill_colour;
	}
	@Override
    public void installUI (JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setBorder(new EmptyBorder(5, 15, 5, 15));
    }

    @Override
    public void paint (Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
        super.paint(g, c);
    }

    private void paintBackground (Graphics g, JComponent c, int yOffset) {
        Dimension size = c.getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       // g.setColor( Color.decode("#d3d3d3").darker() ); 
        g.setColor( Color.decode(colour).darker() ); 
       // g.setColor(c.getBackground().darker());
        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
        g.setColor( Color.decode(Fill_color) ); 
        //g.setColor( Color.decode("#d3d3d3") ); 
       // g.setColor(c.getBackground());
        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
    }
}
