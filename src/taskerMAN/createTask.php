<!DOCTYPE html>
<html>
<head> 
<title> Tasks </title>
<!-- CSS style sheets used within the page are declared here -->
<link rel="stylesheet" type="text/css" href="includes/css/styles.css">
<link rel="stylesheet" type="text/css" href="includes/css/tasksStyles.css">
<script src="scripts/scripts.js"></script>
</head>
<body>

<!-- includes php file which allows connection to the database -->
<?php
	require 'includes/php/connect.php';
?>

<div id = "container"> 

<!-- includes php file containing the navigation, which saves time when making changes to it, and makes for cleaner, shorter files -->
<?php include 'includes/php/menu.php'; ?>

<div id ="bodyContainer">

	<div id ="body">
		<form method="POST" name="process" action="includes/php/processTask.php" onsubmit="return validateForm()">
			<div id = "editTaskBodyMain">

				<div class = "viewTaskOptions">
					<div class = "editDescription">Task Title:</div>
					<input type="text" name="title" maxlength="50" pattern="[a-zA-Z0-9 ]+" required>
				</div>

				<div class = "viewTaskOptions">
					<div class = "editDescription">Allocated Member:</div>
					<div class = "editDescription" style = "margin-left: -85px;">
						<select name="member">;
							<?php
								$sql = "SELECT * FROM members";
								$result = $con->query($sql);

								if ($result->num_rows > 0) {
								    while ($row = $result->fetch_assoc()) {
									
										echo '<option value="' .$row['id']. '"> '.$row['name']. '</option>';
									}
								} else {
							    	echo "0 results";
								} 
							?>
						</select>
					</div>
				</div>

					<div class = "viewTaskOptions">
						<div class = "editDescription">Start Date:</div>
						<input type="date" name="sDate" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required>
					</div>

					<div class = "viewTaskOptions">
						<div class = "editDescription">Date of Completion:</div>
						<input type="date" name="cDate" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required>
					</div>

					<div class = "viewTaskOptions">
						<div class = "editDescription">Comments:</div>
						<input type="text" name="comment" maxlength="50" required>
					</div>

					<input type="hidden" name="status" value="Allocated">
			</div>
			<input id='add' style = "margin-left: 400px;" type='submit' id='submit' value='add'>
		</form>
	</div>
</div>	
		
</body>
</html>