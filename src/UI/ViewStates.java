package UI;
import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
public class ViewStates {
	private JFrame frame;
	
	ViewStates()
	{
		frame = new JFrame("Saved States");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollBar scrollBar = new JScrollBar();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(417, Short.MAX_VALUE)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		  //frame.getContentPane().add(BorderLayout.CENTER, panel);
          frame.pack();
          frame.setLocationByPlatform(true);
          frame.setVisible(false);
          frame.setResizable(true);
	}
	public JFrame GetFrame()
	{
		return frame;
	}
}
