<?php
session_start();
if( !isset($_SESSION["login"]) ){
	$url = "../../index.php";
	header("Location: $url");
	session_destroy();
}
error_reporting(0);
?>
<div id = "header">
	<div id ="title"><a href = "home.php"><div id = "logo"></div></div></a>
	<div id = "nav">
		<a href = "members.php"><div class = "navOptions">Team members</div></a>
		<a href = "createTask.php"><div class = "navOptions">Create task</div></a>
		<a href = "viewTasks.php"><div class = "navOptions">View tasks</div></a>
		<a href = "includes/php/logout.php" onclick="return confirm('Are you sure you want to logout?')"><div class = "navOptions">Logout</div></a>	
	</div>
</div>