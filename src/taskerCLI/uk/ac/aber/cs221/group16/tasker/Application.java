package tasker;


import java.util.ArrayList;
import java.util.Scanner;

import uk.ac.aber.cs221.group16.authenticaion.DatabaseConnect;
import menu.MainMenu;

public class Main {
	
	ArrayList<Task> tasks = new ArrayList<Task>();
	DatabaseConnect connect = null;
	
	ArrayList<Task> serverTasks = new ArrayList<Task>();

	
	private String userName;
	private String password;
	private Scanner in;

	
	public Main(){
		try{
			in = new Scanner(System.in);
			

		} catch(NullPointerException e){
			
		}

		
	}
	
	public void logIn(){
		
		do{
			System.out.println("enter username");
			userName = in.nextLine();
			System.out.println("Enter password");
			password = in.nextLine();
			
			connect = new DatabaseConnect(userName, password);

			
		}while(connect.equals(null));
		
	}
	


	public void runMenu(){
		MainMenu mainMenu = new MainMenu();
		int response;
		do{
			response = mainMenu.runMenu();
			switch (response) {
			case 1:
				logIn();
				
				break;
			case 2:
				System.out.println(tasks);
				break;
			case 3:			
				tasks = connect.sync(tasks);
				break;
			case 4:			
				tasks = connect.getTasks();
//				serverTasks = connect.getTasks();
				break;

			default:
				System.out.println("Try again");
			} 
		}while (response != 5);



	}
	

	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Main program = new Main();
		
		
		// loadTasks();
		program.logIn();
		program.runMenu();

	
		

		
		
		/*
		 * Show gui
		 * authenticate 
		 * load tasks from hdd into program 
		 * check tasks against server (but which should be kept)
		 * Download from server I guess >.> 
		 * 
		 *  
		 */
		
		
		
	}

}
