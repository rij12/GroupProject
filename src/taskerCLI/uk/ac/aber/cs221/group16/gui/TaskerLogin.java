/*
 * @(#) TaskerLogin.java
 *
 * Copyright (c) 2016 Group 16 
 * All rights reserved.
 *
 */

/**
 * TaskerLogin- provides the login menu graphical user interface. 
 * <p>
 * Creates the user interface for login, handels the connection and some syncing between the locally and the database.
 * It also ensures that the user information is correct to send to the server. 
 * </P>
 * @author Richard Price-Jones
 * @author Emil Ramsdal
 * @see MainFrame.java
 * @version 1.3
 * 
 */
package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.net.Inet4Address;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.MainFrame;
// External 
import net.miginfocom.swing.MigLayout;

public class TaskerLogin extends JFrame { 

	// Will be final when project is assembled 
	private static final String HEADER_IMG_Path = "/tasker_1.0.jpg";
	private static final String BODY_IMG_PATH = "/LoginMenuBackground_800_by_500.jpg";
	private static final String HEADER_BACKGROUND_IMG_PATH = "/HeaderBackground.jpg";
	public DatabaseConnect connection;


	JFrame frame;
	JPanel panel;


	// Labels 
	JLabel header;
	JLabel usernameLabel;
	JLabel passwordLabel;
	JLabel notificationLabel;
	JLabel notificationLabelConnection;


	// Textfields 
	JTextField userName;
	JPasswordField passwordField;

	// Buttons
	JButton loginButton;
	JButton offlineButton;

	//images
	Image headerImg;


	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public TaskerLogin() {

		connection = new DatabaseConnect();

		// Creating the main frame
		frame = new JFrame("Tasker CLI");

		// Creating the JPanel
		panel = new JPanel(new MigLayout("", "[]100[]100[]", "[]10[]5[]10[][]"));

		// Create Swing Components

		// Header
		header = new JLabel("");
		headerImg = new ImageIcon(this.getClass().getResource(HEADER_IMG_Path)).getImage();
		header.setIcon(new ImageIcon(headerImg));


		//Labels 
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		notificationLabel = new JLabel("Enter a valid email and password.");
		notificationLabelConnection = new JLabel("Could not connect to database.");
		notificationLabel.setForeground(Color.RED);
		notificationLabelConnection.setForeground(Color.RED);
		notificationLabel.setVisible(false);
		notificationLabelConnection.setVisible(false);

		// Text fields 
		userName = new JTextField();
		passwordField = new JPasswordField();

		// Login Button
		loginButton = new JButton("Login ");
		// Login Button Action Listener
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logIn();
			}
		});


		//Offline 
		offlineButton = new JButton("Offline");
		offlineButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				TaskPage mainPage = new TaskPage(connection);
				frame.setVisible(false);
				mainPage.setVisible(true);
				dispose();

			}
		});

		// Action listener for the password feild. 
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn();
			}			
		});


		// ADD swing Components
		panel.add(header, "cell 1 0,alignx center,aligny center");


		panel.add(usernameLabel, "cell 1 1,sg 1,left,split 2, w 25, h 25 ");
		panel.add(userName, "cell 1 1,pushx ,growx,sizegroupx 2,alignx right,sizegroupy 2,aligny center,w 25, h 25");

		panel.add(passwordLabel, "cell 1 3,sizegroupx 1,alignx left,sizegroupy 1, split 2,w 25, h 25");
		panel.add(passwordField, "cell 1 3,pushx ,growx,sizegroupx 2,alignx right,sizegroupy 2,aligny center,w 25, h 25");

		panel.add(loginButton, "cell 1 4,left, split 2, gapx 100px,w 100, h 20");
		panel.add(offlineButton, "cell 1 4, right, gapx 25px, w 100, h 20");

		// adding the swing message Panels. 
		panel.add(notificationLabelConnection, "cell 1 5, align center");
		panel.add(notificationLabel, "cell 1 5, align center");

		// Frame
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(500, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tasker CLI");
	}

	public void logIn(){
		connection.logIn(userName.getText(), passwordField.getPassword());
		if(validate(userName.getText())){
			if(connection.isLoggedIn()){

				TaskPage mainPage = new TaskPage(connection);
				frame.setVisible(false);
				mainPage.setVisible(true);
				dispose();
			}
			else{
				notificationLabelConnection.setVisible(true);
				frame.repaint();
				frame.revalidate();
			}
		}
		else{
			notificationLabel.setVisible(true);
			frame.repaint();
			frame.revalidate();
		}

	}


	/**
	 * Validate Email with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		pattern = Pattern.compile(EMAIL_PATTERN);

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}	
}
