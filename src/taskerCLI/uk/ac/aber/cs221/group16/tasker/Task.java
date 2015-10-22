package tasker;

import java.util.Date;


public class Task {
	
	private int id;
	private String user;
	private String taskInfo;
	private Date deadLine;
	private String status;
	private String headLine;
	
	
	
	
	public Task(int theId, String theUser, String theTaskInfo, Date date, String theStatus, String theHeadLine){

		id = theId;
		user = theUser;
		taskInfo = theTaskInfo;
		deadLine = date;	
		status = theStatus;
		headLine = theHeadLine;
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


	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
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

	public String getHeadLine() {
		return headLine;
	}

	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", user=" + user + ", taskInfo=" + taskInfo
				+ ", deadLine=" + deadLine + ", status=" + status + "] \n";
	}


}
