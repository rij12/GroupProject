/*
* @(#) Load.java
*
* Copyright (c) 2016 Group 16 
* All rights reserved.
*
*/

/**
 * This Class handles everything that have to do with save and load of 
 * tasks. 
 * 
 * @author Emil Ramsdal
 * @see MainFrame.java
 * @version 1
 * 
 */

package uk.ac.aber.cs221.group16.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Load {		

	private static String saveFolder;	
	private static String filename = "tasks.txt";
	private Scanner scan;
	private String userName;
	
	String path;
	File file;
	


	/**
	 *  Creates the folder and the file used to store the task on locally. 
	 */
	public Load(){
		path = System.getProperty("user.home") + File.separator + "Documents";
		path += File.separator + "TaskerCLI";
		File customDir = new File(path);	

		file = new File(path +"/" + filename);
	}

	/**
	 * 
	 * @return the username
	 */
	public String getUserName(){
		return userName;
	}

	/**
	 * This takes the array list with tasks and save them to the choosen save location 
	 * @param tasks
	 */
	public void save(ArrayList<Task> tasks, String userName2){
		try(FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outfile = new PrintWriter(bw);){

			outfile.println(tasks.size()); // the number of tasks
			outfile.println(userName2); // your username 

			// THe different task information 
			for (Task t: tasks){
				outfile.println(t.getId());
				outfile.println(t.getUser());
				outfile.println(countLines(t.getTaskInfo()));
				outfile.println(t.getTaskInfo());
				outfile.println(t.getDeadLine());
				outfile.println(t.getStatus());
				outfile.println(t.getTitle());
				outfile.println(t.getStartDate());
			}
		} catch (IOException e) {
			System.err.println("Problem when trying to write to file: " + file);
		}
	}
	
	/**
	 * This method counts the number of lines in the 
	 * task description, and is used so we are able to save multi-line description 
	 * @param str - the string that is being checked
	 * @return - the number lines
	 */
	private static int countLines(String str){
		String[] lines = str.split("\r\n|\r|\n");
		return  lines.length;
	}
	
	/**
	 * This method loads the tasks
	 * @param username - checks against the user and checks if it is the same 
	 * user that logges in as last time.
	 * @param isLoggedIn - a boolean which is true when the user is logged in 
	 * @return - a array list with all the tasks 
	 */
	public ArrayList<Task> load(String username, boolean isLoggedIn){

		ArrayList<Task> tasks = new ArrayList();

		try(FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				Scanner infile = new Scanner(br)){
			int numTasks = infile.nextInt();
			infile.nextLine();

			userName = infile.nextLine();
			if(!isLoggedIn || userName.equals(username)){

				
				// all the tasks 
				for(int i = 0; i < numTasks; i++){
					String taskInfo = "";
					int id = infile.nextInt();
					infile.nextLine();
					String user = infile.nextLine();
					int numberOfLines = infile.nextInt();
					infile.nextLine();
					for(int j = 0; j < numberOfLines; j++){
						taskInfo = taskInfo + infile.nextLine();
					}
					String deadLine = infile.nextLine();
					String status = infile.nextLine();
					String title = infile.nextLine();
					String startDate = infile.nextLine();

					Task task  = new Task(id, user, taskInfo, deadLine, status, title, startDate);
					tasks.add(task);
				}
			}
			return tasks;

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}catch (NoSuchElementException e){

		}
		return null;

	}
}
