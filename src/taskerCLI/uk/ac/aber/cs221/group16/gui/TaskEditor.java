package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Savepoint;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.Task;
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

	public JPanel contentPane;
	private JTextField txtCurrentTask;
	private boolean setStatusToComplete = false;
	int indexOfCurrentTask;
	private JScrollPane editorPaneScrollBar;
	JButton SubmitButton; // button you click to submit the changes
	JButton setToComplete; // button you click to set the task to complete

 // Just so we can accsess the tasks.
	Load load; // makes the save and load methods available 
	JPanel editorPanel; // This is where the task description is edited 
	String user; // just so the name of the user can be printed
	

	public TaskEditor(Task task, DatabaseConnect connection) {
		
		System.out.println(task.getTaskInfo());
		
		TaskPage taskPage = new TaskPage(connection);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		user = task.getUser(); 

		//**************************** Main Panel *************************
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		editorPanel = new JPanel();

		// layout manager for the frame
		contentPane.add(editorPanel, BorderLayout.CENTER);

		// seting setlout magager for the panel
		editorPanel.setLayout(new MigLayout("","[grow]", "[][][][][][grow][]"));


		// *************************************** Text fields ***************************
		txtCurrentTask = new JTextField();
		txtCurrentTask.setEditable(false);
		txtCurrentTask.setText(task.getTitle());
		editorPanel.add(txtCurrentTask, "cell 0 1,alignx center,aligny center");
		txtCurrentTask.setColumns(10);

		// Editor pane
		JTextArea  editorPane = new JTextArea ();
		
		editorPane.setText(task.getTaskInfo());	
		editorPane.setLineWrap(true);
		editorPane.setWrapStyleWord(true);
		editorPaneScrollBar = new JScrollPane(editorPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


		//****************submit button **********************************
		SubmitButton = new JButton("Submit\r\n");
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// ***************************************** Find the task currently being edited and change the arraylist  **************
				//Sync local copy with arraylist. 		

				task.setTaskInfo(editorPane.getText());
				System.out.println(task.getTaskInfo());
				indexOfCurrentTask = 0;
				

				for(int i = 0; i < taskPage.getTasks().size(); i++){
					if(taskPage.getTasks().get(i).getId() == task.getId()){
						indexOfCurrentTask = i;
					}
				}
				System.out.println(indexOfCurrentTask);
					
//				taskPage.tasks.remove(indexOfCurrentTask); // Removing the old version of the task that are currently being edited 
//				taskPage.tasks.add(task); // adding the new version of the task that are currently being edited 
				taskPage.getTasks().get(indexOfCurrentTask).setTaskInfo(editorPane.getText());
				taskPage.getTasks().set(indexOfCurrentTask, task);
				
			
//				taskPage.saveAndLoad.save(taskPage.tasks, taskPage.userName);
				if(setStatusToComplete){
					taskPage.getTasks().get(indexOfCurrentTask).setStatus("Complete");
					
					taskPage.deleteItemFromJList(indexOfCurrentTask);
					
				}
//				taskPage.saveAndLoad.save(taskPage.tasks, taskPage.userName);
				if(connection.isLoggedIn()){ 			// This will only sync if the user is logged in 
					connection.sync(taskPage.getTasks());
				}
				
				taskPage.saveAndLoad.save(taskPage.getTasks(), taskPage.getUserName());
				
				connection.sync(taskPage.getTasks());
				
				
				//*********************************************
				dispose();
			}
		});	
		//**********Submit button end ***************//
		
		/*Adding Java swing components to the panel*/
		editorPanel.add(SubmitButton, "cell 0 7,alignx center,aligny center");
		editorPanel.add(editorPaneScrollBar, "cell 0 4 2 2,grow");
		editorPanel.add(txtCurrentTask, "cell 0 1,alignx center,aligny center");
	}	
}