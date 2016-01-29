package uk.ac.aber.cs221.group16.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
//import uk.ac.aber.cs221.group16.controller.JListListener;
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
	JTextArea description;
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
	private int JListSelectedIndex;
	private int selected;
	private Color bg;
	private JScrollPane scrollBarDescription;
	private JScrollPane scrollBar;

	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private JList<String> list = new JList<>(listModel);
	private ListSelectionModel listSelectionModel = getList()
			.getSelectionModel();
	
	;

	public TaskPage(DatabaseConnect connection) {
		/* Checks if your logged in and decides if to sync app to database */
		if (connection.isLoggedIn()) {
			System.out.println("is logged in");
			tasks = saveAndLoad.load(connection.getUserName(), connection.isLoggedIn());
			userName = connection.getUserName();
			if (tasks != null) {
				System.out.println("tasks != null");
				tasks = connection.sync(tasks);
				saveAndLoad.save(tasks, userName);
			} else {
				System.out.println("tasks == null");
				tasks = connection.getTasks();
				saveAndLoad.save(tasks, userName);
			}
		} else {
			System.out.println("not logged in");
			tasks = saveAndLoad.load(null, connection.isLoggedIn());
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
		

		// *******JList***********
		listSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPane = new JScrollPane(getList());
		if (tasks != null) {
			for (Task t : tasks) {
				int i = t.getId();
				JListItemString = i + " \t" + t.getTitle() + " \t"
						+ t.getDeadLine();
				//
				if (t.getStatus().equals("Allocated")) {
					getListModel().addElement(JListItemString);
				}

				i++;
			}
		}

		/* Swing components for the displaying the details of the tasks */
		taskDetailsPanel1 = new JPanel();
		taskDetailsPanel1
				.setLayout(new MigLayout("debug", "[grow]", "[][][][][][]"));

		nameLable = new JLabel("Title: ");
		completionDate = new JLabel("Completion date: ");
		startDate = new JLabel("Start date: ");
		description = new JTextArea("Task description: ");
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		description.setMargin(new Insets(0,0,0,0));
		bg = new Color (233,233,233);
		description.setBackground(bg);

		/* JList component */
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				
				JListSelectedIndex = getList().getSelectedIndex();
				String test = list.getSelectedValue();
				mainPanel.revalidate();
				mainPanel.repaint();
				editTaskButton.setEnabled(true);
				
				if(JListSelectedIndex >= 0){
					for(int i = 0; i < tasks.size(); i++){
						if(test.contains(Integer.toString(tasks.get(i).getId()))){
							selected = i;
						}
					}
				}

				
				System.out.println(Integer.toString(tasks.get(selected).getId()));
				System.out.println(test);
//				System.out.println(test.contains(Integer.toString(tasks.get(selected).getId())));
				
				
				System.out.println(JListSelectedIndex);

				if(JListSelectedIndex >= 0){
					nameLable.setText("Title: " + tasks.get(selected).getTitle());
					completionDate.setText("Completion date: " + tasks.get(selected).getDeadLine());
					description.setText("Description: " + tasks.get(selected).getTaskInfo());
					startDate.setText("Start date: " + tasks.get(selected).getStartDate());
				}
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
//				description.setText("Description: " + tasks.get(JListSelectedIndex).getTaskInfo());
				saveAndLoad.save(tasks, userName);
				TaskEditor editTask = new TaskEditor(tasks.get(selected), connection); 
				editTask.setVisible(true);
				System.err.println("Taskpage to TaskerEditor");

			}
		});
		// **** Resync Button
		JButton rsyncbtn = new JButton("Set task to complete");
		rsyncbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JListSelectedIndex = getList().getSelectedIndex();
				String test = list.getSelectedValue();
				
				editTaskButton.setEnabled(true);
				
				if(JListSelectedIndex >= 0){
					for(int i = 0; i < tasks.size(); i++){
						if(test.contains(Integer.toString(tasks.get(i).getId()))){
							selected = i;
						}
					}
				}
				
				tasks.get(selected).setStatus("Complete");

				saveAndLoad.save(tasks, userName);
				if (connection.isLoggedIn()) {
					connection.sync(tasks);
				}

				rsyncbtn.setEnabled(true);
				deleteItemFromJList(list.getSelectedIndex());
				//					description.setText("Description: " + tasks.get(list.getSelectedIndex()).getTaskInfo());
			}
		});

		// ********Adding Swing component to the GUI**********
		
		scrollBar = new JScrollPane(getList(),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBarDescription = new JScrollPane(description,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// *********** Main Panel**********

		mainPanel.add(taskDetailsPanel1, "cell 1 1, aligny top, growx, growy");
		mainPanel.add(editTaskButton, "cell 1 2 ");
		mainPanel.add(rsyncbtn, "cell 0 2 ");
		mainPanel.add(scrollBar, "cell 0 1, aligny top, growy, growx 0,wmin 10");

		// *Task details Panel****************************
		taskDetailsPanel1.add(nameLable, "cell 0 0, aligny top, alignx left, ");
		taskDetailsPanel1.add(completionDate,
				"cell 0 1, aligny top, alignx left");
		taskDetailsPanel1.add(startDate, "cell 0 2, aligny top, alignx left");
		taskDetailsPanel1.add(scrollBarDescription, "cell 0 3, aligny top, growy, growx");

		// ***Refreshing GUI Panel Description*******
		Timer timer = new Timer();
		TimerTask updateDescription = new TimerTask() {
			@Override
			public void run() {
				if(list.getSelectedIndex() >= 0){
					description.setText("Description: " + tasks.get(selected).getTaskInfo());
					
//					System.out.println(tasks.get(selected).getTaskInfo());
				}
			}
		};
		
		TimerTask everyFiveMinute = new TimerTask() {
			@Override
			public void run() {

					connection.sync(tasks);
					saveAndLoad.save(tasks, userName);
					System.out.println("automatic sync");
				
			}
		};
		timer.schedule(everyFiveMinute, 0l, 1000*60*5);
		
		timer.schedule(updateDescription, 0l, 1000);

		

////		JScrollPane scrollBar = new JScrollPane(getList(),
//				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
	}

	/*
	 * Second constructor, needed for the oberserver.
	 */
	public TaskPage() {

	}


	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}
	
	public ArrayList<Task> getTasks(){
		return tasks;
	}
	
	public String getUserName(){
		return userName;
	}
	
//	public DatabaseConnect getConnection(){
//		return connection;
//	}

	/**
	 * <p>
	 * This method take a index of type int, then removes that index from the
	 * Model
	 * 
	 * </p>
	 * 
	 * @param indexToBeDeleted
	 */
	public void deleteItemFromJList(int number) {
//		int i = list.getSelectedIndex();
		list.clearSelection();
		System.err.println("Size of Model -> " + listModel.getSize() + " --- "
				+ "index -> " + number);
		if(number >= 0 ){
			listModel.remove(number );
		}
		System.out.println(tasks);
	}
}
