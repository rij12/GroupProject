<?php
session_start();
if( !isset($_SESSION["login"]) ){
	$url = "index.php";
	header("Location: $url");
	session_destroy();
}
$name = $_SESSION["login"];

?>
<!DOCTYPE html>
<html>
<head> 
<title> Home </title>
<link rel="stylesheet" type="text/css" href="homeStyles.css">
</head>
<body>
	<div id = "container">

		<div id = "bodyContainer"> 
			<a href = "home.php"><div id ="title"></div></a>
			<div style="color:white;">Welcome <?php echo $name; ?>!</div>
			<div id = "bodyMain">
				<a href = "members.php"><div class = "navOptions" style ="background-image: url(images/membersLogo.png)"></div></a>
				<a href = "createTask.php"><div class = "navOptions" style ="background-image: url(images/createLogo.png)"></div></a>
				<a href = "viewTasks.php"><div class = "navOptions" style ="background-image: url(images/viewTasks.png)"></div></a>
			</div>
		</div>
	</div>
</body>
</html>