package uk.ac.aber.cs221.group16.authenticaion;
//
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
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
		   
		   String a = "test";
		   String b = "stuff";
		   
		   System.out.println(a.concat(b));
		   
		   
//		   System.out.println(haveInternet());
		   
		   System.out.println(isReachable("db.dcs.aber.ac.uk", 80, 10));
		   

//		   System.out.println(countLines("adsasdasd"));


//		   new ReminderBeep(10);
		   
		   
		   
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
		   
		   
//		   tasks = saveAndLoad.load();
//		   
//		   System.out.println(tasks);
		   
//		   con.logIn("emr18@aber.ac.uk");
//		   tasks = con.sync(tasks);
//		   System.out.println(tasks);
//		   
//		   saveAndLoad.save(tasks, tasks.get(0).getUser());
//		   
//		   tasks.get(0).setStatus("Complete");
//		   tasks = con.sync(tasks);
//		   
//		  
//		   saveAndLoad.save(tasks, tasks.get(0).getUser());
//		   System.out.println(tasks);
//		   saveAndLoad.save(tasks, tasks.get(0).getUser());
		   
		   
		   
}
	   
	   private static int countLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines.length;
		}
	   
		public static boolean haveInternet(){
			Process p1 = null;
			try {
				p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 db.dcs.aber.ac.uk");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int returnVal = 0;
			try {
				returnVal = p1.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean reachable = (returnVal==0);
				
			
			return reachable;
		}
	    private static boolean isReachable(String addr, int openPort, int timeOutMillis) {
	        // Any Open port on other machine
	        // openPort =  22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
	        try {
	            try (Socket soc = new Socket()) {
	                soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
	            }
	            return true;
	        } catch (IOException ex) {
	            return false;
	        }
	    }
	   
}