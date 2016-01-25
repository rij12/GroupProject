package uk.ac.aber.cs221.group16.authenticaion;
//
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;

import uk.ac.aber.cs221.group16.controller.Load;
import uk.ac.aber.cs221.group16.controller.Task;
import uk.ac.aber.cs221.group16.authenticaion.*;

public class Main {


//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
				Scanner scan;
				static DatabaseConnect con = new DatabaseConnect();
				static Load saveAndLoad = new Load();
				static ArrayList<Task> tasks = new ArrayList<Task>();
//	}
	
	
	   public static void main(String[] args) {
		   // creating timer task, timer

		   
//		   Timer timer = new Timer ();
//		   TimerTask hourlyTask = new TimerTask () {
//		       @Override
//		       public void run () {
//		           System.out.println("Test!!");
//		       }
//		   };
//
//		   // schedule the task to run starting now and then every hour...
//		   timer.schedule (hourlyTask, 0l, 1000);
		   
		   
		   tasks = saveAndLoad.load();
		   
		   System.out.println(tasks);
		   
		   con.logIn("emr18@aber.ac.uk");
		   tasks = con.sync(tasks);
		   System.out.println(tasks);
		   
//		   saveAndLoad.save(tasks, tasks.get(0).getUser());
		   
		   tasks.get(0).setStatus("Complete");
		   tasks = con.sync(tasks);
		   
		  
		   saveAndLoad.save(tasks, tasks.get(0).getUser());
		   System.out.println(tasks);
//		   saveAndLoad.save(tasks, tasks.get(0).getUser());
		   
		   
		   
}
}