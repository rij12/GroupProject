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
	<div id = "container"> 
		<!-- php file "menu" declared -->
		<?php include 'includes/php/menu.php'; ?>
		<div id ="bodyContainer">
			<div id ="body">

				<?php 

					require 'includes/php/connect.php';		// attempts connection to database

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


		        	echo '	<form method="POST" name="process" action="includes/php/updateTasks.php" onsubmit="return validateForm()">
		        			<div id = "editTaskBodyMain">
		        				'//Allows editing of task title
		        				. ' 
								<div class = "viewTaskOptions">
									<div class = "editDescription">Task Title:</div>
									<input type="hidden" name = "id" pattern="[a-zA-Z0-9 ]+" value = "' . $row['TaskID']. '">
									<input type="text" maxlength="50" name="title" value="' . $row['TitleOfTask']. '">
								</div>
								
								<div class = "viewTaskOptions">
								'//Allows alteration of who the task is allocated via a drop down list
		        				. ' 
									<div class = "editDescription">Allocated Member:</div>
									<div class = "editDescription" style = "margin-left: -85px;"><select name="member">';


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
							
							
							'//Allows editing of task start date
		        			.	' 
							<div class = "viewTaskOptions">
						<div class = "editDescription">Start Date:</div>
						<input type="date" name="sDate" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" value="';
						$dateUS =  implode("-", array_reverse(explode("/", $startdate)));
						echo $dateUS;
						echo '" required>
					</div>
					'//Allows editing of task deadline
		        	.			' 
					<div class = "viewTaskOptions">
						<div class = "editDescription">Date of Completion:</div>
						<input type="date" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name="cDate" value="';
						$dateUS =  implode("-", array_reverse(explode("/", $completedate)));
						echo $dateUS;
						echo '" required>
					</div>
					'//Allows editing of task comments
		        			.	' 
							<div class = "viewTaskOptions">
								<div class = "editDescription">Task Elements:</div>
								<input type="text" name="comments" maxlength="1000" value="' .$comments. '" required>
							</div>';
						                
			        $data = $status;
					// Allows user to pick a status for the task, looking slightly different depending on current status
					if($data == 'Complete') {
						echo 	'<input type="radio" name="status" value="Allocated">Allocated
								<input type="radio" name="status" value="Abandoned">Abandoned
								<input type="radio" name="status" value="Complete" checked >Complete'
						;
					} elseif ($data == 'Abandoned') {
						echo 	'<input type="radio" name="status" value="Allocated">Allocated
								<input type="radio" name="status" value="Abandoned"checked>Abandoned
								<input type="radio" name="status" value="Complete" >Complete';
					} elseif ($data == 'Allocated') {
						echo 	'<input type="radio" name="status" value="Allocated"checked>Allocated
								<input type="radio" name="status" value="Abandoned">Abandoned
								<input type="radio" name="status" value="Complete">Complete';

					}
						echo '<input id = "submit" name="edit" type="submit" id="Edit" value="Edit">
							<a href = "viewTasks.php"><div id = "remove"> Cancel </div></a>';
					}
					} else {
						// Cancel button to return to tasks page		
			    		echo "0 results";
			    		echo '<a href = "viewTasks.php"><div id = "remove"> Cancel </div></a>';
					}

					// Disconnect
					$con->close();
				?>

				</div>
				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>