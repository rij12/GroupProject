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

						<!-- User input field with validation and instruction for name -->
						<div class = "viewTaskOptions">
							<div class = "editDescription">Name:</div> 		<!-- Instruction -->
							<input type="text" name="name" maxlength="20" pattern="[a-zA-Z ]+" required> 	<!-- input -->
						</div>

						<!-- User input field with validation and instruction for email -->
						<div class = "viewTaskOptions">
							<div class = "editDescription">Email Address:</div>		<!-- Instruction -->
							<input type="email" name="email" maxlength="50" placeholder="youremail@email.co.uk" required> 	<!-- input -->
							<div <?php
									// Stops douplicate emails by checking for existing emails matching input
									if(!(isset($_GET['failed']) && $_GET['failed'] == 1)){
										echo ' hidden';
									}
								?> name="text">
								<?php
									if($_GET['failed'] == 1){	 // If failed, post error message
										echo '<div style = "text-indent:25px; font-family: tahoma;">The email address you specified already exists</div>';
									}
								?>
							</div>
						</div>	

						<!-- User input field with validation and instruction for password -->
						<div class = "viewTaskOptions">
							<div class = "editDescription">Password:</div>		<!-- Instruction -->
							<input type="password" name="password" maxlength="50" placeholder="password" pattern="[a-zA-Z0-9]+" required> <!-- input -->
						</div>

						<!-- User input field with instruction for profile picture -->
						<div class = "viewTaskOptions">
							<div class = "editDescription">Profile Pic:</div> 	<!-- Instruction -->
							<input type="file" name="picture" accept="image/*"> <!-- input -->
						</div>	

						<!-- Submit and finish creation, or cancel and go back to members page -->
						<div id = "bodyFooter">
							<input id="add" type="submit" value="Submit">		<!-- Submit button -->
							<a href="members.php"><div id = "remove"> Cancel </div></a>	<!-- Cancel button -->
						</div>

					</div>
					
				</form>

			</div>
		</div>

	</div>

</body>
</html>