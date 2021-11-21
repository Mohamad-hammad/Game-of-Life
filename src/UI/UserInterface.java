package UI;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

 	public class UserInterface extends javax.swing.JFrame {

    
	//buttons and jpanel
    private Button Start_Button;
    private Button Reset_Button;
    private javax.swing.JPanel Panel;
    
    int width = 30;
    int height = 40;
    
    //arrays to store current and next state/generation
    int[][] Current_State = new int[height][width];
    int[][]	Next_State = new int[height][width];
    //variable to check if play button is clicked or not
    boolean Is_Playing;
    //Image and graphics object to draw the grid
//    Image Image_OffScreen;
//    Graphics Graph_OffScreen;
    Grid grid;
    
    public UserInterface() {
        initComponents();
//        Image_OffScreen = createImage(Panel.getWidth(), Panel.getHeight());
//        Graph_OffScreen = Image_OffScreen.getGraphics();
        //create and draw grid
        grid = new Grid();        
        grid.DrawGrid(Panel, width, height, Current_State);
    }
    
  
 
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	//initialize panel and buttons
        Panel = new javax.swing.JPanel();
        	
        Start_Button = new Button("Start","#d3d3d3","#d3d3d3");
        Reset_Button =  new Button("Reset","#d3d3d3","#d3d3d3");
        this.getContentPane().setBackground( Color.decode("#3b444b") ); 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       // this.getContentPane().setBackground(Color.PINK);
        Panel.setBackground(new java.awt.Color(102, 102, 102));
        //set mouse actions
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

       
        Reset_Button.Get_Button().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        //set layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Start_Button.Get_Button(), javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
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
                    .addComponent(Reset_Button.Get_Button()))
                .addContainerGap())
        );

        pack();
    }
    
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {
    	  int j = width * evt.getX() / Panel.getWidth();
          int i = height * evt.getY() / Panel.getHeight();
          if(SwingUtilities.isLeftMouseButton(evt)){
              Current_State[i][j] = 1;
          }else Current_State[i][j] = 0;
          grid.DrawGrid(Panel,  width, height, Current_State);
    }

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {
        
        grid.DrawGrid(Panel, width, height, Current_State);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Is_Playing = !Is_Playing;
        if(Is_Playing) Start_Button.Get_Button().setText("Pause");
        else Start_Button.Get_Button().setText("Play");
        grid.DrawGrid(Panel, width, height, Current_State);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        Current_State = new int[height][width];
        grid.DrawGrid(Panel, width, height, Current_State);
    }

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {
        int j = width * evt.getX() / Panel.getWidth();
        int i = height * evt.getY() / Panel.getHeight();
        if(SwingUtilities.isLeftMouseButton(evt)){
            Current_State[i][j] = 1;
        }else Current_State[i][j] = 0;
        grid.DrawGrid(Panel, width, height, Current_State);
    }
    
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }


}
