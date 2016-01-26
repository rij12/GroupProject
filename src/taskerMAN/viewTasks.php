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

                    <div id = "bodyMain">
                        <?php
                        $sql = "SELECT * FROM tasks";
                        $result = $con->query($sql);

                        if ($result->num_rows > 0) {


                            while ($row = $result->fetch_assoc()) {
								$status = $row['Status'];
								$taskID = $row['TaskID'];
								$member = $row['MemberAllocated'];
                                echo '<div class = "viewTaskOptions">
						<div class = "taskDescription">    StartDate: ' . $row['StartDate'] . ' / Title Of Task: ' . $row['TitleOfTask'] . ' / Member: ';
						 $sql = "SELECT * FROM members WHERE id = '$member'";
							  $result2 = mysqli_query($con,$sql);
							  $row2 = mysqli_fetch_array($result2);
							  echo $row2['name'];
						echo ' / Status: ' . $status . '<br></div>
						<a href = "viewTask.php?TaskID=' . $taskID . '"><div class = "taskOptions"> View </div></a>
						<a href = "editTask.php?TaskID=' . $taskID . '"><div class = "taskOptions"> Edit </div></a>
					</div>';
                            }
                        } else {
                            echo "0 results";
                        }
                        $con->close();
                        
                        ?>


                    </div>
                </div>
            </div>
        </div>
    </body>
</html>