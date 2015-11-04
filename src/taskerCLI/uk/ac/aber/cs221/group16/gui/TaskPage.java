package gui;

import java.awt.BorderLayout;
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

import net.miginfocom.swing.MigLayout;

public class TaskPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchField;
	private JTable table;
	private JButton editTaskButton;

	/**
	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TaskPage frame = new TaskPage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TaskPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][][grow]", "[][grow][]"));
		
		JLabel lblCurrentUserInformation = new JLabel("Current user information");
		panel.add(lblCurrentUserInformation, "cell 0 0,alignx center,aligny center");
		
		txtSearchField = new JTextField();
		txtSearchField.setText("Search Field");
		panel.add(txtSearchField, "cell 2 0,alignx center");
		txtSearchField.setColumns(10);
		
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
			}
		});
		panel.add(editTaskButton, "cell 0 2,alignx center,aligny center");
		
		
		
		
	}

}
