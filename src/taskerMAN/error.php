<!DOCTYPE html>
<html>

<head> 
	<title> Error </title>
	<link rel="stylesheet" type="text/css" href="includes/css/styles.css">
	<link rel="stylesheet" type="text/css" href="includes/css/membersStyles.css">
</head>

<body>
	<div id = "container"> 
		<?php include 'includes/php/menu.php'; ?>
		<div id ="bodyContainer">
			<div id ="body">
				<div id = "bodyMain">
					<div id = "errorText">
						<h3>ERROR!</h3></br>
						Can't Connect to the database. Try Again!
					</div>
					<div id = "bodyFooter">
						<a href="home.php"><div id = "return" style = "margin-left: 350px;"> Try Again </div></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>