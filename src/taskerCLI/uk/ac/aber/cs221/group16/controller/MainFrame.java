package ac.aber.cs221.group16.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ac.aber.cs221.group16.gui.TaskerLogin;
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

			
			public void run() {

				JFrame LoginApp = new TaskerLogin();
				LoginApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


			}
		});

	}

}
