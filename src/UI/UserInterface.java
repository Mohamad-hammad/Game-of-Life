package UI;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.TimerTask;

import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Elements.BL_Interface;
import Elements.Game;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

    public class UserInterface    {
    private BL_Interface  BL;

    //flag to keep a check on whether user has clicked the play button or not    
    boolean play;

    //buttons and jpanel
    private Button Start_Button;
    private Button Reset_Button;
    private Button Next_Button;
    private Button ViewStates_Button;
    private Button SaveStates_Button;
    private Button ZoomIn;
    private Button ZoomOut;
    private int zoom_scale=1;
    private final int w=300;
    private final int h=200;
    private javax.swing.JPanel Panel;
    private ViewStates SavedStates;
    private JLabel counter;
    private JLabel Counter_Label;
    private JLabel templabel;
    private Image offScrImg;
    private JSlider speed_slider;
    private Graphics offScrGraph;
    //generation counter
    int countervalue=10;
    //height and width of the grid
    int width = 302;
    int height = 202;
    private JFrame frame;
    //arrays to store current and next state/generation
    int[][] Current_State = new int[height][width];
    int[][] Next_State = new int[height][width];
    //variable to check if play button is clicked or not
    boolean Is_Playing;
    Grid grid;
    double generations=0;
    public UserInterface(Game obj) {
    	//GUIorConsole();
    	BL=obj;
        initComponents();
        offScrImg = frame.createImage(Panel.getWidth(), Panel.getHeight());
        offScrGraph = offScrImg.getGraphics();
        //create and draw grid
        grid = new Grid();        
        counter.setText(String.valueOf(generations));
        //Revmoe this --------------------------------------
        Timer time = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
           
                    //Playthegame();
            	    BL.Play(play,Current_State);
            	    grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
            	    counter.setText(String.valueOf(BL.get_Counter()));
                    try {
                        Thread.sleep(speed_slider.getValue()*50);
                        
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        };
        time.scheduleAtFixedRate(task, 0, 100);
        
        //Remove this------------------------------------------
       // grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
    
    }
    public JFrame getframe()
    {
        return frame;
    }
    
    //Remvoe this--------------------------------------------------
  
    private int decide(int i, int j){
        int neighbors = 0;
       
        if(j > 0){
            if(Current_State[i][j-1]==1) neighbors++;
            if(i>0) if(Current_State[i-1][j-1]==1) neighbors++;
            if(i<height-1) if(Current_State[i+1][j-1]==1) neighbors++;
        }
        if(j < width-1){
            if(Current_State[i][j+1]==1) neighbors++;
            if(i>0) if(Current_State[i-1][j+1]==1) neighbors++;
            if(i<height-1) if(Current_State[i+1][j+1]==1) neighbors++;
        }
        if(i>0) if(Current_State[i-1][j]==1) neighbors++;
        if(i<height-1) if(Current_State[i+1][j]==1) neighbors++;
        if(neighbors == 3) return 1;
        if(Current_State[i][j]==1 && neighbors == 2) return 1;
        return 0;
    }
    void PlayNext()
    {
         generations++;
         counter.setText(String.valueOf(generations));
          for(int i = 0; i < height; i++){
              for(int j = 0; j < width; j++){
                Next_State[i][j] = decide(i,j);
              }
          }
          for(int i = 0; i < height; i++){
              for(int j = 0; j < width; j++){
                Current_State[i][j] = Next_State[i][j];
              }
          }
          grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
    }
    void Playthegame()
    {
         Is_Playing = play;
         
        if(play){
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    Next_State[i][j] = decide(i,j);
                }
            }
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    Current_State[i][j] = Next_State[i][j];
                }
            }
            generations++;
            counter.setText(String.valueOf(generations));
            templabel.setText(String.valueOf(speed_slider.getValue()));
            grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
        }
    }
    
    //Remove this ----------------------------------------------
  
    @SuppressWarnings("unchecked")
    private void initComponents() {
        //initialize panel and buttons
        frame=new JFrame();
        Panel = new javax.swing.JPanel();
            
        Start_Button = new Button("Start","#d3d3d3","#d3d3d3");
        Reset_Button =  new Button("Reset","#d3d3d3","#d3d3d3");
        Next_Button = new Button("Next","#d3d3d3","#d3d3d3");
        ViewStates_Button= new Button("View States","#d3d3d3","#d3d3d3");
        SaveStates_Button= new Button("Save State","#d3d3d3","#d3d3d3");
        ZoomIn= new Button("+","#d3d3d3","#d3d3d3");
        ZoomOut= new Button("-","#d3d3d3","#d3d3d3");
        SavedStates=null;
        Counter_Label=new JLabel();
        Counter_Label.setText("Counter: ");
        templabel=new JLabel();
        templabel.setText("0");
        Font LabelFont=new Font(Counter_Label.getFont().getName(),Font.BOLD,Counter_Label.getFont().getSize());
        Counter_Label.setFont(LabelFont);
        counter=new JLabel();
        Counter_Label.setForeground(Color.white);
        counter.setBackground(Color.white);
        counter.setOpaque(true);
        speed_slider=new JSlider();
        speed_slider.setMinimum(0);
        speed_slider.setMaximum(10);
        speed_slider.setPaintLabels(true);
        speed_slider.setPaintTicks(true);
        speed_slider.setMinorTickSpacing(1);
        frame.getContentPane().setBackground( Color.decode("#3b444b") ); 
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
       // this.getContentPane().setBackground(Color.PINK);
        Panel.setBackground(new java.awt.Color(102, 102, 102));
        //set mouse actions
        speed_slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                countervalue=speed_slider.getValue();
            }
        });
       
        Panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        Panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });
        Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
       
        //set layout
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(Panel);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 485, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 293, Short.MAX_VALUE)
        );
        Panel.setLayout(jPanel1Layout);

        //set buttons actions
        Start_Button.Get_Button().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Next_Button.Get_Button().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               NextButtonActionPerformed(evt);
            }
        });
       
        Reset_Button.Get_Button().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        ViewStates_Button.Get_Button().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewStatesActionPerformed(evt);
            }
        });
        ZoomIn.Get_Button().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomInClicked(evt);
            }
        });
        ZoomOut.Get_Button().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomOutClicked(evt);
            }
        });
        SaveStates_Button.Get_Button().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveCurrentState(evt);
            }
        });
        //set layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Start_Button.Get_Button(), javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, Short.MAX_VALUE)
                        .addComponent(Next_Button.Get_Button(), javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)                      
                        .addComponent(ViewStates_Button.Get_Button(), javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(SaveStates_Button.Get_Button(), javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(ZoomIn.Get_Button(), javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(ZoomOut.Get_Button(), javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                        .addComponent(templabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50)
                        .addComponent(speed_slider, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50)
                        .addComponent(Counter_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(counter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(Reset_Button.Get_Button(), javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Start_Button.Get_Button())
                    .addComponent(Next_Button.Get_Button())
                    .addComponent(ViewStates_Button.Get_Button())
                    .addComponent( SaveStates_Button.Get_Button())
                    .addComponent(ZoomIn.Get_Button())
                    .addComponent(ZoomOut.Get_Button())
                    .addComponent(templabel)
                    .addComponent(speed_slider)
                    .addComponent(Counter_Label)
                    .addComponent(counter)
                    .addComponent(Reset_Button.Get_Button()))
                
                .addContainerGap())
        );

        frame.pack();
    }
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) 
    {
          int j = width * evt.getX() / Panel.getWidth();
          int i = height * evt.getY() / Panel.getHeight();
          if(SwingUtilities.isLeftMouseButton(evt))
          {
              Current_State[i][j] = 1;
              BL.Set_Cell_Alive(i, j);
          }
          else 
    	  {
    	  	Current_State[i][j] = 0;
    	  	BL.Set_Cell_Dead(i, j);
    	  }
          grid.DrawGrid(Panel,offScrImg, offScrGraph,  width, height, Current_State);
    }
    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {
        
        grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Is_Playing=play;
        Is_Playing = !Is_Playing;
        if(Is_Playing) 
    	{
        	Start_Button.Get_Button().setText("Pause");
    	}
        else 
        {
        	Start_Button.Get_Button().setText("Play");
        }
        grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
        play=Is_Playing;
    }
    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        Is_Playing=play;
        if(!Is_Playing)
        {
            //PlayNext();
        	BL.Next(Current_State);
     	    grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
        }
    }
    private void SaveCurrentState(java.awt.event.ActionEvent evt) {
    	BL.SaveState();
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        //Current_State = new int[height][width];
        BL.Reset_States(Current_State);
        grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
        //generations=0;
        //counter.setText(String.valueOf(generations));
        BL.Reset_Counter();
        counter.setText(String.valueOf(BL.get_Counter()));
    
    }
    private void ViewStatesActionPerformed(java.awt.event.ActionEvent evt)
    {
        
            SavedStates=new ViewStates(BL);
            SavedStates.GetFrame().setVisible(true);
    }
    private void ZoomInClicked(java.awt.event.ActionEvent evt)
    {
        if(zoom_scale<10)
        {
            zoom_scale++;
            if(width - (zoom_scale*20)>20 && height - (zoom_scale*20)>20 )
            {
                width=width - (zoom_scale*20);
                height=height - (zoom_scale*20);
                grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
            }
        }
        

    }
    private void ZoomOutClicked(java.awt.event.ActionEvent evt)
    {

        if(zoom_scale>=1)
        {
            zoom_scale--;
            if(width + (zoom_scale*20)<400 && height + (zoom_scale*20)<300 )
            {
                width=width + (zoom_scale*20);
                height=height + (zoom_scale*20);
                grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
            }
            else
            {
                width=400;
                height=300;
                grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
            }
        }
    }
    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {
        int j = width * evt.getX() / Panel.getWidth();
        int i = height * evt.getY() / Panel.getHeight();
        if(SwingUtilities.isLeftMouseButton(evt)){
    	{
    		Current_State[i][j] = 1;
    		BL.Set_Cell_Alive(i, j);
    	}
        }else
        {
        	Current_State[i][j] = 0;
        	BL.Set_Cell_Dead(i, j);
        }
        grid.DrawGrid(Panel,offScrImg, offScrGraph, width, height, Current_State);
    }
    private void GUIorConsole()
    {
    	System.out.print("in gui or console");
    	JFrame tempframe=new JFrame("GUI OR CONSOLE");
    	tempframe.getContentPane().setBackground( Color.decode("#3b444b") ); 
    	tempframe.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    	JButton GUI = new JButton("GUI");
    	JButton Console = new JButton("Console");
    	tempframe.add(GUI);
    	tempframe.add(Console);
    	tempframe.pack();
    	tempframe.setLocationByPlatform(true);
    	tempframe.setVisible(false);
    	tempframe.setResizable(true);
    	Console.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CallConsole(evt);
                tempframe.setVisible(false); 
                tempframe.dispose();
            }
        });
    	GUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CallGUI(evt);
                tempframe.setVisible(false);
                tempframe.dispose();
            }
        });
    }
    private void CallConsole(java.awt.event.ActionEvent evt) {
    	System.out.print("call gui\n");
    }
    private void CallGUI(java.awt.event.ActionEvent evt) {
    	System.out.print("call console\n");

    }
//    public static void main(String args[]) {
//      
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//       
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//               // new UserInterface(UserInterface).frame.setVisible(true);
//            }
//        });
//    }

}
