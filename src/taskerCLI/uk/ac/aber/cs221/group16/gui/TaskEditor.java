package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.Button;
<<<<<<< Updated upstream
=======
import java.awt.Color;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
import uk.ac.aber.cs221.group16.authentication.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;
=======
import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.Task;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======
 * Edited by - Emil 
>>>>>>> Stashed changes
 * 
 */
public class TaskEditor extends JFrame {

	private JPanel contentPane;
	private JTextField txtCurrentTask;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	// Used for diagrams 
<<<<<<< Updated upstream
	  
	 TaskPage taskPage = new TaskPage ();
	public int test5 = 0; 

	

=======
	boolean taskisComplete;  
//	 TaskPage taskPage = new TaskPage ();
>>>>>>> Stashed changes
	
	/**
	 * Launch the application.
	 */
<<<<<<< Updated upstream
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
=======
	
>>>>>>> Stashed changes

	/**
	 * Create the frame.
	 */
<<<<<<< Updated upstream
	public TaskEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
=======
	public TaskEditor(Task task) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
>>>>>>> Stashed changes
		setBounds(100, 100, 935, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
<<<<<<< Updated upstream
		JPanel panel = new JPanel();
		// layout manager for the frame
		contentPane.add(panel, BorderLayout.CENTER);
		// seting setlout magager for the panel
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][grow][]"));
=======
		JPanel editorPanel = new JPanel();
		// layout manager for the frame
		contentPane.add(editorPanel, BorderLayout.CENTER);
		// seting setlout magager for the panel
		editorPanel.setLayout(new MigLayout("debug", "[grow][grow]", "[][][][][][grow][]"));
>>>>>>> Stashed changes
		// Current user Display
		txtCurrentTask = new JTextField();
		txtCurrentTask.setEditable(false);
		txtCurrentTask.setText("Current Task");
<<<<<<< Updated upstream
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
=======
		editorPanel.add(txtCurrentTask, "cell 0 1,alignx center,aligny center");
		txtCurrentTask.setColumns(10);
		// Set a task to complete
		

		
	
		// Needs database input
		JEditorPane editorPane = new JEditorPane();
		editorPanel.add(editorPane, "cell 0 4 2 2,grow");
>>>>>>> Stashed changes
		//submit button
		JButton SubmitButton = new JButton("Submit\r\n");
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
<<<<<<< Updated upstream
			}
		});
		panel.add(SubmitButton, "cell 0 6 2 1,alignx center,aligny center");
	}

	
=======
//				TaskPage  taskPage = new TaskPage();
				contentPane.setVisible(false);
				dispose();
			}
		});
			
	editorPanel.add(SubmitButton, "cell 0 7,alignx center,aligny center");
	
	JButton setToComplete = new JButton("Completed ");
	setToComplete.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			
			setToComplete.setBackground(Color.GREEN);
			taskisComplete = true; 
			
			
			
		}		
	});
	editorPanel.add(setToComplete, "cell 1 7,alignx center,aligny center");
	}
>>>>>>> Stashed changes
}
