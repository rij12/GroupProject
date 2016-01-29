<!DOCTYPE html>
<html>
<head> 
<title> Tasks </title>
	<!-- CSS style sheets used within the page are declared here -->
	<link rel="stylesheet" type="text/css" href="includes/css/styles.css">
	<link rel="stylesheet" type="text/css" href="includes/css/tasksStyles.css">
	<script src="scripts/script.js"></script>
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
			<!-- User input field with validation and instruction for title of task -->
				<div class = "viewTaskOptions">
					<div class = "editDescription">Task Title:</div> 	<!-- Instruction -->
					<input type="text" name="title" maxlength="50" pattern="[a-zA-Z0-9 ]+" required> 	<!-- input -->
				</div>
				<!-- User input field for allocation of member -->
				<div class = "viewTaskOptions">
					<div class = "editDescription">Allocated Member:</div> 	<!-- Instruction -->
					<div class = "editDescription" style = "margin-left: -85px;"> 	<!-- input -->
						<select name="members">;
							<?php 										//Drop down list with each existing member
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
					<!-- User input field with validation and instruction for start date -->
					<div class = "viewTaskOptions">
						<div class = "editDescription">Start Date:</div> <!-- Instruction -->
						<input type="date" name="sDate" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required> <!-- input -->
					</div>
					<!-- User input field with validation and instruction for deadline -->
					<div class = "viewTaskOptions">
						<div class = "editDescription">Date of Completion:</div> 	<!-- Instruction -->
						<input type="date" name="cDate" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required> 	<!-- input -->
					</div>
					<!-- User input field and instruction for any comments -->
					<div class = "viewTaskOptions">
						<div class = "editDescription">Comments:</div> 	<!-- Instruction -->
						<input type="text" name="comment" maxlength="1000" required> 	<!-- input -->
					</div>

					<input type="hidden" name="status" value="Allocated">
			</div>
			<input id='add' style = "margin-left: 400px;" type='submit' id='submit' value='add'>
		</form>
	</div>
</div>	
		</div>
</body>
</html>