<!DOCTYPE html>
<html>
<head> 
<title> Home </title>
<link rel="stylesheet" type="text/css" href="homeStyles.css">
</head>
<body>
	<div id = "container">

		<div id = "bodyContainer"> 
			<div id ="title"></div>
			<div id = "loginArea">
				
					<form name ="login" method="post" action = "checkLogin.php">
					<div class = "loginSection">
						<b> Username: </b>
						<input type="text" name="username" value="" pattern="[a-zA-Z0-9 ]+" maxlength="50" required><br>
				</div>
				<div class = "loginSection">
					<b> Password: </b>
					<input type="password" name="password" value="" pattern="[a-zA-Z0-9 ]+" maxlength="50" required>
				</div>
				<div class = "loginSection">
					<div <?php 
					if(!(isset($_GET['denied']) && ($_GET['denied'] == "1" || $_GET['denied'] == "2"))){
						echo 'hidden';
					}
					?> name="text">
					<?php 
					if($_GET['denied'] == "1"){
						echo 'Username and passwords do not match';
					}else if($_GET['denied'] == "2"){
						echo 'You do not have Permission to Logon to this Website';
					}
					?>
					</div>
				</div>
				<div class = "button" style ="margin-left:5em">
					<input type = "submit" value = "Login">
				</div>
				</form>
				<form action="checkLogin.php" method="post">		
				</form>
			</div>
		</div>
	</div>
</body>
</html>