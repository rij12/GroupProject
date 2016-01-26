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

<?php
require 'connect.php';

$id = isset($_GET['TaskID']) ? $_GET['TaskID'] : 1;

$sql = "SELECT * FROM tasks WHERE TaskID= '$id' ";
$result = $con->query($sql);

if ($result->num_rows > 0) {

    while ($row = $result->fetch_assoc()) {

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
}
$con->close();
?>
                <a href = "viewTasks.php"><div id = "return">Return</div></a>
            </div>
        </div>
    </div>
</body>
</html>