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
				<form method="POST" action="processTask.php">
				<div id = "editTaskBodyMain">
					<div class = "viewTaskOptions">
						<div class = "editDescription">Task Title:</div>
						<input type="text" name="title">
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
						<input type="date" name="sDate">
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Date of Completion:</div>
						<input type="date" name="cDate">
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Comments:</div>
						<input type="text" name="comment">
					</div>
					
					
					
					<input type="radio" name="status" value="Allocated">Allocated
					<input type="radio" name="status" value="Abandoned">Abandoned
					<input type="radio" name="status" value="Complete">Complete
					</div>
				</div>
			
		
</div>


	<div id = "bodyFooter">
						<input name='add' type='submit' id='add' value='add'>
						<div id = "remove"> Cancel</div>
					</div>
					</form>
</div>
</body>
</html>