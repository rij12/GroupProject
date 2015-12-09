package uk.ac.aber.cs221.group16.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Emil Ramsdal
 *
 */
public class Load {		

	private static String saveFolder;
	private static String filename = "saveLocation.txt";
	private Scanner scan;
	private String userName;
	
	/**
	 * 
	 */
	public Load(){
	}
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String theUserName){
		userName = theUserName;
	}

	/**
	 * 
	 */
	public void readSaveLocation(){
		/*psudo code!!
			if the file is null;
				start the chooselocation thingy
				
					saveFolder = chooselocation();
				
			else
				read the file location from file
					saveFolder = from file;

		*/

	}
	
	
	/**
	 * @param tasks
	 */
	public void save(ArrayList<Task> tasks, String userName2){
		try(FileWriter fw = new FileWriter(filename);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outfile = new PrintWriter(bw);){

			
			outfile.println(tasks.size());
			outfile.println(userName2);
			
			for (Task t: tasks){
				outfile.println(t.getId());
				outfile.println(t.getUser());
				outfile.println(t.getTaskInfo());
				outfile.println(t.getDeadLine());
				outfile.println(t.getStatus());
				outfile.println(t.getTitle());
				outfile.println(t.getStartDate());
			}
		} catch (IOException e) {
			System.err.println("Problem when trying to write to file: " + filename);
		}

	}

	public ArrayList<Task> load(){
		ArrayList<Task> tasks = new ArrayList();

		try(FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				Scanner infile = new Scanner(br)){
			int numTasks = infile.nextInt();
			infile.nextLine();
			
			userName = infile.nextLine();
			
			for(int i = 0; i < numTasks; i++){
				int id = infile.nextInt();
				infile.nextLine();
				String user = infile.nextLine();
				String taskInfo = infile.nextLine();
				String deadLine = infile.nextLine();
				String status = infile.nextLine();
				String title = infile.nextLine();
				String startDate = infile.nextLine();
				
				Task task  = new Task(id, user, taskInfo, deadLine, status, title, startDate);
				tasks.add(task);
			}
			return tasks;

	} catch (FileNotFoundException e) {
		System.err.println("The file: " + " does not exist. Assuming first use and an empty file." +
	                       " If this is not the first use then have you accidentally deleted the file?");
	} catch (IOException e) {
		System.err.println("An unexpected error occurred when trying to open the file " + filename);
		System.err.println(e.getMessage());
	}catch (NoSuchElementException e){
		
	}
	return null;
		
	}
}