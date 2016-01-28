/*
* @(#) MainFrame.java.java
*
* Copyright (c) 2016 Group 16 
* All rights reserved.
*
*/

/**
 * This Class handles everything that have to do with save and load of 
 * tasks. 
 * 
 * @author Richard Price-Jones
 * @version 1
 * 
 */
package uk.ac.aber.cs221.group16.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;

import uk.ac.aber.cs221.group16.gui.TaskerLogin;
/**
 * <p>
 * Test GUI Main Class
 * </p>
 *  @author Richard Price-Jones 
 *  
 *  Edited by Emil
 */
public class MainFrame {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			/**
			 * This starts the TaskerLogin()
			 */
			public void run() {

				JFrame LoginApp = new TaskerLogin();
				LoginApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
