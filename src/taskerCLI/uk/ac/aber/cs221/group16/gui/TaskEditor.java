package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
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

import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.Task;

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
 * Edited by - Emil 
 * 
 */

public class TaskEditor extends JFrame {
	 
	 	private JPanel contentPane;
	 	private JTextField txtCurrentTask;
	 	private final ButtonGroup buttonGroup = new ButtonGroup();
	 	// Used for diagrams 
	 	boolean taskisComplete;  
	 	
	 //	 TaskPage taskPage = new TaskPage ();
	 	
	 public TaskEditor(Task task, DatabaseConnect connection) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 String user = task.getUser();
		 
		setBounds(100, 100, 935, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel editorPanel = new JPanel();
		// layout manager for the frame
		contentPane.add(editorPanel, BorderLayout.CENTER);
		// seting setlout magager for the panel
		editorPanel.setLayout(new MigLayout("","[grow][grow]", "[][][][][][grow][]"));
		// Current user Display
		txtCurrentTask = new JTextField();
		txtCurrentTask.setEditable(false);
		txtCurrentTask.setText(task.getTitle());
		editorPanel.add(txtCurrentTask, "cell 0 1,alignx center,aligny center");
		txtCurrentTask.setColumns(10);
		
		// Editor pane
		JEditorPane editorPane = new JEditorPane();
		editorPanel.add(editorPane, "cell 0 4 2 2,grow");
		editorPane.setText(task.getTaskInfo());
		
			
		//submit button
		JButton SubmitButton = new JButton("Submit\r\n");
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//TaskPage  taskPage = new TaskPage();
				contentPane.setVisible(false);
				dispose();
		//Sync local copt with arraylist. 		
		TaskPage test = new TaskPage(connection);
		task.setTaskInfo(editorPane.getText());
		// This line needs help
		int test1 = 0;
		
		for(int i = 0; i < test.tasks.size(); i++){
			if(test.tasks.get(i).getId() == task.getId()){
				test1 = i;
			}
		}
		
		test.tasks.remove(test1);
		
//		System.out.println(test.tasks.indexOf(task.getId()));
		
		test.tasks.add(task);
		connection.sync(test.tasks);
		Load load = new Load();
//		load.save(test.tasks, user);
		System.out.println(test.tasks);
		test.repaint();
		 }
		});
			
	editorPanel.add(SubmitButton, "cell 0 7,alignx center,aligny center");
	
	JButton setToComplete = new JButton("Completed");
	setToComplete.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			setToComplete.setBackground(Color.GREEN);
//			taskisComplete = true;
			task.setStatus("Complete");
			
		}		
	});
	editorPanel.add(setToComplete, "cell 1 7,alignx center,aligny center");
	}
	
	
}
