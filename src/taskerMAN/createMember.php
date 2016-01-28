<!DOCTYPE html>
<html>
<head> 
<title> Members </title>
<!-- CSS style sheets used within the page are declared here -->
<link rel="stylesheet" type="text/css" href="includes/css/styles.css">
<link rel="stylesheet" type="text/css" href="includes/css/tasksStyles.css">
</head>
<body>

	<div id = "container"> 
		<!-- includes php file containing the navigation, which saves time when making changes to it, and makes for cleaner, shorter files -->
		<?php include 'includes/php/menu.php'; ?>

		<div id ="bodyContainer">
			<div id ="body">
				<form id="addMember" action="includes/php/addMember.php" method="post" enctype="multipart/form-data" accept-charset="utf-8">

					<div id = "editTaskBodyMain">

						<div class = "viewTaskOptions">
							<div class = "editDescription">Name:</div>
							<input type="text" name="name" maxlength="50" pattern="[a-zA-Z0-9 ]+" required>
						</div>

						<div class = "viewTaskOptions">
							<div class = "editDescription">Email Address:</div>
							<input type="email" name="email" maxlength="50" placeholder="youremail@email.co.uk" required>
							<div <?php
									if(!(isset($_GET['failed']) && $_GET['failed'] == 1)){
										echo ' hidden';
									}
								?> name="text">
								<?php
									if($_GET['failed'] == 1){
										echo '<div style = "text-indent:25px; font-family: tahoma;">The email address you specified already exists</div>';
									}
								?>
							</div>
						</div>	

						<div class = "viewTaskOptions">
							<div class = "editDescription">Password:</div>
							<input type="password" name="password" maxlength="50" placeholder="password" pattern="[a-zA-Z0-9]+" required>
						</div>

						<div class = "viewTaskOptions">
							<div class = "editDescription">Profile Pic:</div>
							<input type="file" name="picture" accept="image/*">
						</div>	

						<div id = "bodyFooter">
							<input id="add" type="submit" value="Submit">
							<a href="members.php"><div id = "remove"> Cancel </div></a>
						</div>

					</div>
					
				</form>

			</div>
		</div>

	</div>

</body>
</html>