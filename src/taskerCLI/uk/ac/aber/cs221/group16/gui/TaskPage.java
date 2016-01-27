package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

import net.miginfocom.swing.MigLayout;
import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.Task;

/**
 * @author Richard Edited by Emil
 *
 */
public class TaskPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchField;
	private JTable table;
	private JButton editTaskButton;
	JLabel nameLable;
	JLabel description;
	JPanel mainPanel;
	JLabel lblCurrentUserInformation;
	JPanel taskDetailsPanel1;
	JScrollPane listPane;
	JLabel completionDate;
	JLabel startDate;
	JPanel taskDetailsPanel;
	JEditorPane editorPane;
	String JListItemString; 
	ArrayList<Task> tasks = new ArrayList<Task>();
	Task task = new Task();
	Load saveAndLoad = new Load();
	String userName;

	DefaultListModel<String> model = new DefaultListModel<>();
	JList<String> list = new JList<>(model);
	ListSelectionModel listSelectionModel = list.getSelectionModel();

	public TaskPage(DatabaseConnect connection) {
		/* Checks if your logged in and decides if to sync app to database */
		if (connection.isLoggedIn()) {
			tasks = saveAndLoad.load(connection.getUserName());
			userName = connection.getUserName();
			if (tasks != null) {
				connection.sync(tasks);
				saveAndLoad.save(tasks, userName);
			} else {
				tasks = connection.getTasks();
				saveAndLoad.save(tasks, userName);
			}
		} else {
			tasks = saveAndLoad.load(null);
			userName = saveAndLoad.getUserName();
		}

		// **************** Main Window
		// **********************************************************************
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// **************** left Panel
		// **********************************************************************

		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new MigLayout("debug", "[][grow]", "[][grow][]"));

		lblCurrentUserInformation = new JLabel(userName);
		mainPanel.add(lblCurrentUserInformation,
				"cell 0 0,alignx center, align left, aligny top");
		lblCurrentUserInformation.repaint();
//		txtSearchField = new JTextField();
//		txtSearchField.setText("Search Field");
//		mainPanel.add(txtSearchField, "cell 0 2,alignx left, aligny top");
//		txtSearchField.setColumns(10);

		// *******JList***********
		listSelectionModel
		.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPane = new JScrollPane(list);
		if (tasks != null) {
			for (Task t : tasks) {
				int i = tasks.indexOf(t) + 1;
				JListItemString = i + " \t" + t.getTitle() + " \t"
						+ t.getDeadLine();
				//
				if(t.getStatus().equals("Allocated")){
					model.addElement(JListItemString);
				}
				
				i++;
			}
		}
		

		/* Swing components for the displaying the details of the tasks */
		taskDetailsPanel1 = new JPanel();
		taskDetailsPanel1
		.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));

		nameLable = new JLabel("Title: ");
		completionDate = new JLabel("Completion date: ");
		startDate = new JLabel("Start date: ");
		description = new JLabel("Task description: ");

		/* JList component */
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				// THIS LINE IS AN EXPECTION HEAVEN
				task = tasks.get(list.getSelectedIndex());

				System.err.println(task);
				editTaskButton.setEnabled(true);

				nameLable.setText("Title: " + task.getTitle());
				completionDate.setText("Completion date: " + task.getDeadLine());
				description.setText("Description: " + task.getTaskInfo());
				startDate.setText("Start date: " + task.getStartDate());
				
				System.out.println(tasks);
			}
		});

		// **** Editing tasks Swing components **********

		editTaskButton = new JButton("Edit current task");
		editTaskButton.setEnabled(false);
		editTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (connection.isLoggedIn()) {
					connection.sync(tasks);
				}
				description.setText("Description: " + task.getTaskInfo());
				saveAndLoad.save(tasks, userName);
				TaskEditor editTask = new TaskEditor(task, connection);
				editTask.setVisible(true);
				System.err.println("Taskpage to TaskerEditor");

			}
		});
		// Stuff
		JButton rsyncbtn = new JButton("Resync");
		rsyncbtn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(connection.isLoggedIn()){
					connection.sync(tasks);
				}
//				list.clearSelection();
//				list.
				
//				fixJList();
				
			}
		} );

		// ********Adding Swing component to the GUI**********

		// *********** Main Panel**********
//		mainPanel.add(list, "cell 0 1, aligny top, growy");
		mainPanel.add(taskDetailsPanel1, "cell 1 1, aligny top");
		mainPanel.add(editTaskButton, "cell 1 2 ");
		mainPanel.add(rsyncbtn, "cell 0 2 ");

		// *Task details Panel****************************
		taskDetailsPanel1.add(nameLable, "cell 0 0, aligny top, alignx left, ");
		taskDetailsPanel1.add(completionDate,
				"cell 0 1, aligny top, alignx left");
		taskDetailsPanel1.add(startDate, "cell 0 2, aligny top, alignx left");
		taskDetailsPanel1.add(description, "cell 0 3, aligny top");

		// ***Refreshing GUI Panel Description*******
		Timer timer = new Timer();
		TimerTask hourlyTask = new TimerTask() {
			@Override
			public void run() {
				description.setText("Description: " + task.getTaskInfo());
				int toRemove = 99999;
				for(int i = 0; i < tasks.size(); i++){
					if(tasks.get(i).getStatus().equals("Complete")){
						toRemove = i;
					}
				}
				if(toRemove != 99999){
					tasks.remove(toRemove);
//					list.remove(toRemove-1);
				}
//				list.clearSelection();
				mainPanel.validate();
				
				
				
				
			}
		};
		JScrollPane scrollBar = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(scrollBar, "cell 0 1, aligny top, growy");
		//	scrollBar.setViewportView(list);

		timer.schedule(hourlyTask, 0l, 1000);
		


	}

	public void fixJList(){
			if (tasks != null) {
				
			for (Task t : tasks) {
				int i = tasks.indexOf(t) + 1;
				JListItemString = i + " \t" + t.getTitle() + " \t"
						+ t.getDeadLine();
				//
				if(t.getStatus().equals("Allocated")){
					model.addElement(JListItemString);
				}
				
				i++;
			}
		}
	}


}
