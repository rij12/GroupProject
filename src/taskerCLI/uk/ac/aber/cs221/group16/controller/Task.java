/*
* @(#) Task.java
*
* Copyright (c) 2016 Group 16 
* All rights reserved.
*
*/

/**
 * This class defines what a task looks like and provdies the methods that are used 
 * for updating them. 
 * 
 * @author Emil Ramsdal
 * @see MainFrame.java
 * @version 1
 * 
 */

package uk.ac.aber.cs221.group16.controller;

public class Task {
	
	private int id;
	private String user;
	private String taskInfo;
	private String deadLine;
	private String status;
	private String title;
	private String startDate;
	
	public Task(){
		
	}
	
	
	public Task(int theId, String theUser, String theTaskInfo, String date, String theStatus, String theTitle, String theStartDate){

		id = theId;
		user = theUser;
		taskInfo = theTaskInfo;
		deadLine = date;	
		status = theStatus;
		title = theTitle;
		startDate = theStartDate;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", user=" + user + ", taskInfo=" + taskInfo
				+ ", deadLine=" + deadLine + ", status=" + status + ", title="
				+ title + ", startDate=" + startDate + "]\n";
	}


}