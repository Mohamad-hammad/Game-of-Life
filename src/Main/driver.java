package Main;

import Elements.BL_Interface;
import org.json.*;
import UI.Welcom1;
import Elements.Game;
import UI.UserInterface;
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
	   while(window.IsFrameVisible()) {
		   System.out.print(" ");
		   }
	   System.out.println("flag is false now\n");
       BL_Interface BL=new Game();
       Abstract_Factory f1 = new DB_Factory();
       JSONObject Fac_J = new JSONObject();
       try {
		Fac_J.put("Factory", f1);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       try {
		Fac_J.put("DB_Type", "Text");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   BL.setD_Fac(Fac_J);
	   BL.CreateDB(Fac_J);
	   //BL.toJSONStr();
	   UserInterface Game_UI = new UserInterface(BL);
       Game_UI.getframe().show();
   }
   
}
