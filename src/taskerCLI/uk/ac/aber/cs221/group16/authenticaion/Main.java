package uk.ac.aber.cs221.group16.authenticaion;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.Task;

public class Main {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan;
		DatabaseConnect con = new DatabaseConnect();
		Load saveAndLoad = new Load();
		ArrayList<Task> tasks = new ArrayList<Task>();
	
		
		
		tasks = saveAndLoad.load();
		
		System.out.println(tasks);
		
		
//		System.out.println("enter email ");
//		scan = new Scanner(System.in);
//		
//		String email = scan.nextLine();
		

		
//		tasks = con.getTasks2();
//		System.out.println(tasks);
		
//		boolean loggedin = con.logIn(email);
//		
//		tasks = con.sync(tasks);
//		System.out.println(tasks);
//		
//		saveAndLoad.save(tasks);
		
		
//		tasks = con.getTasks2();
//		System.out.println(tasks);
		
	}
	

}
