package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
<<<<<<< Updated upstream
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import net.miginfocom.swing.MigLayout;

=======
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.lang.model.element.Element;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
>>>>>>> Stashed changes
public class TaskPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchField;
	private JTable table;
	private JButton editTaskButton;
<<<<<<< Updated upstream
	
	// Delete this is only for testing 
	
	public int test3 = 0;
	public int test2 = 0; 
	
	
	
	
	
	
	
	/**
	 * Launch the application.
//	 */
	public static void main(String[] args) {
		TaskEditor taskedit2 = new TaskEditor();
		taskedit2.test5 = 3;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskPage frame = new TaskPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
=======
//	private TaskerLogin taskLogin = new TaskerLogin();
	ArrayList<Task> tasks = new ArrayList<Task>();
	Load saveAndLoad = new Load();
	// Delete this is only for testing 
	
>>>>>>> Stashed changes

	/**
	 * Create the frame.
	 */
<<<<<<< Updated upstream
	public TaskPage() {
=======
	public TaskPage(DatabaseConnect connection) {

		if(connection.isLoggedIn()){
			System.out.println(connection.isLoggedIn());
			tasks = saveAndLoad.load();
			if(tasks != null){
				connection.sync(tasks);
			}
			else{
				tasks = connection.getTasks();
			}	
		}
		else{
			tasks = saveAndLoad.load();
		}
>>>>>>> Stashed changes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
<<<<<<< Updated upstream
		panel.setLayout(new MigLayout("", "[][][grow]", "[][grow][]"));
		
		JLabel lblCurrentUserInformation = new JLabel("Current user information");
		panel.add(lblCurrentUserInformation, "cell 0 0,alignx center,aligny center");
=======
		panel.setLayout(new MigLayout("", "[grow][][grow]", "[][grow][]"));
		
		JLabel lblCurrentUserInformation = new JLabel("Current user information");
		panel.add(lblCurrentUserInformation, "cell 0 0,alignx center, align left");
>>>>>>> Stashed changes
		
		txtSearchField = new JTextField();
		txtSearchField.setText("Search Field");
		panel.add(txtSearchField, "cell 2 0,alignx center");
		txtSearchField.setColumns(10);
		
<<<<<<< Updated upstream
		JLabel lblCurrentTaskInformation = new JLabel("Current Task Information  -- hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh\n\nhhhhhhhhhhh"
				+ "hhhhhhhhhhhhhhhh");
		panel.add(lblCurrentTaskInformation, "cell 0 1");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"StartDate", "Comment", "Status", "Date of Completion", "Member Allocated", "Task ID"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(96);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(121);
		panel.add(table, "cell 2 1,alignx center,growy");
		
		editTaskButton = new JButton("New button");
		editTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DatabaseConnect connect = new DatabaseConnect();
				
				connect.g = 3;
				
				
			}
		});
		panel.add(editTaskButton, "cell 0 2,alignx center,aligny center");
		
		
		
		
	}

}
=======

		
		
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

		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent ev) {
				Task task = tasks.get(list.getSelectedIndex());
				
				System.err.println(task);
				
				
				
				
				editTaskButton.setEnabled(true);			
				editTaskButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						TaskEditor editTask = new TaskEditor(task);
						editTask.setVisible(true);
						System.err.println("Taskpage to TaskerEditor");

					}
				});
				
				
			} 
		});



		editTaskButton = new JButton("Edit current task");
		editTaskButton.setEnabled(false);

		panel.add(editTaskButton,"cell 0 2 ");

	}

}








>>>>>>> Stashed changes
