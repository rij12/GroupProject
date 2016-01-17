package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.lang.model.element.Element;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.Task;
import net.miginfocom.swing.MigLayout;

/**
 * @author Richard 
 * Edited by Emil
 *
 */
public class TaskPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchField;
	private JTable table;
	private JButton editTaskButton;
//	private TaskerLogin taskLogin = new TaskerLogin();
	ArrayList<Task> tasks = new ArrayList<Task>();
	Task task = new Task();
	Load saveAndLoad = new Load();
	String userName;
	// Delete this is only for testing 
	

	/**
	 * Create the frame.
	 */
	public TaskPage(DatabaseConnect connection) {

		if(connection.isLoggedIn()){
			System.out.println(connection.isLoggedIn());
			tasks = saveAndLoad.load();
			userName = connection.getUserName();
			if(tasks != null){
				connection.sync(tasks);
				saveAndLoad.save(tasks, userName);
			}
			else{
				tasks = connection.getTasks();
				saveAndLoad.save(tasks, userName);
			}	
		}
		else{
			tasks = saveAndLoad.load();
			userName = saveAndLoad.getUserName();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][grow]", "[][grow][]"));
		
		
		JLabel lblCurrentUserInformation = new JLabel(userName);
		panel.add(lblCurrentUserInformation, "cell 0 0,alignx center, align left, aligny top");
		
		txtSearchField = new JTextField();
		txtSearchField.setText("Search Field");
		panel.add(txtSearchField, "cell 0 2,alignx left, aligny top");
		txtSearchField.setColumns(10);
		
		
		DefaultListModel<String> model = new DefaultListModel<>();
		JList<String> list = new JList<>( model );
		 ListSelectionModel listSelectionModel = list.getSelectionModel();
		 listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 JScrollPane listPane = new JScrollPane(list);
		
		panel.add(list, "cell 0 1, aligny top, growy");
	
	
		if(tasks != null){
			for(Task t: tasks){
				int i = tasks.indexOf(t) + 1;
				String text = i + " \t" + t.getTitle() + " \t" + t.getDeadLine();
				//				
				model.addElement(text);	
				i++;
			}
		}


		
		// The current task
		JPanel panel2 = new JPanel();
		panel.add(panel2, "cell 1 1, aligny top");
		panel2.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));


		JLabel nameLable = new JLabel("Title: ");
		JLabel completionDate = new JLabel("Completion date: ");
		JLabel startDate = new JLabel("Start date: ");
		JLabel description =new JLabel("Task description: ");
		//This should display the current task. 
		JEditorPane editorPane = new JEditorPane();
		 panel2.add(nameLable, "cell 0 0, aligny top, alignx left, ");
		 panel2.add(completionDate, "cell 0 1, aligny top, alignx left");
		 panel2.add(startDate, "cell 0 2, aligny top, alignx left");
		 panel2.add(description, "cell 0 3, aligny top");
		 
		 
		 
		 
			list.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent ev) {
					task = tasks.get(list.getSelectedIndex());
					
					System.err.println(task);
					editTaskButton.setEnabled(true);
					
					nameLable.setText("Title: " + task.getTitle());	
					completionDate.setText("Completion date: " + task.getDeadLine());
					description.setText("Description: " + task.getTaskInfo());
					startDate.setText("Start date: " + task.getStartDate());
				} 
			});
			
			
			
			editTaskButton = new JButton("Edit current task");
			editTaskButton.setEnabled(false);
			panel.add(editTaskButton,"cell 1 2 ");
			
			editTaskButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					connection.sync(tasks);
					saveAndLoad.save(tasks, userName);
					TaskEditor editTask = new TaskEditor(task);
					editTask.setVisible(true);
					System.err.println("Taskpage to TaskerEditor");

				}
			});	
		 
		
	}
	
}