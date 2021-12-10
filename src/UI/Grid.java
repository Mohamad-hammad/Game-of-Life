package UI;
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import java.util.*;
public class Grid extends JFrame{
	public Grid()
	{
		
	}
	public void DrawGrid(JPanel panel,Image Image_OffScreen,Graphics Graph_OffScreen,int width,int height,int [][]CurrentState){
		pack();
		Image_OffScreen = createImage(panel.getWidth(), panel.getHeight());
	    Graph_OffScreen = Image_OffScreen.getGraphics();
	    Graph_OffScreen.setColor(panel.getBackground());
	    Graph_OffScreen.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < width; j++){
                if(CurrentState[i][j]==1){
                	Graph_OffScreen.setColor(Color.YELLOW);
                    int x = j * panel.getWidth()/width;
                    int y = i * panel.getHeight()/height;
                    Graph_OffScreen.fillRect(x, y, panel.getWidth()/width, panel.getHeight()/height);
                }
            }
        }
        Graph_OffScreen.setColor(Color.BLACK);
        for(int i = 1; i < height;i++){
            int y = i * panel.getHeight()/height;
            Graph_OffScreen.drawLine(0, y, panel.getWidth(), y);
        }
        for(int j = 1; j < width;j++){
            int x = j * panel.getWidth()/width;
            Graph_OffScreen.drawLine(x, 0, x, panel.getHeight());
        }
        panel.getGraphics().drawImage(Image_OffScreen, 0, 0, panel);
    }
}
