package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 * <p>
 * Test GUI Main Class
 * </p>
 *  @author Richard Price-Jones 
 */
public class MainFrame {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			
			public void run() {

				JFrame LoginApp = new TaskerLogin();
				LoginApp.setVisible(true);
				LoginApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				LoginApp.pack();

			}
		});

	}

}
