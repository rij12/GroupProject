package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JButton;

import uk.ac.aber.cs221.group16.authentication.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;

import java.awt.event.ActionListener;
/**
 * <p>
 * This class generate the graphical user interface for the edit task window. 
 * The following changes still need to be made.
 * Action listeners.
 * Test for bugs
 * Frame disposing 
 * </P>
 * @version 1.0
 * @author Richard Price-Jones 
 * 
 */
public class TaskEditor extends JFrame {

	private JPanel contentPane;
	private JTextField txtCurrentTask;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	// Used for diagrams 
	  
	 TaskPage taskPage = new TaskPage ();
	public int test5 = 0; 

	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				Load load = new Load();
				load.something = 3;
				
				DatabaseConnect connect = new DatabaseConnect();
				
				connect.g = 3;
				
				
				
				try {
					TaskEditor frame = new TaskEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaskEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		// layout manager for the frame
		contentPane.add(panel, BorderLayout.CENTER);
		// seting setlout magager for the panel
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][grow][]"));
		// Current user Display
		txtCurrentTask = new JTextField();
		txtCurrentTask.setEditable(false);
		txtCurrentTask.setText("Current Task");
		panel.add(txtCurrentTask, "cell 0 1,alignx center,aligny center");
		txtCurrentTask.setColumns(10);
		// Set a task to complete
		JButton setToComplete = new JButton("Completed ");
		// Login Button Action Listener
		setToComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
					
				}
						});
		
		//Text Box
		// Needs database input
		JEditorPane editorPane = new JEditorPane();
		panel.add(editorPane, "cell 0 4 2 2,grow");
		//submit button
		JButton SubmitButton = new JButton("Submit\r\n");
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(SubmitButton, "cell 0 6 2 1,alignx center,aligny center");
	}

	
}
