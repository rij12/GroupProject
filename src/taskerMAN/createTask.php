<!DOCTYPE html>
<html>
<head> 
<title> Tasks </title>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" type="text/css" href="tasksStyles.css">
</head>
<body>

	<?php
require 'connect.php';
?>
<div id = "container"> 
		<?php include 'menu.php'; ?>


<div id ="bodyContainer">
	
	

		<div id ="body">
				<form method="POST" name="process" action="processTask.php" onsubmit="return validateForm()">
				<div id = "editTaskBodyMain">
					<div class = "viewTaskOptions">
						<div class = "editDescription">Task Title:</div>
						<input type="text" name="title" maxlength="50" pattern="[a-zA-Z0-9 ]+" required>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Allocated Member:</div>
						<select name="members">


<?php
$sql = "SELECT * FROM members";
$result = $con->query($sql);

if ($result->num_rows > 0) {

    while ($row = $result->fetch_assoc()) {
	
		echo '



					
   						<option value="' .$row['id']. '"> '.$row['name']. '</option>';
    					
  					
    					   }
} else {
    echo "0 results";
} 
?>
			</select>
			
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Start Date:</div>
						<input type="date" name="sDate" required>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Date of Completion:</div>
						<input type="date" name="cDate" required>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Comments:</div>
						<input type="text" name="comment" maxlength="50" pattern="[a-zA-Z0-9 ]+" required>
					</div>
					<script>
					
					function validateForm() {
	var start = document.forms["process"]["sDate"].value;
	var end = document.forms["process"]["cDate"].value;
	if (start > end) {
		alert("The start date must begin before the end date");
		return false;
	}
}</script>
					<input type="hidden" name="status" value="Allocated">
					</div>
				</div>
			
		
</div>


	<div id = "bodyFooter">
						<input name='add' type='submit' id='submit' value='add'>
						<div id = "remove"> Cancel</div>
					</div>
					</form>
</div>
</body>
</html>