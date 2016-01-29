<!DOCTYPE html>
<html>
    <head> 
        <title> Tasks </title>
        <!-- CSS style sheets used within the page are declared here -->
        <link rel="stylesheet" type="text/css" href="includes/css/styles.css">
        <link rel="stylesheet" type="text/css" href="includes/css/tasksStyles.css">
    </head>
    <body>

		<!-- PHP files used within the page are declared here -->
        <?php
        require 'includes/php/connect.php';
        ?>

        <div id = "container"> 
           <?php include 'includes/php/menu.php'; ?>
            <div id ="bodyContainer">

<?php
require 'includes/php/connect.php';

$id = isset($_GET['TaskID']) ? $_GET['TaskID'] : 1;

$sql = "SELECT * FROM tasks WHERE TaskID= '$id' ";
$result = $con->query($sql);

if ($result->num_rows > 0) {

    while ($row = $result->fetch_assoc()) {		// Gather all information on task from database

		$startdate = $row['StartDate'];
		$completedate = $row['DateOfCompletion'];
		$comments = $row['Comments'];
		$member = $row['MemberAllocated'];
		$status = $row['Status'];

        echo '	<div id ="body">
				<div id = "editTaskBodyMain">
					<div class = "viewTaskOptions">
						<div class = "editDescription">Task Title:</div>
						<div class = "editDescriptionInfo"> ' . $row['TitleOfTask'] . '</div>
					</div>
					<div class = "viewTaskOptions"> 
						<div class = "editDescription">Allocated Member:</div>
						<div class = "editDescriptionInfo"> ';
						$sql = "SELECT * FROM members WHERE id = '$member'";
							  $result2 = mysqli_query($con,$sql);
							  $row2 = mysqli_fetch_array($result2);
							  echo $row2['name'];
				echo '</div>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Start Date:</div>
						<div class = "editDescriptionInfo"> ' . $startdate . '</div>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Date of Completion:</div>
						<div class = "editDescriptionInfo"> ' . $completedate . '</div>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Task Status:</div>
						<div class = "editDescriptionInfo"> ' . $status . '</div>
					</div>
					<div class = "viewTaskOptions">
						<div class = "editDescription">Task Elements:</div>
						<div class = "taskElements"> ' . $comments . '</div>
					</div>
					
				</div>';
    }
} else {
    echo "0 results";
}	//Disconnect
$con->close();
?>			
                <a href = "viewTasks.php"><div id = "return">Return</div></a> <!-- return to tasks page -->
            </div>
        </div>
    </div>
</body>
</html>