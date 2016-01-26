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
	 * This is something for later 
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
	 * This would take an arrayList and save it to filename. 
	 */
	public void save(ArrayList<Task> tasks, String userName2){
		try(FileWriter fw = new FileWriter(filename);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outfile = new PrintWriter(bw);){
			
			for(Task t : tasks){
				if(t.getStatus().equals("Complete")){
					tasks.remove(t);
				}
			}

			
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
			System.err.println("Problem when trying to write to file: " + filename);
		}

	}
	   private static int countLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines.length;
		}
	public ArrayList<Task> load(String username){
		
		ArrayList<Task> tasks = new ArrayList();

		try(FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				Scanner infile = new Scanner(br)){
			int numTasks = infile.nextInt();
			infile.nextLine();
			
			userName = infile.nextLine();
			if(username != userName){
				return null;
			}
			
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