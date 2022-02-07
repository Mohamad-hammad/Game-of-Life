package UI;

import Elements.BL_Interface;
import Elements.Game;

public class driver {
   public static void main(String[] args) {
//	   try {
//           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//               if ("Nimbus".equals(info.getName())) {
//                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                   break;
//               }
//           }
//       } catch (ClassNotFoundException ex) {
//           java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//       } catch (InstantiationException ex) {
//           java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//       } catch (IllegalAccessException ex) {
//           java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//           java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//       }

	   
	   
	   Welcom1 window = new Welcom1();
	   window.setframevisible();
	   while(window.IsFrameVisible()) {System.out.println("in while\n");}
	   System.out.println("flag is false now\n");
       BL_Interface BL=new Game();
	   UserInterface Game_UI = new UserInterface(BL);
       Game_UI.getframe().show();
   }
   
}
