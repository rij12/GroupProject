package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

	JButton SubmitButton; // button you click to submit the changes
	JButton setToComplete; // button you click to set the task to complete

 // Just so we can accsess the tasks.
	Load load; // makes the save and load methods available 
	JPanel editorPanel; // This is where the task description is edited 
	String user; // just so the name of the user can be printed
	

	public TaskEditor(Task task, DatabaseConnect connection) {
		TaskPage taskPage = new TaskPage(connection);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		user = task.getUser(); 

		//**************************** Main Panel *************************
		setBounds(100, 100, 935, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		editorPanel = new JPanel();

		// layout manager for the frame
		contentPane.add(editorPanel, BorderLayout.CENTER);

		// seting setlout magager for the panel
		editorPanel.setLayout(new MigLayout("","[grow][grow]", "[][][][][][grow][]"));


		// *************************************** Text fields ***************************
		txtCurrentTask = new JTextField();
		txtCurrentTask.setEditable(false);
		txtCurrentTask.setText(task.getTitle());
		editorPanel.add(txtCurrentTask, "cell 0 1,alignx center,aligny center");
		txtCurrentTask.setColumns(10);

		// Editor pane
		JEditorPane editorPane = new JEditorPane();
		editorPanel.add(editorPane, "cell 0 4 2 2,grow");
		editorPane.setText(task.getTaskInfo());	


		//****************submit button **********************************
		SubmitButton = new JButton("Submit\r\n");
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// ***************************************** Find the task currently being edited and change the arraylist  **************
				//Sync local copy with arraylist. 		

				task.setTaskInfo(editorPane.getText());
				int indexOfCurrentTask = 0;

				for(int i = 0; i < taskPage.tasks.size(); i++){
					if(taskPage.tasks.get(i).getId() == task.getId()){
						indexOfCurrentTask = i;
					}
				}

				
//				taskPage.tasks.remove(indexOfCurrentTask); // Removing the old version of the task that are currently being edited 
//				taskPage.tasks.add(task); // adding the new version of the task that are currently being edited 
				taskPage.tasks.get(indexOfCurrentTask).setTaskInfo(editorPane.getText());
				taskPage.tasks.set(indexOfCurrentTask, task);
//				taskPage.saveAndLoad.save(taskPage.tasks, taskPage.userName);
				if(setStatusToComplete){
					taskPage.tasks.get(indexOfCurrentTask).setStatus("Complete");
					
					
				}
//				taskPage.saveAndLoad.save(taskPage.tasks, taskPage.userName);
				if(connection.isLoggedIn()){ 			// This will only sync if the user is logged in 
					connection.sync(taskPage.tasks);
				}
				
				//*********************************************
				dispose();
			}
		});	
		//**********Submit button end ***************//
		editorPanel.add(SubmitButton, "cell 0 7,alignx center,aligny center");

		setToComplete = new JButton("Completed");
		setToComplete.addActionListener(new ActionListener(){

			 
			//*********** Set to complete button1!! ! !! !!! !! ! one ! 111 one
			public void actionPerformed(ActionEvent e) {
				setToComplete.setBackground(Color.GREEN);
				task.setStatus("Complete");
				setStatusToComplete = true;
			}		
		});
		editorPanel.add(setToComplete, "cell 1 7,alignx center,aligny center");
	}	
}