<?php //Session start to allow for only users with permission to enter website, if they are already logged in redirect them to the home page
session_start();
if(isset($_SESSION["login"]) ){
	$url = "home.php";
	header("Location: $url");
}


?>
<!DOCTYPE html>
<html>

<head> 
	<title> Home </title>
	<!-- CSS style sheets used within the page are declared here -->
	<link rel="stylesheet" type="text/css" href="includes/css/homeStyles.css">
</head>

<body>
	<div id = "container">		<!-- container ensures the web page will be completely full screen and sets the foundation of the page -->
		<div id = "bodyContainer"> 
			<div id ="title"></div> 		<!-- "title" displays the banner of the website within its CSS code -->

			<!-- here an error message will be displayed if the user has tried to log in inccorectly -->
			<div class = "errorField"			
				<?php 
					if(!(isset($_GET['denied']) && ($_GET['denied'] == "1" || $_GET['denied'] == "2"))){
						echo 'hidden';
					}
				?> >
				<?php 
					if($_GET['denied'] == "1"){
						echo 'Username and passwords do not match';
					}else if($_GET['denied'] == "2"){
						echo 'You do not have Permission to Logon to this Website';
					}
				?> 
			</div>
			<div id = "loginArea">				<!-- loginArea is the area in which the user can log in from -->
				<form name ="login" method="post" action = "includes/php/checkLogin.php">
					<div id = "loginLeft">		<!-- loginArea is split into two halfs for presentation purposes -->
						Username:<br>
						Password:
					</div>

					<div id = "loginRight">		<!-- loginArea is split into two halfs for presentation purposes -->
						<input type="text" name="username" value="" maxlength="50" size="29" required><br>
						<input type="password"  pattern = "[a-zA-Z0-9 ]+" name="password" value="" maxlength="50" size="29" required>
						
							<input type = "submit" value = "Login">
						
						</div>
				</form>

				<div class = "errorField">			<!-- errorField is re-used to save memory in CSS file -->
					<a href = "files/TaskerCLI.jar" target = "_blank">		<!-- Link to download the taskerCLI client -->
						<div id = "downloadLink"> Dowload Client </div>
					</a>
				</div>

					<form action="includes/php/checkLogin.php" method="post"></form>	 <!-- includes checkLogin to validate login -->
					</div>
		</div>
	</div>
</body>

</html>