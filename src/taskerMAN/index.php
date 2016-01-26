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
						<input type="text" name="username" value="" required><br>
				</div>
				<div class = "loginSection">
					<b> Password: </b>
					<input type="password" name="password" value="" required>
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