<!DOCTYPE html>
<html>
<head> 
<title> Members </title>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" type="text/css" href="tasksStyles.css">
</head>
<body>
	<div id = "container"> 
		<?php include 'menu.php'; ?>
		<div id ="bodyContainer">
			<div id ="body">
				<form id="addMember" action="addMember.php" method="post" enctype="multipart/form-data" accept-charset="utf-8">
					<div id = "editTaskBodyMain">
						<div class = "viewTaskOptions">
							<div class = "editDescription">Name:</div>
							<input type="text" name="name" required>
						</div>
						<div class = "viewTaskOptions">
							<div class = "editDescription">Email Address:</div>
							<input type="email" name="email" placeholder="youremail@email.co.uk" required>
						</div>	
						<div class = "viewTaskOptions">
							<div class = "editDescription">Password:</div>
							<input type="password" name="password" placeholder="password" required>
						</div>
						<div class = "viewTaskOptions">
							<div class = "editDescription">Profile Pic:</div>
							<input type="file" name="picture" accept="image/*">
						</div>	
						<div id = "bodyFooter">
							<input id="add" type="submit" value="submit">
							<a href="members.php"><div id = "remove"> Cancel </div></a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>