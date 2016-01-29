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
import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.Task;

/**
 *<p>
 * This class Creates the main tasker graphical user interface, 
 * as well as many of the main functionality of the program. Using this class the user can
 * view all the tasks that are assigned to them. In addition to this they can set the task to complete and edit the task comments.
 *</p>
 * 
 * @author Richard Price-Jones (rij12) 
 * @author Emil Ramsdal (emr18)
 *
 */
public class TaskPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchField;
	private JTable table;
	private JButton editTaskButton;
	private JLabel nameLable;
	private JTextArea description;
	private JLabel descriptionLabel;
	private JPanel mainPanel;
	private JLabel lblCurrentUserInformation;
	private JPanel taskDetailsPanel1;
	private JScrollPane listPane;
	private JLabel completionDate;
	private JLabel startDate;
	private JLabel notify;
	private JPanel taskDetailsPanel;
	private JEditorPane editorPane;
	private String JListItemString;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private Task task = new Task();
	private Load saveAndLoad = new Load();
	private String userName;
	private int JListSelectedIndex;
	private int selected;
	private Color bg;
	private JScrollPane scrollBarDescription;
	private JScrollPane scrollBar;

	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private JList<String> list = new JList<>(listModel);
	private ListSelectionModel listSelectionModel = getList()
			.getSelectionModel();



	public TaskPage(DatabaseConnect connection) {
		/* Checks if your logged in and decides if to sync app to database */
		if (connection.isLoggedIn()) {
			tasks = saveAndLoad.load(connection.getUserName(), connection.isLoggedIn());
			userName = connection.getUserName();
			if (tasks != null) {
				tasks = connection.sync(tasks);
				saveAndLoad.save(tasks, userName);
			} else {
				tasks = connection.getTasks();
				saveAndLoad.save(tasks, userName);
			}
		} else {
			tasks = saveAndLoad.load(null, connection.isLoggedIn());
			userName = saveAndLoad.getUserName();
		}

		// ********* Main Window *******

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// ************ left Panel ******

		// Main panel
		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new MigLayout("", "[][grow]", "[][grow][]"));

		lblCurrentUserInformation = new JLabel(userName);
		// Layout manager set using 3rd party tool, MigLayout Manager. 
		mainPanel.add(lblCurrentUserInformation,
				"cell 0 0,alignx center, align left, aligny top");
		lblCurrentUserInformation.repaint();


		// *******JList***********
		/* Addes Task data into the ListModel, ready to be displayed by the JList*/
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		taskDetailsPanel1.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));

		nameLable = new JLabel("Title: ");
		completionDate = new JLabel("Completion date: ");
		startDate = new JLabel("Start date: ");

		//************************* Description Text Area ******************************
		description = new JTextArea("");
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		description.setMargin(new Insets(0,0,0,0));
		bg = new Color (233,233,233);
		description.setBackground(bg);

		/* JList component,  This detects changes, and acts upon them,  */
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {

				JListSelectedIndex = getList().getSelectedIndex();
				String test = list.getSelectedValue();
				// Refreshing the GUI. 
				mainPanel.revalidate();
				mainPanel.repaint();
				// If there is a task then button is available to use. 
				editTaskButton.setEnabled(true);

				// iterates though the arraylist to search for an item with matching ID and return that Index. 
				if(JListSelectedIndex >= 0){
					for(int i = 0; i < tasks.size(); i++){
						if(test.contains(Integer.toString(tasks.get(i).getId()))){
							selected = i;
						}
					}
				}


				// obtains information from the task to display for the JPanels. 
				if(JListSelectedIndex >= 0){
					nameLable.setText("Title: " + tasks.get(selected).getTitle());
					completionDate.setText("Completion date: " + tasks.get(selected).getDeadLine());
					description.setText(tasks.get(selected).getTaskInfo());
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
				// If there is a connection and the try to sync the tasks. 
				if (connection.isLoggedIn()) {
					connection.sync(tasks);
				}
				// Save changes to local storage. 
				saveAndLoad.save(tasks, userName);
				TaskEditor editTask = new TaskEditor(tasks.get(selected), connection); 
				editTask.setVisible(true);

			}
		});
		// **** Set To complete button ******
		JButton setToCompleteButton = new JButton("Set task to complete");
		setToCompleteButton.addActionListener(new ActionListener() {
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
				// Sets selected task, status to complete 
				tasks.get(selected).setStatus("Complete");

				if (connection.isLoggedIn()) {
					connection.sync(tasks);
				}

				setToCompleteButton.setEnabled(true);
				deleteItemFromJList(list.getSelectedIndex());
				saveAndLoad.save(tasks, userName);
			}
		});

		// ********Adding Swing components to the GUI**********
		notify = new JLabel();
		notify.setForeground(Color.RED);
		notify.setVisible(false);
		scrollBar = new JScrollPane(getList(),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBarDescription = new JScrollPane(description,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		descriptionLabel = new JLabel("Description: ");
		// *********** Main Panel**********
		// Addes all swing components to frames using MigLayout manager. 
		mainPanel.add(taskDetailsPanel1, "cell 1 1, aligny top, growx, growy");
		mainPanel.add(editTaskButton, "cell 1 2 ");
		mainPanel.add(setToCompleteButton, "cell 0 2 ");
		mainPanel.add(scrollBar, "cell 0 1, aligny top, growy, growx 0,wmin 10");
		mainPanel.add(notify, "cell 1 0");
		// *Task details Panel***************
		taskDetailsPanel1.add(nameLable, "cell 0 0, aligny top, alignx left, ");
		taskDetailsPanel1.add(completionDate,
				"cell 0 1, aligny top, alignx left");
		taskDetailsPanel1.add(startDate, "cell 0 2, aligny top, alignx left");
		taskDetailsPanel1.add(descriptionLabel,"cell 0 3, aligny top, alignx left");
		taskDetailsPanel1.add(scrollBarDescription, "cell 0 4, aligny top, growy, growx");

		// ***Refreshing GUI Panel Description*******


		/* Refreshs the GUI every 1 second */
		Timer timer = new Timer();
		TimerTask updateDescription = new TimerTask() {
			@Override
			public void run() {
				if(list.getSelectedIndex() >= 0){
					description.setText(tasks.get(selected).getTaskInfo());
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				if (listModel.getSize() < 1){
					editTaskButton.setEnabled(false);
					setToCompleteButton.setEnabled(false);
					description.setText(" ");

				}
			}
		};
		/* Calls the sync method every 5 minutes, to kepp the app updated.*/
		TimerTask everyFiveMinute = new TimerTask() {
			@Override
			public void run() {

				connection.sync(tasks);
				saveAndLoad.save(tasks, userName);	

				if(connection.getDidItSync()){
					notify.setVisible(true);
					notify.revalidate();
					notify.repaint();
				}

			}
		};
		timer.schedule(everyFiveMinute, 0l, 1000*60*5);

		timer.schedule(updateDescription, 0l, 1000);

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

	public Load getSaveAndLoad(){
		return saveAndLoad;
	}

	/**
	 * <p>
	 * This method takes a index of type int, then removes that index from the
	 * Model, it then gives defaults values to the JPanels. 
	 * 
	 * </p>
	 * 
	 * @param indexToBeDeleted
	 */
	public void deleteItemFromJList(int index) {
		list.clearSelection();
		if(index >= 0 ){
			listModel.remove(index);
			// Setting to default values 
			nameLable.setText("Title: " + "Select a task");
			completionDate.setText("Completion date: " + "Select a task");
			descriptionLabel.setText("Description: " + "Select a task");
			startDate.setText("Start date: " + "Select a task");
			descriptionLabel.setText(" ");
		}
	}
}
