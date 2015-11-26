package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;





import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.MainFrame;
// External 
import net.miginfocom.swing.MigLayout;
/**
 * TaskerLogin- provides the login menu graphical user interface. 
 * <p>
 * DRAFT - IT'S NOT UP CODING STANDARDS YET...
 * This class uses a custom layout manager called Mig Layout. 
 * When you create an instance of this class, Login Menu is created. 
 * </P>
 * @author Richard Price-Jones
 * @see MainFrame.java
 * @version 1.2 
 * Comments we be fixed, they are for testing and editing for edit
 */
public class TaskerLogin extends JFrame { 
	//ONLY FOR TESTING
	
	public int test1 =3; 
	// Will be final when project is assembled 
	private static final String HEADER_IMG_Path = "/tasker_1.0.jpg";
	private static final String BODY_IMG_PATH = "/LoginMenuBackground_800_by_500.jpg";
	private static final String HEADER_BACKGROUND_IMG_PATH = "/HeaderBackground.jpg";

	public TaskerLogin() {

		
		
		// Creating the main frame
		JFrame frame = new JFrame("Tasker CLI");

		// Creating the JPanel
		JPanel panel = new JPanel(new MigLayout("", "[]100[]100[]", "[]10[]5[]10[][]"));

		// Create Swing Components

		// Header
		JLabel header = new JLabel("");
		header.setBackground(Color.BLACK);
		Image headerImg = new ImageIcon(this.getClass().getResource(HEADER_IMG_Path)).getImage();
		header.setIcon(new ImageIcon(headerImg));
				
		// Header background
		JLabel headerBackGround = new JLabel("");
		Image headerBackGroundImg = new ImageIcon(this.getClass().getResource(HEADER_BACKGROUND_IMG_PATH)).getImage();
		header.setIcon(new ImageIcon(headerBackGroundImg));
		
		// User name
		JLabel usernameLabel = new JLabel("Username:");
		JTextField userName = new JTextField();

		// Password
		JLabel passwordLabel = new JLabel("Password:");
		JPasswordField passwordField = new JPasswordField();
		
		// Login Button
		JButton loginButton = new JButton("Login ");
		// Login Button Action Listener
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = "rich"; 	
				String passowrd = "password"; 
				TaskPage taskPage = new TaskPage ();
				name= userName.getText();
				if (passowrd.equals(passwordField)){
					TaskPage tp = new TaskPage();
					tp.setVisible(true);
						frame.dispose();
						
					
				}
				
			}
		});
		
		//Offline 
		JButton offlineButton = new JButton("Offline");
		
		// ADD swing Components

		panel.add(headerBackGround, "cell 0 0,grow");
		panel.add(header, "cell 1 0,alignx center,aligny center");
		
		//
		panel.add(usernameLabel, "cell 1 1,sg 1,left,split 2, w 25, h 25 ");
		panel.add(userName, "cell 1 1,pushx ,growx,sizegroupx 2,alignx right,sizegroupy 2,aligny center,w 25, h 25");
		//
		panel.add(passwordLabel, "cell 1 3,sizegroupx 1,alignx left,sizegroupy 1, split 2,w 25, h 25");
		panel.add(passwordField, "cell 1 3,pushx ,growx,sizegroupx 2,alignx right,sizegroupy 2,aligny center,w 25, h 25");
		//
		panel.add(loginButton, "cell 1 4,left, split 2, gapx 100px,w 100, h 20");
		panel.add(offlineButton, "cell 1 4, right, gapx 25px, w 100, h 20");
		// Frame
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(500, 300);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tasker CLI");
	}

}
