<?php //Session start to allow for only users with permission to enter website
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
	<!-- CSS style sheets used within the page are declared here -->
	<link rel="stylesheet" type="text/css" href="includes/css/homeStyles.css">
</head>

<body>
	<div id = "container">
		<div id = "bodyContainer"> 
			<a href = "home.php"><div id ="title"></div></a>
			<div class = "errorField">Welcome <?php echo $name; ?>!</div> <!-- Welcome message for whoever is logged in at the time -->
			<div id = "bodyMain">
				<div id = "navOptionsContainer">		<!-- Navigation options -->
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