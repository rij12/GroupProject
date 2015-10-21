package uk.ac.aber.cs221.group16.authenticaion;

import java.sql.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Properties;

import tasker.Task;



public class DatabaseConnect {

	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://192.168.1.100:3306/tasker";
	private static final String MAX_POOL = "250";
	private String USERNAME;
	private String PASSWORD;
	private Connection connection = null;
	private Properties properties;

//	ArrayList<Task> tasks = new ArrayList<Task>();



	public DatabaseConnect(String theUsername, String thePassword){
		USERNAME = theUsername;
		PASSWORD = thePassword;

	}


	private Properties getProperties(){
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("MaxPooledStatements", MAX_POOL);
		}
		return properties;
	}

	public Connection connect() throws SQLException{
		if (connection == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				connection = DriverManager.getConnection(DATABASE_URL, getProperties());
			} catch (ClassNotFoundException | SQLException  | NullPointerException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}



	public ArrayList<Task> getTasks(){
		ArrayList<Task> tasks = new ArrayList<Task>();

		try{

			Statement st = connect().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  Task WHERE who='"+ USERNAME +"'");
			while (res.next()) {
				int id = res.getInt("TaskId");
				String who = res.getString("Who");
				String headline = res.getString("Headline");
				String details = res.getString("Details");
				Date date = res.getDate("Deadline");
				String status = res.getString("Status");
				Task task = new Task(id, who, details, date, status, headline );
				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return tasks;
	}
	
	private void uploadAllTasks(ArrayList<Task> tasks){
		try{
			Connection conn = connect();
		Statement stmt = conn.createStatement();
		
		for(Task t : tasks){
			String id = Integer.toString(t.getId());
			String update = ("UPDATE Task SET Details='" + t.getTaskInfo() + "',"
					+ " Status='" + t.getStatus() + "' WHERE TaskId='" + id + "'");
				
			@SuppressWarnings("unused")
			int somehing = stmt.executeUpdate(update);
		}
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}


	public void disconnect(){
		if (connection != null){
			try{
				connection.close();
				connection = null;
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Task> sync(ArrayList<Task> tasks){
		
		tasks = removeDeletedTasks(checkForNewTasks(tasks));
		uploadAllTasks(tasks);
		return tasks;	
	}
	
	
	private ArrayList<Task> checkForNewTasks(ArrayList<Task> tasks){
		ArrayList<Task> serverTasks = new ArrayList<Task>();
		serverTasks = getTasks();
		for(Task t: serverTasks){
			boolean exist = true;
			for(Task t1 : tasks){
				if(t.getId() == (t1.getId())){
					exist = false;
				}
				
			}
			if(exist){
				tasks.add(t);
			}
		}
		System.out.println("After check for new tasks");
		System.out.println(tasks);
		return tasks;
	}
	
	private ArrayList<Task> removeDeletedTasks(ArrayList<Task> tasks){
		ArrayList<Task> serverTasks = new ArrayList<Task>();
		serverTasks = getTasks();
		try{
			for(Task t : tasks){
				boolean exist = false;
				for(Task t1 : serverTasks){
					if(t1.getId() == t.getId()){
						exist = true;
					}
				}
				if(!exist){
					tasks.remove(t);
				}
			}
			
		}catch(ConcurrentModificationException e){
//			e.printStackTrace();
		}

		return tasks;
	}
}