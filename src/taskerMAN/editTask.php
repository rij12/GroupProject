<!DOCTYPE html>
<html>
<head> 
<title> Tasks </title>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" type="text/css" href="tasksStyles.css">
</head>
<body>
	<div id = "container"> 
		<?php include 'menu.php'; ?>
		<div id ="bodyContainer">
			<div id ="body">


		<?php 

			require 'connect.php';

			$id = isset($_GET['TaskID']) ? $_GET['TaskID'] : 1;

			$sql = "SELECT * FROM tasks WHERE TaskID= '$id' ";
			$result = $con->query($sql);

			if ($result->num_rows > 0){

			while($row = $result->fetch_assoc()){
    			
        	$status = $row['Status'];
			$member = $row['MemberAllocated'];
			$startdate = $row['StartDate'];
			$completedate = $row['DateOfCompletion'];
			$comments = $row['Comments'];


        	echo '	 <form method="POST" name="process" action="updateTasks.php" onsubmit="return validateForm()">
        			<div id = "editTaskBodyMain">
					<div class = "viewTaskOptions">
						<div class = "editDescription">Task Title:</div>
						<input type="hidden" name = "id" value = "' . $row['TaskID']. '">
						<input type="text" maxlength="50" name="title" value="' . $row['TitleOfTask']. '">
						</div>
						
						<div class = "viewTaskOptions">
						
						<div class = "viewTaskOptions">
						<div class = "editDescription">Allocated Member:</div>
						<select name="member">';


						$sql = "SELECT * FROM members where id='$member'";
						$result = $con->query($sql);

						if ($result->num_rows > 0) {

							while ($row = $result->fetch_assoc()) {
							
								echo '

												<option value="' .$row['id']. '"> '.$row['name']. '</option>';
												
											
												   }
						} else {
							echo "0 results";
						} 
						$sql = "SELECT * FROM members";
						$result = $con->query($sql);

						if ($result->num_rows > 0) {

							while ($row = $result->fetch_assoc()) {
							if($row['id'] != $member){
								echo '

												<option value="' .$row['id']. '"> '.$row['name']. '</option>';
							}
												
											
												   }
						} else {
							echo "0 results";
						} 
						
			echo'</select></div>
						
					
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Start Date:</div>
						<input type="text" name="sDate" value="' .$startdate. '" required>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Date of Completion:</div>
						<input type="text" name="cDate" value="' .$completedate. '" required>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Task Elements:</div>
						<input type="text" name="comments" maxlength="50" value="' .$comments. '" required>
					</div>';?>
						<script>
					
					function validateForm() {
	var start = document.forms["process"]["sDate"].value;
	var end = document.forms["process"]["cDate"].value;
	if (start > end) {
		alert("The start date must begin before the end date");
		return false;
	}
}</script> <?php
                
                $data = $status;


			
				if($data == 'Complete') {
					echo '<input type="radio" name="status" value="Allocated">Allocated
					<input type="radio" name="status" value="Abandoned">Abandoned
					<input type="radio" name="status" value="Complete" checked >Complete'
				;
				} elseif ($data == 'Abandoned') {
					echo 	'<input type="radio" name="status" value="Allocated">Allocated
					<input type="radio" name="status" value="Abandoned"checked>Abandoned
					<input type="radio" name="status" value="Complete" >Complete';
				} elseif ($data == 'Allocated') {
					echo '<input type="radio" name="status" value="Allocated"checked>Allocated
					<input type="radio" name="status" value="Abandoned">Abandoned
					<input type="radio" name="status" value="Complete">Complete';
				}
			 
			
                
						
        	} 
				} else {
    				echo "0 results";
				}

		
	   

$con->close();
		?>
				
					
					<div id = "bodyFooter">
                                           
                                            <input name='edit' type='submit' id='edit' value='edit'>
                                        
                                        </form>
                                       
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>