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
				<div class = "loginSection">
					<form name ="login" method="post" action = "checkLogin.php" style ="margin-left:0.65em">
						<b> Username: </b>
						<input type="text" name="username" value="" required></br>
				</div>
				<div class = "loginSection" style ="margin-left:1em">
					<b> Password: </b>
					<input type="password" name="password" value="" required>
				</div>
				<div class = "button" style ="margin-left:5em">
					<input type = "submit" value = "Login">
				</div>
				</form>
				<form action="checkLogin.php" method="post">
				<div class = "button">
					<input type="hidden" value="guest" name="guest">
					<input type = "submit" value = "Guest">	
				</div>			
				</form>
			</div>
		</div>
	</div>
</body>
</html>