/*
 * @(#) DatabaseConnect.java
 *
 * Copyright (c) 2016 Group 16 
 * All rights reserved.
 *
 */

/**
 * A class that handles all the database connectivity which includes syncronising, 
 * downloading and uploading changes.
 *
 * You should only create one DatabaseConnect() and only use sync() and logIn()
 *
 * @author Emil Ramsdal
 * @since 1  Initial development.
 * @version 1.2  Now works with modified database structure.
 */

package uk.ac.aber.cs221.group16.authenticaion;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;

import com.mysql.jdbc.UpdatableResultSet;

import uk.ac.aber.cs221.group16.controller.Task;


public class DatabaseConnect {

	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://db.dcs.aber.ac.uk/csgp_16_15_16"; // 
	private static String dbPassword = "fPeeWanK";
	private static String dbUsername = "csgpadm_16";
	private boolean loggedIn = false;
	private int userId;
	private String userName;


	// for the userlogin 
	private Connection connection = null;
	private boolean didItSync;


	/**
	 * 
	 * @param theUsername
	 * @param thePassword
	 * 
	 */
	public DatabaseConnect(){

	}



	/**
	 * This method returns the username
	 * @return
	 */
	public String getUserName(){
		return userName;
	}


	/**
	 * Checks for a working networking card that is connect to the internet.
	 */
	private boolean checkForInterfaces(){

		Enumeration<NetworkInterface> interfaces = null;
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (interfaces.hasMoreElements()) {
			NetworkInterface interf = interfaces.nextElement();
			try {
				if (interf.isUp() && !interf.isLoopback()){
					return true;
				}
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	/**
	 * This method will check if the server is reachable. 
	 * @param addr - the address you want to check if it is available
	 * @param openPort - the port you want to access
	 * @param timeOutMillis - the timeout before you give up
	 * @return
	 */
	private static boolean isReachable(String addr, int openPort, int timeOutMillis) {
		try {
			try (Socket soc = new Socket()) {
				soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
			}
			return true;
		} catch (IOException ex) {
			return false;
		}
	}

	public boolean haveInternet(){
		if(!checkForInterfaces()){
			return false;
		}
		else if(!isReachable("aber.ac.uk", 80, 100)){
			return false;
		}
		return true;
	}

	/**
	 * This method is for hashing the password. 
	 * @param password The password the user puts in
	 * @return - the hashed value of the entered password
	 */
	private String hashing(String password){

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		//convert the byte to hex format 
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}		

		return sb.toString();
	}



	/**
	 * This method logs the user in to the database server
	 * @param email - users email 
	 * @param userPassword - this is the password before hashing
	 * @return - a boolean which represent if the user is logged in or not 
	 */
	public boolean logIn(String email, char[] userPassword){

		String text = String.valueOf(userPassword);

		if(!haveInternet()){
			return false;
		}

		try{
			Connection conn = connect();
			Statement stmt = conn.createStatement();

			ResultSet res = stmt.executeQuery("SELECT * FROM members WHERE email='"+ email +"' AND password='" + hashing(text) +"'");
			if (!res.isBeforeFirst()){
				loggedIn = false;
			}
			else{
				while(res.next()){
					userId = res.getInt("id");
					userName = res.getString("name");
					loggedIn = true;
				}
			}

		}catch(Exception e){
		}
		disconnect();

		return loggedIn;
	}


	/**
	 * This method is that which creates the connection. 
	 * @return a connection, that is used to communicate with the database 
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



	/**
	 * This method will get all the tasks from the server
	 * @return all the tasks that is allocated to you
	 */
	public ArrayList<Task> getTasks(){
		ArrayList<Task> tasks = new ArrayList<Task>();
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
	 * This will upload all the tasks to the databse 
	 * @param tasks
	 */
	private void uploadAllTasks(ArrayList<Task> tasks){
		try{
			Connection conn = connect();
			Statement stmt = conn.createStatement();

			for(Task t : tasks){
				String id = Integer.toString(t.getId());
				String update = ("UPDATE tasks SET Comments='" + t.getTaskInfo() + "',"
						+ " Status='" + t.getStatus() + "' WHERE TaskID=" + id + "");

				@SuppressWarnings("unused")
				int somehing = stmt.executeUpdate(update);
			}

		}catch(Exception e){
		}
	}

	/*
	 *this method closes the connection to the database 
	 */
	public void disconnect(){
		if (connection != null){
			try{
				connection.close();
				connection = null;
			} catch (SQLException e){
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
		ArrayList<Task> tempTasks = new ArrayList<Task>();
		if(loggedIn && haveInternet()){
			ArrayList<Task> serverTasks = new ArrayList<Task>();
			serverTasks = getTasks();

			tempTasks = checkForNewTasks(tasks, serverTasks);
			tempTasks= removeDeletedTasks(tempTasks, serverTasks);
			uploadAllTasks(tempTasks);

			disconnect();
		}
		else{
			System.err.println("Not logged in or you dont have internet.");
			didItSync = false;
		}
		didItSync = true;
		return tempTasks;	
	}


	/*
	 * This method will chekc if there is any new tasks on the server are not downloaded yet.
	 * @param tasks -> the local tasks that it will check against the server 
	 * @return -> the updated tasklist after downloading the new ones. 
	 */
	private ArrayList<Task> checkForNewTasks(ArrayList<Task> tasks, ArrayList<Task> serverTasks){
		for(Task t: serverTasks){
			boolean isNew = true;
			if(tasks !=null){
				for(Task t1 : tasks){
					if(t.getId() == (t1.getId())){
						isNew = false;
					}
				}
			}
			if(isNew){
				tasks.add(t);
			}
		}
		return tasks;
	}

	/*
	 * This method will check if any tasks that you allready have is deleted. 
	 * @param tasks -> the local tasks 
	 * @return
	 */
	private ArrayList<Task> removeDeletedTasks(ArrayList<Task> tasks, ArrayList<Task> serverTasks){
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

		}

		return tasks;
	}

	/**
	 * returns true if the user is logged in 
	 * @return
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Sets the status og the login.
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean getDidItSync(){
		return didItSync;
	}

}
