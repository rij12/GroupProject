package uk.ac.aber.cs221.group16.authenticaion;

import java.sql.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Properties;

import uk.ac.aber.cs221.group16.controller.Task;



/**
 * @author Emil Ramsdal
 *
 */
public class DatabaseConnect {

	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	//private static final String DATABASE_URL = "jdbc:mysql://192.168.1.100:3306/tasker"; // local server
	//private static final String DATABASE_URL = "jdbc:mysql://89.168.25.87:3306/tasker"; // 
	private static final String DATABASE_URL = "jdbc:mysql://db.dcs.aber.ac.uk/csgp_16_15_16"; // 
	private static String dbPassword = "fPeeWanK";
	private static String dbUsername = "csgpadm_16";
	private boolean loggedIn = false;
	private int userId;
	private String userName;


	// for the userlogin 
	private String USERNAME;
	private String PASSWORD;
	private Connection connection = null;


	/**
	 * 
	 * @param theUsername
	 * @param thePassword
	 * 
	 */
	public DatabaseConnect(){

	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String theUserName){
		userName = theUserName;
	}

	public boolean logIn(String email){

		//		System.out.println("login");

		try{
			Connection conn = connect();
			Statement stmt = conn.createStatement();

			ResultSet res = stmt.executeQuery("SELECT * FROM members WHERE email='"+ email +"'");
			if (!res.isBeforeFirst()){
				System.out.println("Wrong email ");
				loggedIn = false;
			}
			else{
				while(res.next()){
					userId = res.getInt("id");
					userName = res.getString("name");
					System.out.println("Logged in");
					System.out.println(userId);
					loggedIn = true;
				}
			}

		}catch(Exception e){
		}
		disconnect();

		return loggedIn;
	}


	/**
	 * @return a connection, that is used to communicate with the databse 
	 * @throws SQLException
	 */
	public Connection connect() throws SQLException{
		if (connection == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				connection = DriverManager.getConnection(DATABASE_URL,dbUsername, dbPassword);		
			} catch (ClassNotFoundException | SQLException  | NullPointerException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}



	/*
	 * @return all the tasks that is allocated to you
	 */
	public ArrayList<Task> getTasks(){
		ArrayList<Task> tasks = new ArrayList<Task>();
		//		System.out.println(userId);

		try{

			Statement st = connect().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM tasks WHERE Status='Allocated' AND MemberAllocated=" + userId);
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

		if(loggedIn){
			tasks = removeDeletedTasks(checkForNewTasks(tasks));
			uploadAllTasks(tasks);
			disconnect();
		}
		else{
			System.out.println("you are not logged in");
		}

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
			if(tasks !=null){
				for(Task t1 : tasks){
					if(t.getId() == (t1.getId())){
						exist = false;
					}
				}

			}
			if(exist){
				tasks.add(t);
			}
		}
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}