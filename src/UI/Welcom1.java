package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.DropMode;

public class Welcom1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Welcom1 window = new Welcom1();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public void setframevisible()
	{
		frame.setVisible(true);
	}
	public boolean IsFrameVisible()
	{
		return frame.isShowing();
	}
	/**
	 * Create the application.
	 */
	public Welcom1() {
		initialize();
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1245, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setForeground(new Color(135, 206, 235));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1229, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextPane Fgcb = new JTextPane();
		Fgcb.setBackground(Color.BLACK);
		Fgcb.setForeground(Color.WHITE);
		Fgcb.setFont(new Font("Montserrat SemiBold", Font.BOLD, 21));
		Fgcb.setText("			How to Play\r\n\n"
		        + "Click to set a box in the grid alive\r\n"
				+ "Click on highlighted boxes to mark as dead\r\n"
				+ "Next Button produces one new generation\r\n"
				+ "Play button will produce new generations in a loop\r\n"
				+ "Pause button will be pressed to break loop of Play button\r\n"
				+ "Counter display the number of current generation\r\n"
				+ "Use slider to adjust speed and buttons for zoom");
		panel_3.add(Fgcb);
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeframe(e);
				
							}
		});
		panel_2.add(btnNewButton);
		
		JTextPane txtpn = new JTextPane();
		txtpn.setBackground(Color.BLACK);
		txtpn.setForeground(Color.WHITE);
		txtpn.setFont(new Font("Montserrat SemiBold", Font.BOLD, 21));
		txtpn.setText("			\t\tRULES\r\n\n"
				+ "1.Any live cell with two or three live neighbours survives.\r\n"
				+ "2.Any dead cell with three live neighbours becomes a live cell.\r\n"
				+ "3.All other live cells die in the next generation. Similarly, all other dead cells stay dead.\r\n"
				+ "4.left click to make cell alive.\r\n"
				+ "5.right click to make cell dead.");
		panel_1.add(txtpn);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 0, 0));
		lblNewLabel.setForeground(new Color(102, 205, 170));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PC-X\\Downloads\\Untitled design (1).png"));
		panel.add(lblNewLabel);
		frame.getContentPane().setLayout(groupLayout);
	}
	private void closeframe(java.awt.event.ActionEvent evt)
	{
		frame.dispose();
	}
}
