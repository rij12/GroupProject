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
	<link rel="stylesheet" type="text/css" href="includes/css/homeStyles.css">
</head>

<body>
	<div id = "container">
		<div id = "bodyContainer"> 
			<a href = "home.php"><div id ="title"></div></a>
			<div id = "errorField">Welcome <?php echo $name; ?>!</div>
			<div id = "bodyMain">
				<div id = "navOptionsContainer">
					<a href = "members.php"><div class = "navOptions" style ="background-image: url(images/membersLogo.png);"></div></a>
					<a href = "createTask.php"><div class = "navOptions" style ="background-image: url(images/createLogo.png)"></div></a>
					<a href = "viewTasks.php"><div class = "navOptions" style ="background-image: url(images/viewTasks.png)"></div></a>
					<a href = "includes/php/logout.php" onclick="return confirm('Are you sure you want to logout?')"><div class = "navOptions" style ="background-image: url(images/logout.png)">
					</div></a>
				</div>
			</div>
		</div>
	</div>
</body>

</html>