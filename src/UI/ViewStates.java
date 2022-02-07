package UI;

import org.json.*;
import org.json.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;


import Elements.BL_Interface;
import Elements.Game;
public class ViewStates extends JFrame{
	private JButton RemoveButton;
	private JButton LoadButton;
	private JTextField input;
	private int[] statesid;
	private BL_Interface  BL;
	ViewStates(BL_Interface obj)
	{
		super("View States");
		BL=obj;
		System.out.print("i am in viewstates\n");
		setBounds(100,100,450,300);
		Container ControlHost= getContentPane();
		ControlHost.setLayout(new BorderLayout());
		
		
		JPanel panel = new JPanel();
		GridLayout gl = new GridLayout(0,3,1,1);
		panel.setLayout(gl);
		//panel.setMinimumSize(new Dimension(500, 500));
	
		JLabel [] label;
//		JButton [] LoadState;
//		JButton [] RemoveState;
		label=new JLabel[500];
//		LoadState=new JButton[500];
//		RemoveState=new JButton[500];
		JLabel heading,heading1,heading2;
		heading1 = new JLabel("			");
		heading1.setFont(new Font("Serif", Font.PLAIN, 40));
		heading1.setHorizontalAlignment(SwingConstants.CENTER);
		heading1.setVerticalAlignment(SwingConstants.CENTER);
		
		heading = new JLabel("LIST OF STATES");
		heading.setFont(new Font("Serif", Font.PLAIN, 40));
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setVerticalAlignment(SwingConstants.CENTER);
		
		heading2 = new JLabel("			");
		heading2.setFont(new Font("Serif", Font.PLAIN, 40));
		heading2.setHorizontalAlignment(SwingConstants.CENTER);
		heading2.setVerticalAlignment(SwingConstants.CENTER);
		
		input = new JTextField("enter the state id", 20);
		LoadButton=new JButton("Load State");
		
		RemoveButton=new JButton("Remove State");
		panel.add(heading1);
		panel.add(heading);
		panel.add(heading2);
		panel.add(input);
		panel.add(LoadButton);
		panel.add(RemoveButton);
		JSONObject StateIDs = new JSONObject();
		StateIDs=BL.ViewSavedStates();
		JSONArray jsonArry1 = null;
        try {
    		 jsonArry1 = StateIDs.getJSONArray("SavedStates");
    	} catch (JSONException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
        statesid=new int[jsonArry1.length()];	    		
        for(int j = 0; j<jsonArry1.length();j++){
            try {
            	statesid[j] = jsonArry1.getInt(j);
				//System.out.print(jsa1.getInt(j)+" ");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    
		
		boolean myflag=true;
		for(int i=0;i<statesid.length;i++) 
		{
			if(statesid[i]==-2)
				myflag=false;
			if(myflag)
			{
				 label[i] = new JLabel("State #"+statesid[i]);
				 label[i].setPreferredSize(new Dimension(300, 25));
				 label[i].setHorizontalAlignment(SwingConstants.CENTER);
				 label[i].setVerticalAlignment(SwingConstants.CENTER);
	//			 LoadState[i]=new JButton("Load State");
	//			 RemoveState[i]=new JButton("Remove State");
				 panel.add(label[i]);
	//			 panel.add(LoadState[i]);
	//			 panel.add(RemoveState[i]);
			}
			else
			{
				 label[i] = new JLabel("Empty");
				 label[i].setPreferredSize(new Dimension(300, 25));
				 label[i].setHorizontalAlignment(SwingConstants.CENTER);
				 label[i].setVerticalAlignment(SwingConstants.CENTER);
	//			 LoadState[i]=new JButton("Load State");
	//			 RemoveState[i]=new JButton("Remove State");
				 panel.add(label[i]);
			}
		}
//		JLabel jlabel = new JLabel("This is a label");
//		jlabel.setPreferredSize(new Dimension(300, 25));
//		panel.add(jlabel);
//		panel.add(new JButton("HI "+ 1));
//		panel.add(new JButton("HI "+ 2));
		
		JScrollPane jsp=new JScrollPane(
				panel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		ControlHost.add(jsp);
	
		  //frame.getContentPane().add(BorderLayout.CENTER, panel);
          this.pack();
          this.setLocationByPlatform(true);
          this.setVisible(false);
          this.setResizable(true);
          
          RemoveButton.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  RemoveState(evt);
              }
          });
          
          LoadButton.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  LoadState(evt);
              }
          });
	}

	private void RemoveState(java.awt.event.ActionEvent evt) {
		System.out.print("removing " + input.getText());
		JSONObject s1=new JSONObject();
		try {
			s1.put("DeleteStates",input.getText());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BL.DeleteStates(s1);
		this.dispose();
	}
	private void LoadState(java.awt.event.ActionEvent evt) {
		System.out.print("loading " + input.getText());
		JSONObject s1=new JSONObject();
		try {
			s1.put("LoadStates",input.getText());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BL.LoadSaveStates(s1);
		this.dispose();
	}
	public JFrame GetFrame()
	{
		return this;
	}
}
