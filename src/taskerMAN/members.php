<?php 
	require 'connect.php';
	
	$sql = "SELECT * FROM members";
	$result = mysqli_query($con,$sql);
	
 ?>
<!DOCTYPE html>
<html>
<head> 
<title> Members </title>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" type="text/css" href="membersStyles.css">
</head>
<body>
	<div id = "container"> 
		<?php include 'menu.php'; ?>
		<div id ="bodyContainer">
			<div id ="body">
				<div id = "bodyMain">
				<?php
					while($row = mysqli_fetch_array($result)) {							
					echo'
					<a href = "membersInfo.php?id='. $row['id'] . '"><div class = "members">
						<div class = "membersName">' . $row['name'] . '</div>
						<div class = "membersProfile"><img src="images/profilepics/pic'. $row['id'] . '.png" alt="portfolio image" height="150" width="148"></div>
					</div></a>';
					
					} 
					
					?>
				</div>
				<div id = "bodyFooter">
					<a href="createMember.php"><div id = "add"> Add </div></a>
					<a href="removeMember.php"><div id = "remove"> Remove </div></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>