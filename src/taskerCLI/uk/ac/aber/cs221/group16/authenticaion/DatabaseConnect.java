package uk.ac.aber.cs221.group16.authenticaion;

import java.sql.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import uk.ac.aber.cs221.group16.controller.Task;



/**
 * @author emil
 *
 */
public class DatabaseConnect {

	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
		//private static final String DATABASE_URL = "jdbc:mysql://192.168.1.100:3306/tasker"; // local server
	//private static final String DATABASE_URL = "jdbc:mysql://89.168.25.87:3306/tasker"; // 
	private static final String DATABASE_URL = "jdbc:mysql://db.dcs.aber.ac.uk/csgp_16_15_16"; // 
	private static String password = "fPeeWanK";
	private static String username = "csgpadm_16";
	private boolean loggedIn = false;
	
	
	// for the userlogin 
	private String USERNAME;
	private String PASSWORD;
	private Connection connection = null;
	
	public int g = 4;
	
	
	
	
	
	/**
	 * 
	 * @param theUsername
	 * @param thePassword
	 * 
	 */
	public DatabaseConnect(){

	}
	

	/**
	 * @return tasks that it get from the server.
	 * only for test purposes
	 */
	private ArrayList<Task> getTasks2(){
		ArrayList<Task> tasks = new ArrayList<Task>();

		try{

			Statement st = connect().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  tasks");
			while (res.next()) {
				int id = res.getInt("TaskID");
				String start = res.getString("StartDate");
				String date = res.getString("DateOfCompletion");
				String headline = res.getString("TitleOfTask");
				String who = res.getString("MemberAllocated");
				String status = res.getString("Status");
				String details = res.getString("Comments");
				Task task = new Task(id, who, details, date, status, headline, start );
				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return tasks;
	}
	


	/**
	 * @return a connection, that is used to communicate with the databse 
	 * @throws SQLException
	 */
	public Connection connect() throws SQLException{
		if (connection == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				connection = DriverManager.getConnection(DATABASE_URL,username, password);		
			} catch (ClassNotFoundException | SQLException  | NullPointerException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}



	/*
	 * @return all the tasks that is
	 */
	private ArrayList<Task> getTasks(){
		ArrayList<Task> tasks = new ArrayList<Task>();

		try{

			Statement st = connect().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  task WHERE who='"+ USERNAME +"' status=" + "allocated");
			while (res.next()) {
				int id = res.getInt("TaskID");
				String start = res.getString("StartDate");
				String date = res.getString("DateOfCompletion");
				String headline = res.getString("TitleOfTask");
				String who = res.getString("MemberAllocated");
				String status = res.getString("Status");
				String details = res.getString("Comments");
				Task task = new Task(id, who, details, date, status, headline, start );
				tasks.add(task);
			}
		} catch (Exception e) {
		}
		return tasks;
	}
	
	/*
	 * @param tasks
	 */
	private void uploadAllTasks(ArrayList<Task> tasks){
		try{
			Connection conn = connect();
		Statement stmt = conn.createStatement();
		
		for(Task t : tasks){
			String id = Integer.toString(t.getId());
			String update = ("UPDATE Task SET Comments='" + t.getTaskInfo() + "',"
					+ " Status='" + t.getStatus() + "' WHERE TaskID='" + id + "'");
				
			@SuppressWarnings("unused")
			int somehing = stmt.executeUpdate(update);
		}
		
		}catch(Exception e){
		}
	}


	public void disconnect(){
		if (connection != null){
			try{
				connection.close();
				connection = null;
			} catch (SQLException e){
//				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This will first download new tasks (if there is any) then it will delete tasks 
	 * that are deleted from the server, then it will upload the local changes 
	 * @param tasks 
	 * @return the updated task list
	 */
	public ArrayList<Task> sync(ArrayList<Task> tasks){
		
		tasks = removeDeletedTasks(checkForNewTasks(tasks));
		uploadAllTasks(tasks);
		disconnect();
		return tasks;	
	}
	
	
	/*
	 * @param tasks -> the local tasks that it will check against the server 
	 * @return -> the updated tasklist after downloading the new ones. 
	 */
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
	
	/*
	 * @param tasks -> the local tasks 
	 * @return
	 */
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