package gui;

import java.awt.BorderLayout;
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


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
		// Radio buttons
		// need to get data from the database
		JRadioButton completedadioButton = new JRadioButton("Completed\r\n");
		buttonGroup.add(completedadioButton);
		panel.add(completedadioButton, "flowx,cell 1 1,alignx center,aligny center");
		
		JRadioButton onGoingRadioButton = new JRadioButton("on-going");
		buttonGroup.add(onGoingRadioButton);
		panel.add(onGoingRadioButton, "cell 1 1,alignx center,aligny center");
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
