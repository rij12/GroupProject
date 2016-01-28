<?php 
	require 'includes/php/connect.php';
	
	$sql = "SELECT * FROM members";
	$result = mysqli_query($con,$sql);
	
 ?>

<!DOCTYPE html>
<html>

<head> 
	<title> Members </title>
	<link rel="stylesheet" type="text/css" href="includes/css/styles.css">
	<link rel="stylesheet" type="text/css" href="includes/css/membersStyles.css">
</head>

<body>
	<div id = "container"> 
		<?php include 'includes/php/menu.php'; ?>
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
					<div id = "bodyFooter">
						<a href="createMember.php"><div id = "memberAdd"> Add </div></a>
						<a href="removeMember.php"><div id = "memberRemove"> Remove </div></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>